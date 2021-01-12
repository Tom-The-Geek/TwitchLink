package me.geek.tom.twitchlink.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import static me.geek.tom.twitchlink.TwitchLink.MOD_ID;

public class TwitchButton extends TexturedButtonWidget {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/twitch_button.png");

    private final int type;
    private final Tooltip tooltip;
    public TwitchButton(int x, int y, int type, PressAction pressAction, Tooltip tooltip) {
        super(x, y, 16, 16, 0, 0, 0, TEXTURE, pressAction);
        this.type = type;
        this.tooltip = tooltip;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(TEXTURE);
        RenderSystem.enableDepthTest();
        drawTexture(matrices, this.x, this.y, 16, 16, this.type == 1 ? 31 : 0, this.isHovered() ? 31 : 0,  31, 31, 256, 256);
        if (this.isHovered()) {
            this.renderToolTip(matrices, mouseX, mouseY);
        }
    }

    @Override
    public void renderToolTip(MatrixStack matrices, int mouseX, int mouseY) {
        Text message = new TranslatableText(this.type == 0 ? "tooltip.twitchlink.configure" : "tooltip.twitchlink.reconnect");
        this.tooltip.drawTooltip(matrices, message, mouseX, mouseY);
    }

    public interface Tooltip {
        void drawTooltip(MatrixStack matrices, Text text, int mouseX, int mouseY);
    }
}
