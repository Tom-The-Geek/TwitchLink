package me.geek.tom.twitchlink.api.event.info;

public class HostInfo {
    public final String username;
    public final int viewers;

    public HostInfo(String username, int viewers) {
        this.username = username;
        this.viewers = viewers;
    }
}
