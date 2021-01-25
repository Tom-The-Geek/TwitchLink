package me.geek.tom.twitchlink.api;

import me.geek.tom.chaticons.api.ChatIconsApi;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static me.geek.tom.twitchlink.TwitchLink.MOD_ID;

/**
 * Helper for working with chat messages
 */
public class ChatHelper {
    /**
     * Adds a message to the specified {@link ChatHud} (usually from {@link InGameHud#getChatHud()} that will be prefixed
     * with a Twitch icon. It is recommended that all messages generated from Twitch are sent using this method
     *
     * @param chat The {@link ChatHud} to add the message to
     * @param message An {@link Text} that is the message to be prefixed with a twitch icon.
     */
    public static void addTwitchMessage(ChatHud chat, Text message) {
        ChatIconsApi.addChatMessageWithIcon(chat, message, new Identifier(MOD_ID, "textures/twitch_icon.png"));
    }
}
