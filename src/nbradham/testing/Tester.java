package nbradham.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

final class Tester {

	private final HashMap<String, ProdDetails> prodDetails = new HashMap<>();
	private final Queue<Recipe> queue = new LinkedList<>();

	private void start() {
		Scanner s = new Scanner(Tester.class.getResourceAsStream("/raws.tsv")).useDelimiter("\t|\n");
		s.nextLine();
		while (s.hasNext()) {
			prodDetails.put(s.next(), new ProdDetails(s.nextFloat()));
			s.nextLine();
		}
		s.close();

		s = new Scanner(Tester.class.getResourceAsStream("/recipes.tsv")).useDelimiter("\t|\n");
		s.nextLine();
		Recipe r;
		while (s.hasNext()) {
			queue.add(r = new Recipe(s.next(), s.next(), ItemStack.parseStacks(s.next()), s.nextShort(),
					ItemStack.parseStacks(s.next().strip())));
			for (ItemStack is : r.getInput()) {
				prodDetails.putIfAbsent(is.name(), new ProdDetails(Float.POSITIVE_INFINITY));
				prodDetails.get(is.name()).addConsumer(r);
			}
			for (ItemStack is : r.getOutput()) {
				prodDetails.putIfAbsent(is.name(), new ProdDetails(Float.POSITIVE_INFINITY));
				prodDetails.get(is.name()).addProducer(r);
			}
		}

		float sum, rate;
		ArrayList<Recipe> cs = new ArrayList<>();
		while (!queue.isEmpty()) {
			sum = 0;
			rate = 60 / (r = queue.poll()).getTime();
			System.out.printf("Processing %s... ", r.getName());
			for (ItemStack is : r.getInput())
				sum += rate * prodDetails.get(is.name()).getWeight();
			System.out.println(sum);
			if (sum < r.getWeight()) {
				r.setWeight(sum);
				cs.clear();
				for (ItemStack is : r.getOutput())
					cs.addAll(Arrays.asList(prodDetails.get(is.name()).getConsumers()));
				queue.addAll(cs);
				System.out.printf("ADDED MORE: %s%n", cs);
			}
		}
		prodDetails.values().forEach(v -> v.update());
		prodDetails.forEach((k, v) -> System.out.printf("%s -> %s%n", k, Arrays.toString(v.getProducers())));
	}

	public static void main(String[] args) {
		System.out.println("Started.");
		new Tester().start();
	}
}