package me.geek.tom.twitchlink.api.event;

import me.geek.tom.twitchlink.api.event.info.HostInfo;
import net.blay09.javatmi.TwitchUser;

public class HostedEvent extends TwitchEvent<HostInfo> {
    public HostedEvent(String channel, TwitchUser user, HostInfo hostInfo) {
        super(channel, user, hostInfo);
    }
}
