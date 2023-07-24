package nbradham.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

final class Tester {

	private static final HashSet<String> RAW_ITEMS = new HashSet<>();
	static {
		RAW_ITEMS.add("Bauxite");
		RAW_ITEMS.add("Caterium Ore");
		RAW_ITEMS.add("Coal");
		RAW_ITEMS.add("Copper Ore");
		RAW_ITEMS.add("Flower Petals");
		RAW_ITEMS.add("Hatcher Remains");
		RAW_ITEMS.add("Hog Remains");
		RAW_ITEMS.add("Iron Ore");
		RAW_ITEMS.add("Leaves");
		RAW_ITEMS.add("Limestone");
		RAW_ITEMS.add("Mycelia");
		RAW_ITEMS.add("m^3 Crude Oil");
		RAW_ITEMS.add("m^3 Nitrogen Gas");
		RAW_ITEMS.add("m^3 Water");
		RAW_ITEMS.add("Plasma Spitter Remains");
		RAW_ITEMS.add("Blue Power Slug");
		RAW_ITEMS.add("Purple Power Slug");
		RAW_ITEMS.add("Yellow Power Slug");
		RAW_ITEMS.add("Raw Quartz");
		RAW_ITEMS.add("Stinger Remains");
		RAW_ITEMS.add("Sulfur");
		RAW_ITEMS.add("Uranium");
		RAW_ITEMS.add("Wood");
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(Tester.class.getResourceAsStream("/recipes.tsv")).useDelimiter("\t|\n");
		s.nextLine();
		Queue<Recipe> queue = new LinkedList<>();
		while (s.hasNext())
			queue.add(new Recipe(s.next(), s.next(), ItemStack.parseStacks(s.next()), s.nextShort(),
					ItemStack.parseStacks(s.next().strip())));
		System.out.println(queue);
	}

	private static record Recipe(String name, String machine, ItemStack[] input, short time, ItemStack[] output) {

		@Override
		public final String toString() {
			return "[" + name + "," + machine + "," + Arrays.toString(input) + "," + time + ","
					+ Arrays.toString(output) + "]";
		}
	}

	private static record ItemStack(float count, String Name) {

		private static ItemStack[] parseStacks(String string) {
			ArrayList<ItemStack> tmp = new ArrayList<>();
			String[] split;
			for (String stack : string.split(","))
				tmp.add(new ItemStack(Float.parseFloat((split = stack.split(":"))[0].strip()), split[1]));
			return tmp.toArray(new ItemStack[0]);
		}
	}
}