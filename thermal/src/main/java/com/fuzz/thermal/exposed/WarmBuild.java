package com.fuzz.thermal.exposed;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;

import com.fuzz.thermal.CallerTempRestrictions;
import com.fuzz.thermal.model.Temperature;
import com.fuzz.thermal.model.WeirdThing;

/**
 * A variant of {@link Bundle} for use by {@link Temperature#WARM WARM} code.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public class WarmBuild extends Build implements WeirdThing {
    /**
     * Constructs a new, empty Build.
     */
    @CallerTempRestrictions(Temperature.WARM)
    public WarmBuild() {
        super();
    }

    /**
     * TODO: use like dynamic {@link android.support.annotation.ColorRes} ints?
     *
     * @return our favorite color (Sandy Brown!)
     */
    @CallerTempRestrictions(Temperature.WARM)
    @ColorInt
    public int getFaveColor() {
        return Color.parseColor("#FFBF3C");
    }
}
