package com.example.yassine.recyclerviewtests.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.yassine.recyclerviewtests.json.Field;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Yassine on 15/05/2017.
 */

public class VelibStation implements Parcelable {

    @SerializedName("recordid")
    public final String recordid;
    @SerializedName("fields")
    public final Field field;

    public VelibStation(String recordid, Field field) {
        this.recordid = recordid;
        this.field = field;
    }

    public VelibStation(Parcel in) {
        this.recordid = in.readString();
        this.field = in.readParcelable(Field.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(recordid);
        dest.writeParcelable(this.field, flags);
    }

    public static final Parcelable.Creator<VelibStation> CREATOR = new Parcelable.Creator<VelibStation>()
    {
        @Override
        public VelibStation createFromParcel(Parcel source)
        {
            return new VelibStation(source);
        }

        @Override
        public VelibStation[] newArray(int size)
        {
            return new VelibStation[size];
        }
    };
}