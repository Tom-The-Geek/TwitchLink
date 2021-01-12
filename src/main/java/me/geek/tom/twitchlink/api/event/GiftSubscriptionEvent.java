package me.geek.tom.twitchlink.api.event;

import net.blay09.javatmi.GiftSubscriptionInfo;
import net.blay09.javatmi.TwitchUser;

public class GiftSubscriptionEvent extends TwitchEvent<GiftSubscriptionInfo> {
    public GiftSubscriptionEvent(String channel, TwitchUser user, GiftSubscriptionInfo giftSubscriptionInfo) {
        super(channel, user, giftSubscriptionInfo);
    }
}
