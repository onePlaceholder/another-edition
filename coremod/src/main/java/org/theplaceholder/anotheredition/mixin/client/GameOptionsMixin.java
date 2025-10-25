package org.theplaceholder.anotheredition.mixin.client;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.theplaceholder.anotheredition.AnotherEdition;

import java.util.ArrayList;
import java.util.List;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @Shadow
    public KeyBinding[] allKeys;

    @Inject(method = "processOptionsForge", at = @At("TAIL"))
    private void processKeyBindings(GameOptions.Visitor arg, CallbackInfo ci) {
        List<String> disableKeybinds = AnotherEdition.getConfig().getDisabledKeybinds();
        List<KeyBinding> newKeys = new ArrayList<>();

        for (KeyBinding keymapping : this.allKeys) {
            if (disableKeybinds.contains(keymapping.getTranslationKey())){
                keymapping.setKeyModifierAndCode(KeyModifier.NONE, InputUtil.UNKNOWN_KEY);
            } else {
                newKeys.add(keymapping);
            }
        }
        this.allKeys = newKeys.toArray(new KeyBinding[0]);
    }
}
