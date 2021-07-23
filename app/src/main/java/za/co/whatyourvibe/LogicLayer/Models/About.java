package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class About implements Serializable {

    @SerializedName("ID")
    private int id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;

    public About() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
