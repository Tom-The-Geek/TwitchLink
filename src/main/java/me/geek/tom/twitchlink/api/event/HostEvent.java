package me.geek.tom.twitchlink.api.event;

import me.geek.tom.twitchlink.api.event.info.HostInfo;
import net.blay09.javatmi.TwitchUser;

public class HostEvent extends TwitchEvent<HostInfo> {
    public HostEvent(String channel, TwitchUser user, HostInfo hostInfo) {
        super(channel, user, hostInfo);
    }
}
