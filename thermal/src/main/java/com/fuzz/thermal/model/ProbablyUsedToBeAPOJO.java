package com.fuzz.thermal.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.fuzz.thermal.Affinity;

/**
 * This class was some simple, parcelable object in the past...it's doing a bit too much now, though.
 *
 * @author Philip Cohn-Cort (Fuzz)
 */
public class ProbablyUsedToBeAPOJO implements WeirdThing, Parcelable {

    private int id;

    private int uniqueId;

    @NonNull
    private String name = "orange";

    @NonNull
    private Bundle additionalData;

    public static final ProbablyUsedToBeAPOJO EMPTY = new ProbablyUsedToBeAPOJO();

    public ProbablyUsedToBeAPOJO() {
        additionalData = Bundle.EMPTY;
    }

    protected ProbablyUsedToBeAPOJO(Parcel in) {
        id = in.readInt();
        uniqueId = in.readInt();
        name = in.readString();
        additionalData = in.readBundle();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(uniqueId);
        dest.writeString(name);
        dest.writeBundle(additionalData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Affinity(Temperature.MIDDLING)
    public String getName() {
        String name = "black";
        Bundle nameBundle = additionalData.getBundle("name");
        if (nameBundle != null) {
            name = nameBundle.getString("black");
        }
        return name;
    }

    @Affinity(Temperature.COOL)
    public String getColor() {
        String color = "blue";
        Bundle colorBundle = additionalData.getBundle("name");
        if (colorBundle != null) {
            color = colorBundle.getString("blue");
        }
        return color;
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
}
