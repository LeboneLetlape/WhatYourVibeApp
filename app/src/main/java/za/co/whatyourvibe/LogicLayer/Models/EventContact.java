package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

public class EventContact {

    @SerializedName("ID")
    public int id;
    @SerializedName("EventID")
    public int eventID;
    @SerializedName("ContactID")
    public int contactID;
    @SerializedName("Contact")
    public Contact contact;
    @SerializedName("IsActive")
    public boolean isActive;
    @SerializedName("Info")
    public String info;

    public EventContact() {
    }

    public EventContact(int contactID, String contact) {
        this.contactID = contactID;
        this.contact = new Contact(contactID,contact);
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

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
