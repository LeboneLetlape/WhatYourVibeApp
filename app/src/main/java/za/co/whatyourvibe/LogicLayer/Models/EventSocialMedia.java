package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

public class EventSocialMedia {

    @SerializedName("ID")
    public int id;
    @SerializedName("EventID")
    public int eventID;
    @SerializedName("SocialMediaID")
    public int socialMediaID;
    @SerializedName("SocialMedia")
    public SocialMedia socialMedia;
    @SerializedName("Link")
    public String link;

    public EventSocialMedia() {
    }


    public EventSocialMedia(int socialMediaID, String _Name) {
        this.socialMediaID = socialMediaID;
        this.socialMedia = new SocialMedia(socialMediaID,_Name);
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

    public int getSocialMediaID() {
        return socialMediaID;
    }

    public void setSocialMediaID(int socialMediaID) {
        this.socialMediaID = socialMediaID;
    }

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
