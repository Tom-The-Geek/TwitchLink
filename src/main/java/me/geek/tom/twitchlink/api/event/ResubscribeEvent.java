package me.geek.tom.twitchlink.api.event;

import net.blay09.javatmi.SubscriptionInfo;
import net.blay09.javatmi.TwitchUser;

public class ResubscribeEvent extends TwitchEvent<SubscriptionInfo> {
    public ResubscribeEvent(String channel, TwitchUser user, SubscriptionInfo subscriptionInfo) {
        super(channel, user, subscriptionInfo);
    }
}
