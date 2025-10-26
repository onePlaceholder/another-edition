package org.theplaceholder.anotheredition.mixin.client.justzoom;

import de.keksuccino.justzoom.ZoomHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.anotheredition.client.AnotherEditionClient;

@Mixin(ZoomHandler.class)
public class ZoomHandlerMixin {
    @Inject(method = "isZooming", at = @At("HEAD"), cancellable = true)
    private static void isZooming(CallbackInfoReturnable<Boolean> cir) {
        if (!AnotherEditionClient.hasZoom()) {
            cir.setReturnValue(false);
        }
    }
}
