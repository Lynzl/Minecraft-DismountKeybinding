package fr.lnzl.dkey.mixin;

import fr.lnzl.dkey.keybinding.Keybinding;
import net.minecraft.client.GameSettings;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.client.settings.KeyBinding;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetHandler.class)
public class MixinClientPlayNetHandler {

    @Redirect(
            method = "handleSetPassengers",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/GameSettings;keyBindSneak:Lnet/minecraft/client/settings/KeyBinding;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 0
            )
    )
    public KeyBinding keyBindDismount(GameSettings gameSettings) {
        return Keybinding.keybind;
    }
}
