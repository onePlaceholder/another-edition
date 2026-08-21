package org.theplaceholder.anotheredition.mixin;

import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow
    public static final int MAIN_SIZE = 36 + 9;

    @Shadow
    public static final int OFF_HAND_SLOT = 40 + 9;

    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/DefaultedList;ofSize(ILjava/lang/Object;)Lnet/minecraft/util/collection/DefaultedList;", ordinal = 0))
    public int setMainSize(int size) {
        return MAIN_SIZE;
    }
}
