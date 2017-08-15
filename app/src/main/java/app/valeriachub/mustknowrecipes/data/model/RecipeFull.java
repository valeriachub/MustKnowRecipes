package app.valeriachub.mustknowrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RecipeFull extends Recipe implements Parcelable{

    private List<Item> itemList;
    private List<Step> stepList;

    public RecipeFull(int id, String title, String picture, int timeCooking, String cuisine, String type, String description, int idFavourite, List<Item> itemList, List<Step> stepList) {
        super(id, title, picture, timeCooking, cuisine, type, description, idFavourite);
        this.itemList = itemList;
        this.stepList = stepList;
    }

    public RecipeFull(Recipe recipe){
        super(recipe.getId(), recipe.getTitle(), recipe.getPicture(), recipe.getTimeCooking(), recipe.getCuisine(), recipe.getType(), recipe.getDescription(), recipe.getIdFavourite());
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(List<Step> stepList) {
        this.stepList = stepList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.itemList);
        dest.writeTypedList(this.stepList);
    }

    protected RecipeFull(Parcel in) {
        super(in);
        this.itemList = in.createTypedArrayList(Item.CREATOR);
        this.stepList = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Creator<RecipeFull> CREATOR = new Creator<RecipeFull>() {
        @Override
        public RecipeFull createFromParcel(Parcel source) {
            return new RecipeFull(source);
        }

        @Override
        public RecipeFull[] newArray(int size) {
            return new RecipeFull[size];
        }
    };
}
