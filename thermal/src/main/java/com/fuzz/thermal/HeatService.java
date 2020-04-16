package com.fuzz.thermal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fuzz.thermal.model.Hemisphere;
import com.fuzz.thermal.model.Temperature;

import org.threeten.bp.Clock;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.MonthDay;

/**
 * Background service capable of getting the current temperature.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public class HeatService extends Service {

    protected static final MonthDay autumnSolstice = MonthDay.of(Month.DECEMBER, 21);
    protected static final MonthDay winterEquinox = MonthDay.of(Month.MARCH, 20);
    protected static final MonthDay firstOfJune = MonthDay.of(Month.JUNE, 1);
    protected static final MonthDay lastOfAugust = MonthDay.of(Month.AUGUST, 31);
    @NonNull
    protected Temperature currentTemp = Temperature.MIDDLING;

    @NonNull
    private Clock clock = Clock.systemDefaultZone();

    @NonNull
    private Hemisphere hemisphere = Hemisphere.NORTHERN;

    /**
     * @return the name of a {@link Temperature} constant.
     */
    @NonNull
    public Temperature getCurrentTemp() {
        LocalDate now = LocalDate.now(clock);
        if (isWinter(now, hemisphere)) {
            return Temperature.COLD;
        } else if (isAutumn(now)) {
            return Temperature.COOL;
        } else if (isSummer(now)) {
            return Temperature.HOT;
        } else if (isSpring(now)) {
            return Temperature.WARM;
        } else {
            return Temperature.WHO_EVEN_KNOWS;
        }
    }

    private static boolean isWinter(@NonNull LocalDate now, Hemisphere hemisphere) {
        if (hemisphere == Hemisphere.NORTHERN) {
            return isInSeason(now, autumnSolstice, winterEquinox);
        } else {
            return hemisphere == Hemisphere.SOUTHERN && isInSeason(now, firstOfJune, lastOfAugust);
        }
    }

    private boolean isAutumn(@NonNull LocalDate now) {
        if (hemisphere == Hemisphere.NORTHERN) {
            MonthDay autumnStart = MonthDay.of(Month.SEPTEMBER, 22);
            MonthDay autumnEnd = autumnSolstice;

            return isInSeason(now, autumnStart, autumnEnd);
        } else if (hemisphere == Hemisphere.SOUTHERN) {
            MonthDay autumnStart = MonthDay.of(Month.MARCH, 1);
            MonthDay autumnEnd = MonthDay.of(Month.MAY, 31);

            return isInSeason(now, autumnStart, autumnEnd);
        } else {
            return false;
        }
    }

    private static boolean isInSeason(@NonNull LocalDate now, MonthDay autumnStart, MonthDay autumnEnd) {
        int autumnStartDay = autumnStart.atYear(now.getYear()).getDayOfYear();
        int autumnEndDay = autumnEnd.atYear(now.getYear()).getDayOfYear();

        int dayOfYear = now.getDayOfYear();
        return dayOfYear > autumnStartDay || dayOfYear < autumnEndDay;
    }

    private boolean isSummer(@NonNull LocalDate now) {
        if (hemisphere == Hemisphere.NORTHERN) {
            MonthDay summerStart = MonthDay.of(Month.JUNE, 21);
            MonthDay summerEnd = MonthDay.of(Month.SEPTEMBER, 22);

            return isInSeason(now, summerStart, summerEnd);
        } else if (hemisphere == Hemisphere.SOUTHERN) {
            MonthDay summerStart = MonthDay.of(Month.DECEMBER, 1);
            MonthDay summerEnd = MonthDay.of(Month.FEBRUARY, 29);

            return isInSeason(now, summerStart, summerEnd);
        } else {
            return false;
        }
    }

    private boolean isSpring(@NonNull LocalDate now) {
        if (hemisphere == Hemisphere.NORTHERN) {
            MonthDay springStart = winterEquinox;
            MonthDay springEnd = MonthDay.of(Month.JUNE, 21);

            return isInSeason(now, springStart, springEnd);
        } else if (hemisphere == Hemisphere.SOUTHERN) {
            MonthDay springStart = MonthDay.of(Month.SEPTEMBER, 1);
            MonthDay springEnd = MonthDay.of(Month.NOVEMBER, 30);

            return isInSeason(now, springStart, springEnd);
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new TemperatureProvider() {
            @NonNull
            @Override
            public Temperature getTemperature() {
                return getCurrentTemp();
            }
        };
    }
}
