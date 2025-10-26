package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.client.gui.screen.SplashOverlay;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.theplaceholder.anotheredition.AnotherEdition;

@Mixin(SplashOverlay.class)
public class LoadingDisplayMixin {
    @Final
    @Shadow
    private static final int MOJANG_RED = AnotherEdition.getConfig().getLoadingColor();
}
