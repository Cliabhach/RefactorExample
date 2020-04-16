package com.fuzz.thermal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;

import com.fuzz.thermal.exposed.WarmBuild;
import com.fuzz.thermal.model.ProbablyUsedToBeAPOJO;
import com.fuzz.thermal.model.Temperature;
import com.fuzz.thermal.model.WarmWidget;
import com.fuzz.thermal.model.WeirdThing;
import com.fuzz.thermal.util.DefinitelyNotHelpful;

/**
 * This class makes {@link com.fuzz.thermal.model.WeirdThing WeirdThings} for different
 * {@link Temperature Temperatures}, but there's...some room for improvement.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public final class TerribleFactory {

    @CallerTempRestrictions(Temperature.COOL)
    public static final WeirdThing newCoolObject() {
        return new ProbablyUsedToBeAPOJO();
    }

    @CallerTempRestrictions(Temperature.WARM)
    public static final WeirdThing newWarmObject(@NonNull Context context, @Nullable BundleCompat bundle) {
        if (DefinitelyNotHelpful.allowsBundles(context)) {
            return new WarmBuild();
        } else {
            return new WarmWidget();
        }
    }
}
