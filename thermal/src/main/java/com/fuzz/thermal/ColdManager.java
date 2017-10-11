package com.fuzz.thermal;

import android.os.Binder;
import android.support.annotation.NonNull;

import com.fuzz.thermal.model.Temperature;

/**
 * Public mechanism for getting the current temperature.
 * <p>
 *     TODO: support setting current time?
 * </p>
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public abstract class ColdManager extends Binder {

    @NonNull
    public abstract Temperature getTemperature();
}
