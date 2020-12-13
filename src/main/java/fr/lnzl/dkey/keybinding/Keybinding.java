package fr.lnzl.dkey.keybinding;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static fr.lnzl.dkey.DismountKeybinding.MOD_ID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class Keybinding {

    public static KeyBinding keybind;

    public static boolean wantsToDismount;
    public static boolean dismountBySneaking;

    public static void register() {
        keybind = new KeyBinding("key." + MOD_ID + ".dismount", GLFW.GLFW_KEY_R, "key.categories.misc");
        ClientRegistry.registerKeyBinding(keybind);
    }
}
