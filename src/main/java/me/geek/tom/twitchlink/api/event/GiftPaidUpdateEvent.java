package me.geek.tom.twitchlink.api.event;

import net.blay09.javatmi.GiftPaidUpgradeInfo;
import net.blay09.javatmi.TwitchUser;

public class GiftPaidUpdateEvent extends TwitchEvent<GiftPaidUpgradeInfo> {
    public GiftPaidUpdateEvent(String channel, TwitchUser user, GiftPaidUpgradeInfo giftPaidUpgradeInfo) {
        super(channel, user, giftPaidUpgradeInfo);
    }
}
