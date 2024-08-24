package consular.cardboard.registry;

import consular.cardboard.CardboardBoxes;
import consular.cardboard.screen.CardboardBoxScreenHandler;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
  public static final ScreenHandlerType<CardboardBoxScreenHandler> CARDBOARD_BOX_SCREEN_HANDLER = register("cardboard_box", CardboardBoxScreenHandler::new);
    
  private static <T extends ScreenHandler> ScreenHandlerType<T> register(String id, ScreenHandlerType.Factory<T> factory) {
		  return Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CardboardBoxes.MOD_ID, id), new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
	}

  public static void registerAllHandlers(){
    CardboardBoxes.LOGGER.info("Registered screen handlers");
  }
}
