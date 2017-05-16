package com.example.yassine.recyclerviewtests.json;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Field implements Parcelable {

    @SerializedName("name")
    public final String name;
    @SerializedName("address")
    public final String address;
    @SerializedName("status")
    public final String status;
    @SerializedName("available_bikes")
    public final int availableBikes;
    @SerializedName("available_bike_stands")
    public final int availableBikeStands;

    public Field(String name, String address, String status, int availableBikes, int availableBikeStands)
    {
        this.name = name;
        this.address = address;
        this.status = status;
        this.availableBikes = availableBikes;
        this.availableBikeStands = availableBikeStands;
    }

    protected Field(Parcel in) {
        name = in.readString();
        address = in.readString();
        status = in.readString();
        availableBikes = in.readInt();
        availableBikeStands = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(status);
        dest.writeInt(availableBikes);
        dest.writeInt(availableBikeStands);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Field> CREATOR = new Creator<Field>() {
        @Override
        public Field createFromParcel(Parcel in) {
            return new Field(in);
        }

        @Override
        public Field[] newArray(int size) {
            return new Field[size];
        }
    };
}
