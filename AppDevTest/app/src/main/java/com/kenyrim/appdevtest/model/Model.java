package com.kenyrim.appdevtest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {
    private String title, url;

    private Model(Parcel in) {
        title = in.readString();
        url = in.readString();
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel,int i) {
        parcel.writeString(title);
        parcel.writeString(url);
    }
}
