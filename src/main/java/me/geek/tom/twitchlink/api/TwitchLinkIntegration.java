package me.geek.tom.twitchlink.api;

import com.google.common.eventbus.EventBus;

import java.util.function.Consumer;

/**
 * Starting point for mods wanting to integrate with TwitchLink. Will be loaded from an entrypoint in
 * <code>fabric.mod.json</code> called <code>twitch-link</code>
 */
public interface TwitchLinkIntegration {
    /**
     * Called at launch during the client initializer, to allow other mods to register listeners to the
     * Twitch event bus.
     * @see EventBus for details on listeners
     * @param listener A listener to register using {@link EventBus#register(Object)}
     */
    void registerEventListeners(Consumer<Object> listener);
}
