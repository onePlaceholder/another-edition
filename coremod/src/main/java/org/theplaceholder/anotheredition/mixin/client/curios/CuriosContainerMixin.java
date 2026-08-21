package org.theplaceholder.anotheredition.mixin.client.curios;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import top.theillusivec4.curios.common.inventory.container.CuriosContainer;

@Mixin(CuriosContainer.class)
public class CuriosContainerMixin {

    @ModifyConstant(method = "setPage", constant = @Constant(intValue = 3))
    private int setRowNumber(int constant) {
        return 4;
    }

    @ModifyConstant(method = "setPage", constant = @Constant(intValue = 142))
    private int setY(int y) {
        return y + 18;
    }

    @ModifyConstant(method = "setPage", constant = @Constant(intValue = 40))
    private int setOffHandIndex(int index) {
        return index + 9;
    }

    @ModifyConstant(method = "setPage", constant = @Constant(intValue = 36))
    private int setArmorIndex(int index) {
        return index + 8;
    }
}
