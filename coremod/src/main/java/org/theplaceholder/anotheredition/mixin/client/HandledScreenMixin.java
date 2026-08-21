package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.theplaceholder.anotheredition.AnotherEdition;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    @Shadow
    public static Identifier BACKGROUND_TEXTURE = AnotherEdition.identifier("textures/gui/container/inventory.png");

    @Shadow
    protected int backgroundHeight = 184;
}
