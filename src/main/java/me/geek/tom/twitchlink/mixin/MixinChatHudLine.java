package me.geek.tom.twitchlink.mixin;

import me.geek.tom.twitchlink.ducks.TwitchChatHudLine;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHudLine.class)
public class MixinChatHudLine implements TwitchChatHudLine {

    @SuppressWarnings("ShadowModifiers")
    @Shadow private int id;
    private boolean isTwitch = false;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void hook_init(int creationTick, Object text, int id, CallbackInfo ci) {
        if (id == -1) {
            this.id = 0;
            this.isTwitch = true;
        }
    }

    @Override
    public boolean isTwitch() {
        return this.isTwitch;
    }
}
