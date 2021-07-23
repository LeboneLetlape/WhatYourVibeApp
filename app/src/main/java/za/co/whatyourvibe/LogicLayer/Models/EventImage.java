package za.co.whatyourvibe.LogicLayer.Models;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.gson.annotations.SerializedName;

public class EventImage {

    @SerializedName("ID")
    private int ID;
    @SerializedName("EventImageID")
    private int eventImageID;
    @SerializedName("Name")
    private String name;
    @SerializedName("Url")
    private String url;
    @SerializedName("IsMain")
    private boolean isMain;
    @SerializedName("ImageFile")
    private byte[] ImageFile;

    public EventImage(String name, Bitmap bitmap) {
        this.name = name;
        this.bitmap = bitmap;
    }

    public EventImage(String name, byte[] imageFile, Bitmap bitmap) {
        this.name = name;
        ImageFile = imageFile;
        this.bitmap = bitmap;
    }

    @JsonIgnore
    private Bitmap bitmap;

    public EventImage() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEventImageID() {
        return eventImageID;
    }

    public void setEventImageID(int eventImageID) {
        this.eventImageID = eventImageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public byte[] getImageFile() {
        return ImageFile;
    }

    public void setImageFile(byte[] imageFile) {
        ImageFile = imageFile;
    }
}
