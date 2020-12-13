package fr.lnzl.dkey.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityDismount {

    @CapabilityInject(IDismount.class)
    public static Capability<IDismount> CAPABILITY_DISMOUNT = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IDismount.class, new CapabilityDismount.DismountStorage(), DismountDefaultProvider::new);
    }

    public static class DismountStorage implements Capability.IStorage<IDismount> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IDismount> capability, IDismount instance, Direction side) {
            return null;
        }

        @Override
        public void readNBT(Capability<IDismount> capability, IDismount instance, Direction side, INBT nbt) {
        }

    }


}
