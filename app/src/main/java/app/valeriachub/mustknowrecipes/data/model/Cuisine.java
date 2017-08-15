package app.valeriachub.mustknowrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cuisine implements Parcelable{
    private int id;
    private String title;
    private String picture;

    public Cuisine(int id, String title, String picture) {
        this.id = id;
        this.title = title;
        this.picture = picture;
    }

    protected Cuisine(Parcel in) {
        id = in.readInt();
        title = in.readString();
        picture = in.readString();
    }

    public static final Creator<Cuisine> CREATOR = new Creator<Cuisine>() {
        @Override
        public Cuisine createFromParcel(Parcel in) {
            return new Cuisine(in);
        }

        @Override
        public Cuisine[] newArray(int size) {
            return new Cuisine[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(picture);
    }
}
