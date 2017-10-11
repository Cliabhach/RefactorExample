package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

import com.fuzz.thermal.Affinity;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class HotWidget implements WeirdThing {

    @Affinity(Temperature.HOT)
    public HotWidget() {
    }

    @NonNull
    public String getId() {
        return "white";
    }
}
