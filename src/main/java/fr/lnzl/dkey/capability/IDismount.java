package fr.lnzl.dkey.capability;

public interface IDismount {
    boolean getWantsToDismount();

    void setWantsToDismount(boolean wantsToDismount);

    boolean getDismountBySneaking();

    void setDismountBySneaking(boolean dismountBySneaking);
}
