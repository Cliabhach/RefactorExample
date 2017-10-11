package com.fuzz.thermal.util;

import android.content.Context;
import android.os.UserManager;
import android.support.annotation.NonNull;

/**
 * A class of utility methods that are...not really that useful.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public abstract class DefinitelyNotHelpful {

    public static boolean isActive() {
        return true;
    }

    public static Boolean allowsBundles(@NonNull Context context) {
        UserManager userManager = (UserManager) context.getSystemService(Context.USER_SERVICE);
        return userManager != null && userManager.isUserAGoat();
    }
}
