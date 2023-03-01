package committee.nova.sks.mixin;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameSettings.class)
public class MixinGameSettings {
    @Inject(method = "getKeyDisplayString", at = @At("HEAD"), cancellable = true)
    private static void inject$getKeyDisplayString(int key, CallbackInfoReturnable<String> cir) {
        if (key < 0) {
            cir.setReturnValue(I18n.format("key.mouseButton", key + 101));
            return;
        }
        String keyName = String.valueOf(key);
        try {
            keyName = Keyboard.getKeyName(key);
        } catch (Exception ignored) {
        }
        cir.setReturnValue(keyName);
    }
}
