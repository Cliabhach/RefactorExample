package com.fuzz.thermal.model;

import com.fuzz.thermal.Affinity;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class ColdWidget implements WeirdThing {

    @Affinity(Temperature.COLD)
    public ColdWidget() {
    }
}
