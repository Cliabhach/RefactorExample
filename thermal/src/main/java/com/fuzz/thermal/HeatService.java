package com.fuzz.thermal;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

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

    @NonNull
    protected Temperature currentTemp = Temperature.MIDDLING;

    @NonNull
    private Clock clock = Clock.systemDefaultZone();

    @NonNull
    private Hemisphere hemisphere = Hemisphere.NORTHERN;

    private final IHeatManager manager = new IHeatManager.Stub() {
        /**
         * @return the name of a {@link Temperature} constant.
         * @throws RemoteException when something goes wrong
         */
        @NonNull
        @Override
        public String getCurrentTemp() throws RemoteException {
            LocalDate now = LocalDate.now(clock);
            if (isWinter(now)) {
                return Temperature.COLD.name();
            } else if (isAutumn(now)) {
                return Temperature.COOL.name();
            } else if (isSummer(now)) {
                return Temperature.HOT.name();
            } else if (isSpring(now)) {
                return Temperature.WARM.name();
            } else {
                return Temperature.WHO_EVEN_KNOWS.name();
            }
        }

        private boolean isWinter(@NonNull LocalDate now) {
            if (hemisphere == Hemisphere.NORTHERN) {
                MonthDay winterStart = MonthDay.of(Month.DECEMBER, 21);
                MonthDay winterEnd = MonthDay.of(Month.MARCH, 20);

                int winterStartDay = winterStart.atYear(now.getYear()).getDayOfYear();
                int winterEndDay = winterEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > winterStartDay || dayOfYear < winterEndDay;
            } else if (hemisphere == Hemisphere.SOUTHERN) {
                MonthDay winterStart = MonthDay.of(Month.JUNE, 1);
                MonthDay winterEnd = MonthDay.of(Month.AUGUST, 31);

                int winterStartDay = winterStart.atYear(now.getYear()).getDayOfYear();
                int winterEndDay = winterEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > winterStartDay || dayOfYear < winterEndDay;
            } else {
                return false;
            }
        }

        private boolean isAutumn(@NonNull LocalDate now) {
            if (hemisphere == Hemisphere.NORTHERN) {
                MonthDay autumnStart = MonthDay.of(Month.SEPTEMBER, 22);
                MonthDay autumnEnd = MonthDay.of(Month.DECEMBER, 21);

                int autumnStartDay = autumnStart.atYear(now.getYear()).getDayOfYear();
                int autumnEndDay = autumnEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > autumnStartDay || dayOfYear < autumnEndDay;
            } else if (hemisphere == Hemisphere.SOUTHERN) {
                MonthDay autumnStart = MonthDay.of(Month.MARCH, 1);
                MonthDay autumnEnd = MonthDay.of(Month.MAY, 31);

                int autumnStartDay = autumnStart.atYear(now.getYear()).getDayOfYear();
                int autumnEndDay = autumnEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > autumnStartDay || dayOfYear < autumnEndDay;
            } else {
                return false;
            }
        }

        private boolean isSummer(@NonNull LocalDate now) {
            if (hemisphere == Hemisphere.NORTHERN) {
                MonthDay summerStart = MonthDay.of(Month.JUNE, 21);
                MonthDay summerEnd = MonthDay.of(Month.SEPTEMBER, 22);

                int summerStartDay = summerStart.atYear(now.getYear()).getDayOfYear();
                int summerEndDay = summerEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > summerStartDay || dayOfYear < summerEndDay;
            } else if (hemisphere == Hemisphere.SOUTHERN) {
                MonthDay summerStart = MonthDay.of(Month.DECEMBER, 1);
                MonthDay summerEnd = MonthDay.of(Month.FEBRUARY, 29);

                int summerStartDay = summerStart.atYear(now.getYear()).getDayOfYear();
                int summerEndDay = summerEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > summerStartDay || dayOfYear < summerEndDay;
            } else {
                return false;
            }
        }

        private boolean isSpring(@NonNull LocalDate now) {
            if (hemisphere == Hemisphere.NORTHERN) {
                MonthDay springStart = MonthDay.of(Month.MARCH, 20);
                MonthDay springEnd = MonthDay.of(Month.JUNE, 21);

                int springStartDay = springStart.atYear(now.getYear()).getDayOfYear();
                int springEndDay = springEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > springStartDay || dayOfYear < springEndDay;
            } else if (hemisphere == Hemisphere.SOUTHERN) {
                MonthDay springStart = MonthDay.of(Month.SEPTEMBER, 1);
                MonthDay springEnd = MonthDay.of(Month.NOVEMBER, 30);

                int springStartDay = springStart.atYear(now.getYear()).getDayOfYear();
                int springEndDay = springEnd.atYear(now.getYear()).getDayOfYear();

                int dayOfYear = now.getDayOfYear();
                return dayOfYear > springStartDay || dayOfYear < springEndDay;
            } else {
                return false;
            }
        }
    };

    private static final String TAG = HeatService.class.getSimpleName();

    private final IBinder exposedInterface = new ColdManager() {
        @NonNull
        @Override
        public Temperature getTemperature() {
            try {
                return Temperature.valueOf(manager.getCurrentTemp());
            } catch (RemoteException e) {
                Log.w(TAG, "Can't get the temperature???", e);
            }
            return Temperature.WHO_EVEN_KNOWS;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return exposedInterface;
    }
}
