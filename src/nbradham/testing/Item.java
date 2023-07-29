package nbradham.testing;

import java.util.ArrayList;
import java.util.HashMap;

final class Item {

	private ArrayList<Recipe> recipes = new ArrayList<>();
	private float weight;

	Item() {
		this(Float.POSITIVE_INFINITY);
	}

	Item(float iWeight) {
		weight = iWeight;
	}

	@Override
	public final String toString() {
		StringBuilder sb = new StringBuilder(Float.toString(weight));
		sb.append("\n");
		recipes.forEach(r -> {
			sb.append("\t");
			sb.append(r);
			sb.append("\n");
		});
		return sb.toString();
	}

	static HashMap<String, Float> parseItemStacks(String str) {
		String[] iSplit = str.split(", ");
		HashMap<String, Float> stacks = new HashMap<>();
		String[] cSplit;
		for (String s : iSplit)
			stacks.put((cSplit = s.split(":"))[1], Float.parseFloat(cSplit[0]));
		return stacks;
	}

	void addRecipe(Recipe recipe) {
		recipes.add(recipe);
	}
}