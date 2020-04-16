package com.fuzz.thermal;

import com.fuzz.thermal.model.Temperature;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This marks methods and fields so you can tell
 * <ol>
 *     <li>what kind of code it calls</li>
 *     <li>what kind of code calls it</li>
 * </ol>
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CallerTempRestrictions {
    Temperature value() default Temperature.MIDDLING;
}
