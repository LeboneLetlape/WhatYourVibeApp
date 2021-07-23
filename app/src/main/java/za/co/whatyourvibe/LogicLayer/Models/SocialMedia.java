package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

public class SocialMedia {

    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;

    public SocialMedia() {
    }

    public SocialMedia(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
