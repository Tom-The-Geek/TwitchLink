package me.geek.tom.twitchlink.mixin;

import me.geek.tom.twitchlink.TwitchLink;
import me.geek.tom.twitchlink.ducks.TwitchChatHud;
import me.geek.tom.twitchlink.ducks.TwitchChatHudLine;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ChatHud.class)
public abstract class MixinChatHud implements TwitchChatHud {
    @Shadow protected abstract void addMessage(Text message, int messageId);

    @Shadow protected abstract void removeMessage(int messageId);

    @Shadow @Final private MinecraftClient client;

    @Override
    public void addTwitchMessage(Text message) {
        this.addMessage(message, -1);
    }

    @Redirect(method = "addMessage(Lnet/minecraft/text/Text;IIZ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;removeMessage(I)V"))
    private void redirect_removeMessage(ChatHud chatHud, int messageId) {
        if (messageId != 0 && messageId != -1) {
            this.removeMessage(messageId);
        }
    }

    @Inject(method = "render", locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/OrderedText;FFI)I"))
    private void hook_preRender(MatrixStack matrices, int tickDelta, CallbackInfo ci, int i, int j, boolean bl, double d, int k, double e, double f, double g, double h, int l, int m, ChatHudLine<Text> chatHudLine, double o, int p, int q, int r, double s) {
        if (((TwitchChatHudLine) chatHudLine).isTwitch()) {
            matrices.push();
            int size = this.client.textRenderer.fontHeight;
            matrices.translate(size + 2, 0, 0);
        }
    }

    @Inject(method = "render", locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/OrderedText;FFI)I", shift = At.Shift.AFTER))
    private void hook_postRender(MatrixStack matrices, int tickDelta, CallbackInfo ci, int i, int j, boolean bl, double d, int k, double e, double f, double g, double h, int l, int m, ChatHudLine<Text> chatHudLine, double o, int p, int q, int r, double s) {
        if (((TwitchChatHudLine) chatHudLine).isTwitch()) {
            matrices.pop();
            this.client.getTextureManager().bindTexture(TwitchLink.TWITCH_ICON);
            int size = this.client.textRenderer.fontHeight;
            DrawableHelper.drawTexture(matrices, 0, (int) (s + h - 1), size, size, 0, 0, 256, 256, 256, 256);
        }
    }
}
