package org.theplaceholder.anotheredition.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.anotheredition.AnotherEdition;

@Mixin(IBlockExtension.class)
public interface IBlockExtensionMixin {
    @Inject(at = @At("RETURN"), method = "isPortalFrame", cancellable = true)
    private void isPortalFrame(BlockState state, BlockView level, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(AnotherEdition.getConfig().getNetherPortalFrameBlocks().contains((Block) this));
    }
}
