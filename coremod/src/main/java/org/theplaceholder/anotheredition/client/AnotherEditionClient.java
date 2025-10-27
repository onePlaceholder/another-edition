package org.theplaceholder.anotheredition.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.theplaceholder.anotheredition.AnotherEdition;
import org.theplaceholder.anotheredition.item.AnotherEditionItems;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import java.util.Optional;

@Mod(value = AnotherEdition.MOD_ID, dist = Dist.CLIENT)
public class AnotherEditionClient {

    public AnotherEditionClient(IEventBus modEventBus) {}

    private static boolean hasItemInSlot(String slot, Item item) {
        Optional<ICuriosItemHandler> handler = CuriosApi.getCuriosInventory(MinecraftClient.getInstance().player);
        return handler.isPresent() && handler.get().getCurios().containsKey(slot) && handler.get().getCurios().get(slot).getStacks().getStackInSlot(0).isOf(item);
    }

    public static boolean hasJadeOverlay() {
        return hasItemInSlot("encyclopedia_universalis", AnotherEditionItems.ENCYCLOPEDIA_UNIVERSALIS.get());
    }

    public static boolean hasZoom() {
        return hasItemInSlot("spyglass", Items.SPYGLASS);
    }

    public static CoordStatue getCoordStatus() {
        CoordStatue result = CoordStatue.NONE;
        if (hasItemInSlot("compass", Items.COMPASS)) {
            result = CoordStatue.LIMITED;
        } else if (hasItemInSlot("compass", AnotherEditionItems.ENDER_COMPASS.get())) {
            result = CoordStatue.FULL;
        }
        return result;
    }
}
