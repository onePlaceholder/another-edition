package org.theplaceholder.anotheredition.mixin.client.curios;

import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.theillusivec4.curios.client.ClientEventHandler;

@Mixin(ClientEventHandler.class)
public class ClientEventHandlerMixin {
    @Redirect(method = "onTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0))
    private MutableText reFormat0(MutableText instance, Formatting formatting) {
        return instance.formatted(Formatting.GRAY);
    }

    @Redirect(method = "onTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 1))
    private MutableText reFormat1(MutableText instance, Formatting formatting) {
        return instance.formatted(Formatting.GRAY);
    }
}
