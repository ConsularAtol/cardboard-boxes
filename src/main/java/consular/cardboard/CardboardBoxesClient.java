package consular.cardboard;

import consular.cardboard.registry.ModScreenHandlers;
import consular.cardboard.screen.CardboardBoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CardboardBoxesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.CARDBOARD_BOX_SCREEN_HANDLER, CardboardBoxScreen::new);
    }
}