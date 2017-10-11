package com.fuzz.thermal.model;

import com.fuzz.thermal.Affinity;

/**
 * Helper enum to mark what kind of {@link Affinity} a method is associated with.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public enum Temperature {
    COLD,
    COOL,
    MIDDLING,
    WARM,
    HOT,
    WHO_EVEN_KNOWS
}
