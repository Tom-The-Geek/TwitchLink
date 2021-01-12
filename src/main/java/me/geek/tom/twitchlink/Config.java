package me.geek.tom.twitchlink;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;

import java.util.ArrayList;
import java.util.List;

import static me.geek.tom.twitchlink.TwitchLink.MOD_ID;

@me.sargunvohra.mcmods.autoconfig1u.annotation.Config(name = MOD_ID)
public class Config implements ConfigData {
    public List<String> channels = new ArrayList<>();
}
