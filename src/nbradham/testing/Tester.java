package nbradham.testing;

import java.util.HashMap;
import java.util.Scanner;

final class Tester {

	private static final String DELIM = "\t|\r\n";

	private final HashMap<String, Item> items = new HashMap<>();

	private void start() {
		Scanner scan = new Scanner(Tester.class.getResourceAsStream("/raws.tsv")).useDelimiter(DELIM);
		scan.nextLine();
		while (scan.hasNext()) {
			items.put(scan.next(), new Item(scan.nextFloat()));
			scan.nextLine();
		}
		System.out.println(items);
		scan.close();
		scan = new Scanner(Tester.class.getResourceAsStream("/recipes.tsv")).useDelimiter(DELIM);
		scan.nextLine();
		while (scan.hasNext()) {
			Recipe recipe = new Recipe(scan.next(), scan.next(), Item.parseItemStacks(scan.next()), scan.nextShort(),
					Item.parseItemStacks(scan.next()));
			recipe.outputs().keySet().forEach(i -> {
				items.putIfAbsent(i, new Item());
				items.get(i).addRecipe(recipe);
			});
		}
		System.out.println(items);
	}

	public static void main(String[] args) {
		new Tester().start();
	}
}