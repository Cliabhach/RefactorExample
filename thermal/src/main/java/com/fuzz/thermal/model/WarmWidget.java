package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

import com.fuzz.thermal.CallerTempRestrictions;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class WarmWidget implements WarmThing, WeirdThing {

    @CallerTempRestrictions(Temperature.WARM)
    public WarmWidget() {
    }

    @NonNull
    public String getId() {
        return "yellow";
    }
}
