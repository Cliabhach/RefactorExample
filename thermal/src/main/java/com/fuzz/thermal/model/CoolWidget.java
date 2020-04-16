package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

import com.fuzz.thermal.CallerTempRestrictions;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class CoolWidget implements WeirdThing {

    @CallerTempRestrictions(Temperature.COOL)
    public CoolWidget() {
    }

    @CallerTempRestrictions(Temperature.COOL)
    @NonNull
    public String getId() {
        return "green";
    }
}
