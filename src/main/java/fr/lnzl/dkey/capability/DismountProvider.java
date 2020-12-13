package fr.lnzl.dkey.capability;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DismountProvider implements ICapabilityProvider {


    private final DismountDefaultProvider dismount = new DismountDefaultProvider();
    private final LazyOptional<IDismount> dismountOptional = LazyOptional.of(() -> dismount);

    public void invalidate() {
        dismountOptional.invalidate();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return dismountOptional.cast();
    }

}
