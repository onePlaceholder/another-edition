package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.client.gui.Element;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.theillusivec4.curios.client.gui.CuriosScreen;

@Mixin(CuriosScreen.class)
public class CuriosScreenMixin {
    @Redirect(method = "updateRenderButtons", at = @At(value = "INVOKE", target = "Ltop/theillusivec4/curios/client/gui/CuriosScreen;addDrawableChild(Lnet/minecraft/client/gui/Element;)Lnet/minecraft/client/gui/Element;", ordinal = 3))
    private Element removeRenderButtons(CuriosScreen instance, Element element) {
        return null;
    }
}
