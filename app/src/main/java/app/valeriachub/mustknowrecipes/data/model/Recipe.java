package app.valeriachub.mustknowrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {
    private int id;
    private String title;
    private String picture;
    private int timeCooking;
    private String cuisine;
    private String type;
    private String description;
    private int idFavourite;

    public Recipe(int id, String title, String picture, int timeCooking, String cuisine, String type, String description, int idFavourite) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.timeCooking = timeCooking;
        this.cuisine = cuisine;
        this.type = type;
        this.description = description;
        this.idFavourite = idFavourite;
    }

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

    public int getTimeCooking() {
        return timeCooking;
    }

    public void setTimeCooking(int timeCooking) {
        this.timeCooking = timeCooking;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdFavourite() {
        return idFavourite;
    }

    public void setIdFavourite(int idFavourite) {
        this.idFavourite = idFavourite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.picture);
        dest.writeInt(this.timeCooking);
        dest.writeString(this.cuisine);
        dest.writeString(this.type);
        dest.writeString(this.description);
        dest.writeInt(this.idFavourite);
    }

    protected Recipe(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.picture = in.readString();
        this.timeCooking = in.readInt();
        this.cuisine = in.readString();
        this.type = in.readString();
        this.description = in.readString();
        this.idFavourite = in.readInt();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}