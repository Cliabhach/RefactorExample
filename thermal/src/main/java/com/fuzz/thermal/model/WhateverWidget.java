package com.fuzz.thermal.model;

import com.fuzz.thermal.CallerTempRestrictions;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class WhateverWidget implements WeirdThing {

    @CallerTempRestrictions(Temperature.WHO_EVEN_KNOWS)
    public WhateverWidget() {
    }
}
