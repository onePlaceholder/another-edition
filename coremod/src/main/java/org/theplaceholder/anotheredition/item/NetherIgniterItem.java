package org.theplaceholder.anotheredition.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.NetherPortal;

import java.util.Optional;

public class NetherPortalIgniterItem extends Item {
    public NetherPortalIgniterItem(Settings settings) {
        super(settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult result = ActionResult.PASS;
        World world = context.getWorld();

        if (isOverworldOrNether(world)) {
            Optional<NetherPortal> portal = NetherPortal.getNewPortal(world, context.getBlockPos(), Direction.Axis.X);
            if (portal.isPresent()) {
                portal.get().createPortal();
                result = ActionResult.success(world.isClient());
            }
        }

        return result;
    }

    private static boolean isOverworldOrNether(World world) {
        return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER;
    }
}