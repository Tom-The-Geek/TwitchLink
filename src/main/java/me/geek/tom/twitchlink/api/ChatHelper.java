package me.geek.tom.twitchlink.api;

import me.geek.tom.twitchlink.ducks.TwitchChatHud;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;

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
        ((TwitchChatHud) chat).addTwitchMessage(message);
    }
}
