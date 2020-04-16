package com.fuzz.thermal.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fuzz.thermal.CallerTempRestrictions;

/**
 * This class was some simple, parcelable object in the past...it's doing a bit too much now, though.
 *
 * TODO: ...yeah, but what kind of object?
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public class ProbablyUsedToBeAPOJO implements WeirdThing, Parcelable {

    @NonNull
    private String name = "orange";

    @NonNull
    private Bundle additionalData;

    public static final ProbablyUsedToBeAPOJO EMPTY = new ProbablyUsedToBeAPOJO();

    public ProbablyUsedToBeAPOJO() {
        additionalData = Bundle.EMPTY;
    }


    protected ProbablyUsedToBeAPOJO(Parcel in) {
        name = in.readString();
        additionalData = in.readBundle();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeBundle(additionalData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProbablyUsedToBeAPOJO> CREATOR = new Creator<ProbablyUsedToBeAPOJO>() {
        @Override
        public ProbablyUsedToBeAPOJO createFromParcel(Parcel in) {
            return new ProbablyUsedToBeAPOJO(in);
        }

        @Override
        public ProbablyUsedToBeAPOJO[] newArray(int size) {
            return new ProbablyUsedToBeAPOJO[size];
        }
    };

    private String getValue() {
        String key = getName();
        return map.get(key);
    }

    @CallerTempRestrictions(Temperature.MIDDLING)
    public String getName() {
        return getPropertyOverride("black");
    }

    @CallerTempRestrictions(Temperature.COOL)
    public String getColor() {
        return getPropertyOverride("blue");
    }

    private String getPropertyOverride(@NonNull String defaultColor) {
        String name = defaultColor;
        Bundle nameBundle = additionalData.getBundle("name");
        if (nameBundle != null) {
            name = nameBundle.getString(defaultColor);
        }
        return name;
    }

}
