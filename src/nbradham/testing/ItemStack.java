package nbradham.testing;

import java.util.ArrayList;

final record ItemStack(float count, String name) {

	static ItemStack[] parseStacks(String string) {
		ArrayList<ItemStack> tmp = new ArrayList<>();
		String[] split;
		for (String stack : string.split(","))
			tmp.add(new ItemStack(Float.parseFloat((split = stack.split(":"))[0].strip()), split[1]));
		return tmp.toArray(new ItemStack[0]);
	}
}