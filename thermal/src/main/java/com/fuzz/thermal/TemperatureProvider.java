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
 * TODO: why extend binder?
 * TODO: convert to interface?
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public abstract class TemperatureProvider extends Binder {

    @NonNull
    public abstract Temperature getTemperature();
}
