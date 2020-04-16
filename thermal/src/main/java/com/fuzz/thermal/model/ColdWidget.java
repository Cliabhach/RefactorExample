package com.fuzz.thermal.model;

import com.fuzz.thermal.CallerTempRestrictions;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class ColdWidget implements WeirdThing {

    @CallerTempRestrictions(Temperature.COLD)
    public ColdWidget() {
    }
}
