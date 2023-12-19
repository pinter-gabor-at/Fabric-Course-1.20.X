package net.kaupenjoe.mccourse.datagen.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.recipe.GemEmpoweringRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class GemEmpoweringRecipeBuilder implements CraftingRecipeJsonBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final int count;
    private final Advancement.Builder advancement = Advancement.Builder.create();

    public GemEmpoweringRecipeBuilder(ItemConvertible ingredient, ItemConvertible result, int count) {
        this.ingredient = Ingredient.ofItems(ingredient);
        this.result = result.asItem();
        this.count = count;
    }

    @Override
    public CraftingRecipeJsonBuilder criterion(String name, AdvancementCriterion<?> conditions) {
        this.advancement.criterion(name, conditions);
        return this;
    }

    @Override
    public CraftingRecipeJsonBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public Item getOutputItem() {
        return result;
    }

    @Override
    public void offerTo(RecipeExporter exporter, Identifier recipeId) {
        exporter.accept(recipeId,
                new JsonBuilder(this.result, this.count, this.ingredient,
                this.advancement.build(new Identifier(recipeId.getNamespace(), "recipes/"
                        + recipeId.getPath())))
        );
    }

    public static class JsonBuilder implements RecipeJsonProvider {
        private final Item result;
        private final Ingredient ingredient;
        private final int count;
        private final AdvancementEntry advancement;

        public JsonBuilder(Item result, int count, Ingredient ingredient,
                           AdvancementEntry advancement) {
            this.result = result;
            this.ingredient = ingredient;
            this.count = count;
            this.advancement = advancement;
        }

        @Override
        public void serialize(JsonObject json) {
            JsonArray jsonarray = new JsonArray();
            jsonarray.add(ingredient.toJson(true));

            json.add("ingredients", jsonarray);
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", Registries.ITEM.getId(this.result).toString());
            if (this.count > 1) {
                jsonobject.addProperty("count", this.count);
            }

            json.add("output", jsonobject);
        }

        @Override
        public Identifier id() {
            return new Identifier(MCCourseMod.MOD_ID,
                    Registries.ITEM.getId(this.result).getPath() + "_from_gem_empowering");
        }

        @Override
        public RecipeSerializer<?> serializer() {
            return GemEmpoweringRecipe.Serializer.INSTANCE;
        }

        @Nullable
        @Override
        public AdvancementEntry advancement() {
            return advancement;
        }
    }
}
