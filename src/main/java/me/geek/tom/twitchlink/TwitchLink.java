package me.geek.tom.twitchlink;

import com.google.common.eventbus.EventBus;
import me.geek.tom.twitchlink.api.TwitchLinkIntegration;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TwitchLink implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "twitch-link";
    public static final String MOD_NAME = "TwitchLink";

    public static final Identifier TWITCH_ICON = new Identifier(MOD_ID, "textures/twitch_icon.png");
    private static final EventBus EVENT_BUS = new EventBus("twitch_events");
    private static final TwitchManager manager = new TwitchManager(EVENT_BUS);

    public static void reconnect() {
        manager.disconnect(null);
        manager.connect(null);
    }

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);
        ClientLifecycleEvents.CLIENT_STARTED.register(manager::connect);
        ClientLifecycleEvents.CLIENT_STOPPING.register(manager::disconnect);
        FabricLoader.getInstance().getEntrypoints("twitch-link", TwitchLinkIntegration.class)
                .forEach(integration -> integration.registerEventListeners(EVENT_BUS::register));
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static void openSettings(GameMenuScreen parent) {
        MinecraftClient.getInstance().openScreen(AutoConfig.getConfigScreen(Config.class, parent).get());
    }
}
