package org.theplaceholder.anotheredition.mixin.client.coordhud;

import com.coorddisplay.coordhud.client.HUDRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.theplaceholder.anotheredition.client.AnotherEditionClient;
import org.theplaceholder.anotheredition.client.data.CoordStatue;

import java.util.List;

@Mixin(HUDRenderer.class)
public class HUDRendererMixin {
    @Redirect(method = "buildDisplayLines", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0))
    private static boolean showCoordinates(List<Object> list, Object string) {
        CoordStatue statue = AnotherEditionClient.getCoordStatus();
        if (statue != CoordStatue.NONE) {
            if (statue == CoordStatue.FULL) {
                list.add(string);
            } else {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                ClientWorld world = MinecraftClient.getInstance().world;
                if (world != null && player != null) {
                    if (world.getRegistryKey() == World.OVERWORLD) {
                        list.add(string);
                    } else {
                        list.add("XYZ: ??? / ??? / ???");
                    }
                }
            }
        }

        return true;
    }

    @Redirect(method = "buildDisplayLines", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 2))
    private static boolean showDirection(List<Object> list, Object string) {
        CoordStatue statue = AnotherEditionClient.getCoordStatus();
        if (statue != CoordStatue.NONE) {
            if (statue == CoordStatue.FULL) {
                list.add(string);
            } else {
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                ClientWorld world = MinecraftClient.getInstance().world;
                if (world != null && player != null) {
                    if (world.getRegistryKey() == World.OVERWORLD) {
                        list.add(string);
                    } else {
                        list.add("Facing: ???");
                    }
                }
            }
        }

        return true;
    }
}
