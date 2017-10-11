package com.fuzz.thermal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;

import com.fuzz.thermal.exposed.HotBuild;
import com.fuzz.thermal.exposed.WarmBuild;
import com.fuzz.thermal.model.HotWidget;
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

    @Affinity(Temperature.COLD)
    @Nullable
    public static final WeirdThing makenew() {
        return null;
    }

    @Affinity(Temperature.COOL)
    public static final WeirdThing makenEw() {
        return new ProbablyUsedToBeAPOJO();
    }

    @Affinity(Temperature.MIDDLING)
    @Nullable
    public static final WeirdThing makeNew() {
        if (DefinitelyNotHelpful.isActive()) {
            return null;
        } else {
            return new ProbablyUsedToBeAPOJO();
        }
    }

    @Affinity(Temperature.HOT)
    public static final WeirdThing MaKenew(@NonNull Context context, @Nullable BundleCompat bundle) {
        if (DefinitelyNotHelpful.allowsBundles(context)) {
            return new HotBuild();
        } else {
            return new HotWidget();
        }
    }

    @Affinity(Temperature.WARM)
    public static final WeirdThing MaKEnew(@NonNull Context context, @Nullable BundleCompat bundle) {
        if (DefinitelyNotHelpful.allowsBundles(context)) {
            return new WarmBuild();
        } else {
            return new WarmWidget();
        }
    }
}
