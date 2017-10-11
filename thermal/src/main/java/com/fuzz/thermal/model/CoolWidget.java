package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

import com.fuzz.thermal.Affinity;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class CoolWidget implements WeirdThing {

    @Affinity(Temperature.COOL)
    public CoolWidget() {
    }

    @Affinity(Temperature.COOL)
    @NonNull
    public String getId() {
        return "green";
    }
}
