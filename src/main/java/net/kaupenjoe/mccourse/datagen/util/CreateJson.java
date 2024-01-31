package net.kaupenjoe.mccourse.datagen.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * General purpose JSON manipulation functions
 */
public final class CreateJson {

	/**
	 * Create a {@link JsonArray} from an iterable collection of elements
	 * @param objects an iterable collection of elements that can be converted to {@link JsonElement}
	 * @return the array
	 */
	public static JsonArray createJsonArray(Iterable<?> objects) {
		JsonArray jsa = new JsonArray();
		for (var e : objects) {
			if (e instanceof JsonElement) {
				jsa.add((JsonElement) e);
			} else if (e instanceof String) {
				jsa.add((String) e);
			} else if (e instanceof Number) {
				jsa.add((Number) e);
			} else if (e instanceof Character) {
				jsa.add((Character) e);
			} else if (e instanceof Boolean) {
				jsa.add((Boolean) e);
			}
		}
		return jsa;
	}
}
