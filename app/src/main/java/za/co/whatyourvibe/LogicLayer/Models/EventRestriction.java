package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

public class EventRestriction {

    @SerializedName("ID")
    public int id;
    @SerializedName("EventID")
    public int eventID;
    @SerializedName("RestrictionID")
    public int restrictionID;
    @SerializedName("Restrictions")
    public Restriction restrictions;
    @SerializedName("IsActive")
    public boolean isActive;
    @SerializedName("Info")
    public String info;

    public EventRestriction() {
    }

    public EventRestriction(int restrictionID, String Name) {
        this.restrictionID = restrictionID;
        this.restrictions = new Restriction(restrictionID,Name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getRestrictionID() {
        return restrictionID;
    }

    public void setRestrictionID(int restrictionID) {
        this.restrictionID = restrictionID;
    }

    public Restriction getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(Restriction restrictions) {
        this.restrictions = restrictions;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
