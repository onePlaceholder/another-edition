package org.theplaceholder.anotheredition.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;

@Mixin(CuriosContainer.class)
public class PlayerScreenHandlerMixin {

    @ModifyConstant(method = "setPage", constant = @Constant(intValue = 3))
    private int setRowNumber(int constant) {
        return 4;
    }

    @ModifyConstant(method = "setPage", constant = @Constant(intValue = 142))
    private int setY(int y) {
        return y + 18;
    }
}
