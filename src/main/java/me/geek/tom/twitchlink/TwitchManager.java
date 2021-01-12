package me.geek.tom.twitchlink;

import com.google.common.eventbus.EventBus;
import me.geek.tom.twitchlink.api.ChatHelper;
import me.geek.tom.twitchlink.api.event.*;
import me.geek.tom.twitchlink.api.event.info.HostInfo;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.blay09.javairc.IRCConfiguration;
import net.blay09.javatmi.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TextColor;

import java.nio.charset.StandardCharsets;

@SuppressWarnings("UnstableApiUsage")
public class TwitchManager extends TMIAdapter {

    private final EventBus bus;
    private TMIClient client;
    private boolean isRestarting = false;

    public TwitchManager(EventBus bus) {
        this.bus = bus;
    }

    public void connect(MinecraftClient __) {
        if (this.client == null || !this.client.isConnected()) {
            IRCConfiguration config = TMIClient.defaultConfig();
            config.setEncoding(StandardCharsets.UTF_8);
            config.setNick(getAnonymousUsername());
            client = new TMIClient(config, this);
            client.connect();
        }
    }

    public void disconnect(MinecraftClient __) {
        if (this.client != null && this.client.isConnected()) {
            this.client.disconnect();
            this.client = null;
        }
    }

    @Override
    public void onConnected(TMIClient client) {
        System.out.println("Connected to twitch!");
        this.isRestarting = false;
        AutoConfig.getConfigHolder(Config.class).get().channels.forEach(client::join);
    }

    @Override
    public void onDisconnected(TMIClient client) {
        System.out.println("Disconnected from twitch!");
        if (this.isRestarting) MinecraftClient.getInstance().send(TwitchLink::reconnect);
    }

    @Override
    public void onReconnectInbound(TMIClient client) {
        System.out.println("Reconnect!");
        this.isRestarting = true;
    }

    @Override
    public void onChatMessage(TMIClient client, String channel, TwitchUser user, TwitchMessage message) {
        ChatEvent event = new ChatEvent(channel, user, message);
        this.bus.post(event);
        if (event.showInChat()) {
            ChatHelper.addTwitchMessage(MinecraftClient.getInstance().inGameHud.getChatHud(),
                    new LiteralText(user.getDisplayName()).styled(style ->
                            style.withColor(TextColor.fromRgb(fromHexColour(user.getColor())))
                                    .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new LiteralText("Twitch: " + channel)))
                    ).append(new LiteralText(": " + message.getMessage()))
            );
        }
    }

    @Override
    public void onSubscribe(TMIClient client, String channel, TwitchUser user, SubscriptionInfo subscriptionInfo) {
        this.bus.post(new SubscriptionEvent(channel, user, subscriptionInfo));
    }

    @Override
    public void onGiftSubscription(TMIClient client, String channel, TwitchUser user, GiftSubscriptionInfo giftSubscriptionInfo) {
        this.bus.post(new GiftSubscriptionEvent(channel, user, giftSubscriptionInfo));
    }

    @Override
    public void onGiftPaidUpgrade(TMIClient client, String channel, TwitchUser user, GiftPaidUpgradeInfo giftPaidUpgradeInfo) {
        this.bus.post(new GiftPaidUpdateEvent(channel, user, giftPaidUpgradeInfo));
    }

    @Override
    public void onResubscribe(TMIClient client, String channel, TwitchUser user, SubscriptionInfo subscriptionInfo) {
        this.bus.post(new ResubscribeEvent(channel, user, subscriptionInfo));
    }

    @Override
    public void onHost(TMIClient client, String channel, String username, int viewers) {
        this.bus.post(new HostEvent(channel, null, new HostInfo(username, viewers)));
    }

    @Override
    public void onHosted(TMIClient client, String channel, String username, int viewers) {
        this.bus.post(new HostedEvent(channel, null, new HostInfo(username, viewers)));
    }

    private static int fromHexColour(String colour) {
        if (colour.length() == 0) return 0xFFFFFF;
        if (colour.startsWith("#")) {
            return Integer.parseInt(colour.substring(1), 16);
        } else {
            return Integer.parseInt(colour, 16);
        }
    }

    private static String getAnonymousUsername() {
        return "justinfan" + (int) (Math.floor((Math.random() * 80000) + 1000));
    }
}
