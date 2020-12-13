package fr.lnzl.dkey.capability;

public class DismountDefaultProvider implements IDismount {

    boolean wantsToDismount = false;
    boolean dismountBySneaking = true;

    @Override
    public boolean getWantsToDismount() {
        return wantsToDismount;
    }

    @Override
    public void setWantsToDismount(boolean wantsToDismount) {
        this.wantsToDismount = wantsToDismount;
    }

    @Override
    public boolean getDismountBySneaking() {
        return dismountBySneaking;
    }

    @Override
    public void setDismountBySneaking(boolean dismountBySneaking) {
        this.dismountBySneaking = dismountBySneaking;
    }

}
