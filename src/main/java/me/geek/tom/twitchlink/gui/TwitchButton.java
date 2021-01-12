package me.geek.tom.twitchlink.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import static me.geek.tom.twitchlink.TwitchLink.MOD_ID;

public class TwitchButton extends TexturedButtonWidget {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/twitch_button.png");

    private final int type;
    public TwitchButton(int x, int y, int type, PressAction pressAction) {
        super(x, y, 16, 16, 0, 0, 0, TEXTURE, pressAction);
        this.type = type;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        MinecraftClient.getInstance().getTextureManager().bindTexture(TEXTURE);
        RenderSystem.enableDepthTest();
        drawTexture(matrices, this.x, this.y, 16, 16, this.type == 1 ? 30 : 0, this.isHovered() ? 30 : 0,  30, 30, 256, 256);
        if (this.isHovered()) {
            this.renderToolTip(matrices, mouseX, mouseY);
        }
    }
}
