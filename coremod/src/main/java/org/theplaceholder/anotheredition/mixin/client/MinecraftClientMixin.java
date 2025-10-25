package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.anotheredition.AnotherEdition;
import org.theplaceholder.anotheredition.config.AnotherEditionConfig;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> cir) {
        AnotherEditionConfig config = AnotherEdition.getConfig();
        if (config != null) {
            cir.setReturnValue("Minecraft " + SharedConstants.getGameVersion().getName() + " - Another Edition " + config.getModpackVersion());
        }
    }
}
