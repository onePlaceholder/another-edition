package org.theplaceholder.anotheredition.mixin.client.coordhud;

import com.coorddisplay.coordhud.client.KeyBindings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBindings.class)
public class KeyBindingsMixin {
    @Inject(method = "handleKeyInputs", at = @At("HEAD"), cancellable = true)
    private static void cancelKeyInput(CallbackInfo ci) {
        ci.cancel();
    }
}
