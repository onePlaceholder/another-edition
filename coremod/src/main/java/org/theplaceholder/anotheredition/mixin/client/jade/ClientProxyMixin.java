package org.theplaceholder.anotheredition.mixin.client.jade;

import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.theplaceholder.anotheredition.client.AnotherEditionClient;
import snownee.jade.util.ClientProxy;

@Mixin(ClientProxy.class)
public class ClientProxyMixin {
    @Inject(method = "onRenderTick", at = @At("HEAD"), cancellable = true)
    private static void cancelRender(DrawContext guiGraphics, float tickDelta, CallbackInfo ci) {
        if (!AnotherEditionClient.hasJadeOverlay()) ci.cancel();
    }
}
