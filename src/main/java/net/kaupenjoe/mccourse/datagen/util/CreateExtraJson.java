package net.kaupenjoe.mccourse.datagen.util;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.kaupenjoe.mccourse.util.ModIdentifier;

import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

/**
 * Special JSON manipulation functions
 */
public final class CreateExtraJson {

	/**
	 * Create the {"predicate":{...}, "model": "..."} {@link JsonObject}
	 * @param predicates (key, value) pairs of predicates
	 * @param model "model" link
	 */
	private static JsonObject createJsonPredicateModel(Map<String, Number> predicates, String model) {
		// {...}
		JsonObject jsonPredicate = new JsonObject();
		for (var p : predicates.entrySet()) {
			jsonPredicate.addProperty(p.getKey(), p.getValue());
		}
		// {"predicate":{...}, "model": "..."}
		JsonObject jso = new JsonObject();
		jso.addProperty("model", model);
		jso.add("predicate", jsonPredicate);
		return jso;
	}

	/**
	 * Create models.item/*.json with one texture layer and an 'on' predicate
	 */
	public static JsonObject createOnJson(Identifier id, Map<TextureKey, Identifier> textures) {
		// {"predicate":{...}, "model": "..."}
		JsonObject jsonOverride = createJsonPredicateModel(
			Map.of(new ModIdentifier("on").toString(), 1),
			id + "_on");
		// "overrides": []
		JsonArray jsonOverrides = new JsonArray();
		jsonOverrides.add(jsonOverride);
		// { parent, overrides, textures }
		JsonObject jsonObject = Models.GENERATED.createJson(id, textures);
		jsonObject.add("overrides", jsonOverrides);
		return jsonObject;
	}
}
