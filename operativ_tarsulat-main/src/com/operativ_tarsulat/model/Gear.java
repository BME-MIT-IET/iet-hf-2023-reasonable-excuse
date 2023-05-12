package com.operativ_tarsulat.model;

import java.io.Serializable;

public abstract class Gear extends Observable implements Effect, Serializable {
    public abstract GearSlot GetSlot();
}
