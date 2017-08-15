package app.valeriachub.mustknowrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
    private int id;
    private int number;
    private String text;
    private String picture;

    public Step(int id, int number, String text, String picture) {
        this.id = id;
        this.number = number;
        this.text = text;
        this.picture = picture;
    }

    protected Step(Parcel in) {
        id = in.readInt();
        number = in.readInt();
        text = in.readString();
        picture = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(number);
        dest.writeString(text);
        dest.writeString(picture);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
