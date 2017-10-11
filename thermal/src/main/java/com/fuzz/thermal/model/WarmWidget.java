package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

import com.fuzz.thermal.Affinity;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class WarmWidget implements WeirdThing {

    @Affinity(Temperature.WARM)
    public WarmWidget() {
    }

    @NonNull
    public String getId() {
        return "yellow";
    }
}
