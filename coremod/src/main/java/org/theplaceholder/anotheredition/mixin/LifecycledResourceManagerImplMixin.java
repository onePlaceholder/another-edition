package org.theplaceholder.anotheredition.mixin;

import net.minecraft.resource.LifecycledResourceManagerImpl;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;

@Mixin(LifecycledResourceManagerImpl.class)
public class LifecycledResourceManagerImplMixin {
    @Inject(method = "findResources", at = @At("RETURN"), cancellable = true)
    private void removeResource(String startingPath, Predicate<Identifier> allowedPathPredicate, CallbackInfoReturnable<Map<Identifier, Resource>> cir) {
        Map<Identifier, Resource> original = cir.getReturnValue();
        List<String> disabled = AnotherEdition.getConfig().getDisabledResources();

        Map<Identifier, Resource> result = new TreeMap<>();
        for (Identifier id : original.keySet()) {
            if (!disabled.contains(id.toString())) {
                result.put(id, original.get(id));
            }
        }

        cir.setReturnValue(result);
    }
}
