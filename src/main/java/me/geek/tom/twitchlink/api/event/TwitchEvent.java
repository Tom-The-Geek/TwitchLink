package me.geek.tom.twitchlink.api.event;

import net.blay09.javatmi.TwitchUser;

public abstract class TwitchEvent<INFO> {
    private final String channel;
    private final TwitchUser user;
    private final INFO info;

    public TwitchEvent(String channel, TwitchUser user, INFO info) {
        this.channel = channel;
        this.user = user;
        this.info = info;
    }

    public String getChannel() {
        return channel;
    }

    public TwitchUser getUser() {
        return user;
    }

    public INFO getInfo() {
        return info;
    }
}
