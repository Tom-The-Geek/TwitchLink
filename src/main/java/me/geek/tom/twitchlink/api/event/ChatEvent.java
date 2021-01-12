package me.geek.tom.twitchlink.api.event;

import net.blay09.javatmi.TwitchMessage;
import net.blay09.javatmi.TwitchUser;

public class ChatEvent {

    private final String channel;
    private final TwitchUser user;
    private final TwitchMessage message;
    private boolean showInChat = true;

    public ChatEvent(String channel, TwitchUser user, TwitchMessage message) {
        this.channel = channel;
        this.user = user;
        this.message = message;
    }

    public String getChannel() {
        return channel;
    }

    public TwitchUser getUser() {
        return user;
    }

    public TwitchMessage getMessage() {
        return message;
    }

    public boolean showInChat() {
        return showInChat;
    }

    public void setShowInChat(boolean showInChat) {
        this.showInChat = showInChat;
    }
}
