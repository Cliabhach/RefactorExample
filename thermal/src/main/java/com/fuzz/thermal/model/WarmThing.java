package com.fuzz.thermal.model;

import android.support.annotation.NonNull;

/**
 * Marks things that are {@link Temperature#WARM}
 * @author Philip Cohn-Cort (Fuzz)
 */
public interface WarmThing {

    @NonNull
    String getKey();
}
