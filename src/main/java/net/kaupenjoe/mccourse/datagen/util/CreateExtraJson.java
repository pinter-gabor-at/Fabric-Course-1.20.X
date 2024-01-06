package net.kaupenjoe.mccourse.datagen.util;

import java.util.List;
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
	 * Create tha magic "display" {@link JsonObject} of the "bow" model
	 */
	private static JsonObject createJsonBowDisplay() {
		JsonObject jso = new JsonObject();
		JsonObject thirdRight = createJsonBowDisplayElement(
			List.of(-80, 260, -40),
			List.of(-1, -2, 2.5),
			List.of(0.9, 0.9, 0.9));
		JsonObject thirdLeft = createJsonBowDisplayElement(
			List.of(-80, -280, 40),
			List.of(-1, -2, 2.5),
			List.of(0.9, 0.9, 0.9));
		JsonObject firstRight = createJsonBowDisplayElement(
			List.of(0, -90, 25),
			List.of(1.13, 3.2, 1.13),
			List.of(0.68, 0.68, 0.68));
		JsonObject firstLeft = createJsonBowDisplayElement(
			List.of(0, 90, -25),
			List.of(1.13, 3.2, 1.13),
			List.of(0.68, 0.68, 0.68));
		jso.add("thirdperson_righthand", thirdRight);
		jso.add("thirdperson_lefthand", thirdLeft);
		jso.add("firstperson_righthand", firstRight);
		jso.add("firstperson_lefthand", firstLeft);
		return jso;
	}

	/**
	 * Used by {@link #createJsonBowDisplay()}
	 */
	private static JsonObject createJsonBowDisplayElement(
		Iterable<Number> rotation,
		Iterable<Number> translation,
		Iterable<Number> scale) {
		JsonObject jso = new JsonObject();
		jso.add("rotation", CreateJson.createJsonArray(rotation));
		jso.add("translation", CreateJson.createJsonArray(translation));
		jso.add("scale", CreateJson.createJsonArray(scale));
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

	/**
	 * Create models.item/*.json with one texture layer and 'bow' style predicates
	 */
	public static JsonObject createBowJson(Identifier id, Map<TextureKey, Identifier> textures) {
		// {"predicate": {...}, "model"="..."}
		JsonObject override0 = createJsonPredicateModel(
			Map.of("pulling", 1),
			id.toString() + "_pulling_0");
		JsonObject override1 = createJsonPredicateModel(
			Map.of("pulling", 1, "pull", 0.65f),
			id + "_pulling_1");
		JsonObject override2 = createJsonPredicateModel(
			Map.of("pulling", 1, "pull", 0.9f),
			id + "_pulling_2");
		// "overrides": []
		JsonArray jsonOverrides =
			CreateJson.createJsonArray(List.of(override0, override1, override2));
		// "display": {}
		JsonObject jsonDisplay = createJsonBowDisplay();
		// { parent, display, overrides, textures }
		JsonObject jsonObject = Models.GENERATED.createJson(id, textures);
		jsonObject.add("display", jsonDisplay);
		jsonObject.add("overrides", jsonOverrides);
		return jsonObject;
	}
}
