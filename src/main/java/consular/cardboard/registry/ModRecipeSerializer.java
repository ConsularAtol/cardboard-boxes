package consular.cardboard.registry;

import consular.cardboard.CardboardBoxes;
import consular.cardboard.recipe.CardboardBoxColoringRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface ModRecipeSerializer<T extends Recipe<?>> {
    public static final RecipeSerializer<CardboardBoxColoringRecipe> CARDBOARD_BOX = register("crafting_special_cardboardboxcoloring", new SpecialRecipeSerializer<CardboardBoxColoringRecipe>(CardboardBoxColoringRecipe::new));

    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(CardboardBoxes.MOD_ID, id), serializer);
    }

    public static void registerSerializer(){
        CardboardBoxes.LOGGER.info("registered serializers");
    }
}
