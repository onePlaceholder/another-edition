package org.theplaceholder.anotheredition.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.function.BiConsumer;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text title) {
        super(title);
    }

    @Redirect(method = "initWidgetsNormal", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 2))
    private Element removeRealmsButton(TitleScreen instance, Element element) {
        return element;
    }

    @ModifyArgs(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;position(II)Lnet/minecraft/client/gui/widget/ButtonWidget$Builder;", ordinal = 0))
    private void removeRealmsButton(Args args) {
        args.set(1, (int) args.get(1) - 24);
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 0, shift = At.Shift.AFTER))
    private void removeRealmsButton(CallbackInfo ci, @Local(ordinal = 3) LocalIntRef l) {
        l.set(l.get()-22);
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/client/ClientHooks;renderMainMenu(Lnet/minecraft/client/gui/screen/TitleScreen;Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/font/TextRenderer;III)V"))
    private void removeNeoForge(TitleScreen gui, DrawContext guiGraphics, TextRenderer font, int width, int height, int alpha) {}

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/neoforged/neoforge/internal/BrandingControl;forEachLine(ZZLjava/util/function/BiConsumer;)V"))
    private void removeNeoForge(boolean includeMC, boolean reverse, BiConsumer<Integer, String> lineConsumer, @Local(ordinal = 2) int i, @Local String s, @Local(argsOnly = true) DrawContext context) {
        s = s.split("\\(")[0] + "- Another Edition " + AnotherEdition.getConfig().getModpackVersion();
        context.drawTextWithShadow(this.textRenderer, s, 2, this.height - 10, 16777215 | i);
    }
}
