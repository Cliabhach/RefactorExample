package com.fuzz.thermal.exposed;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;

import com.fuzz.thermal.CallerTempRestrictions;
import com.fuzz.thermal.model.Temperature;
import com.fuzz.thermal.model.WeirdThing;

/**
 * A variant of {@link Bundle} for use by {@link Temperature#HOT HOT} code.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public class HotBuild extends Build implements WeirdThing {
    /**
     * Constructs a new, empty Build.
     */
    @CallerTempRestrictions(Temperature.HOT)
    public HotBuild() {
        super();
    }

    /**
     * TODO: use like dynamic {@link android.support.annotation.ColorRes} ints?
     *
     * @return our favorite color (Chocolate!)
     */
    @CallerTempRestrictions(Temperature.HOT)
    @ColorInt
    public int getFaveColor() {
        return Color.parseColor("#E85A0C");
    }
}
