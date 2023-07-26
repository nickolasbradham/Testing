package nbradham.testing;

import java.util.Arrays;

final class ProdDetails {
	private float weight;
	private Recipe[] consumers = {}, producers = {};

	ProdDetails(float setWeight) {
		weight = setWeight;
	}

	void addConsumer(Recipe r) {
		int l = consumers.length;
		consumers = Arrays.copyOf(consumers, l + 1);
		consumers[l] = r;
	}

	void addProducer(Recipe r) {
		int l = producers.length;
		producers = Arrays.copyOf(producers, l + 1);
		producers[l] = r;
	}

	float getWeight() {
		return weight;
	}

	Recipe[] getConsumers() {
		return consumers;
	}

	Recipe[] getProducers() {
		return producers;
	}

	void update() {
		Arrays.sort(producers, (a, b) -> (int) (a.weight - b.weight));
		if (producers.length > 0)
			weight = producers[0].weight;
	}

	@Override
	public final String toString() {
		return "ProdDetails[" + weight + "," + Arrays.toString(consumers) + "," + Arrays.toString(producers) + "]";
	}
}