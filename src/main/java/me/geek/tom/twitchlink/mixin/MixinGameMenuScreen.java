package me.geek.tom.twitchlink.mixin;

import me.geek.tom.twitchlink.TwitchLink;
import me.geek.tom.twitchlink.gui.TwitchButton;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class MixinGameMenuScreen extends Screen {
    protected MixinGameMenuScreen(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("RETURN"))
    private void hook_initWidgets(CallbackInfo ci) {
        this.addButton(new TwitchButton(5, 15, 0, btn -> TwitchLink.openSettings((GameMenuScreen)(Object)this)));
        this.addButton(new TwitchButton(5, 31, 1, btn -> TwitchLink.reconnect()));
    }
}
