package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

import com.fuzz.thermal.CallerTempRestrictions;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class HotWidget implements WeirdThing {

    @CallerTempRestrictions(Temperature.HOT)
    public HotWidget() {
    }

    @NonNull
    public String getId() {
        return "white";
    }
}
