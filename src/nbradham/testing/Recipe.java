package nbradham.testing;

final class Recipe {
	private final String name, machine;
	private final ItemStack[] input, output;
	private final short time;
	float weight = Float.POSITIVE_INFINITY;

	Recipe(String rName, String rMachine, ItemStack[] rInput, short rTime, ItemStack[] rOutput) {
		name = rName;
		machine = rMachine;
		input = rInput;
		output = rOutput;
		time = rTime;
	}

	void setWeight(float newWeight) {
		weight = newWeight;
	}

	ItemStack[] getInput() {
		return input;
	}

	ItemStack[] getOutput() {
		return output;
	}

	short getTime() {
		return time;
	}

	float getWeight() {
		return weight;
	}

	@Override
	public final String toString() {
		return weight+name;
	}
}