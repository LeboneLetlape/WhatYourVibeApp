package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

public class Province
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Name")
    private String name;

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
