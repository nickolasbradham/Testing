package nbradham.testing;

import java.util.HashMap;

record Recipe(String name, String machine, HashMap<String, Float> inputs, short time, HashMap<String, Float> outputs) {
}