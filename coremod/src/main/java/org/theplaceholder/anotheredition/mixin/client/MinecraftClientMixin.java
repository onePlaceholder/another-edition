package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.anotheredition.AnotherEdition;
import org.theplaceholder.anotheredition.config.AnotherEditionConfig;
import top.theillusivec4.curios.common.network.client.CPacketOpenCurios;

import javax.annotation.Nullable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow
    @Nullable
    public ClientPlayerInteractionManager interactionManager;

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void getWindowTitle(CallbackInfoReturnable<String> cir) {
        AnotherEditionConfig config = AnotherEdition.getConfig();
        if (config != null) {
            cir.setReturnValue("Minecraft " + SharedConstants.getGameVersion().getName() + " - Another Edition " + config.getModpackVersion());
        }
    }

    @ModifyArg(method = "handleInputEvents", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 1))
    private Screen setInventoryScreen(Screen screen) {
        if (screen instanceof InventoryScreen && !interactionManager.hasCreativeInventory()) {
            PacketDistributor.sendToServer(new CPacketOpenCurios(ItemStack.EMPTY));
            return null;
        }
        return screen;
    }
}
