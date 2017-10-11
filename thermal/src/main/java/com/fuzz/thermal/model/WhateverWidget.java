package com.fuzz.thermal.model;

import com.fuzz.thermal.Affinity;

/**
 * @author Philip Cohn-Cort (Fuzz)
 */
public class WhateverWidget implements WeirdThing {

    @Affinity(Temperature.WHO_EVEN_KNOWS)
    public WhateverWidget() {
    }
}
