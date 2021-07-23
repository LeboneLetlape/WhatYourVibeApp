package za.co.whatyourvibe.LogicLayer.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event
{
    @SerializedName("ID")
    private int id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;
    @SerializedName("Location")
    private String location;
    @SerializedName("Lng")
    private double lng;
    @SerializedName("Lat")
    private double lat;
    @SerializedName("FromDate")
    private Date fromDate;
    @SerializedName("ToDate")
    private Date toDate;
    @SerializedName("FromTime")
    private String fromTime;
    @SerializedName("ToTime")
    private String toTime;
    @SerializedName("VideoUrl")
    private String videoUrl;
    @SerializedName("Rating")
    private int rating;
    @SerializedName("Checkin")
    private int checkin;
    @SerializedName("CreatedDate")
    private Date createdDate;

    @SerializedName("EventCategoryID")
    private int eventCategoryID;
    @SerializedName("EventCategory")
    private EventCategory eventCategory;

    @SerializedName("EventTickets")
    private List<EventTicket> eventTickets;

    @SerializedName("EventRestrictions")
    private List<EventRestriction> eventRestrictions;
    @SerializedName("EventImages")
    private List<EventImage> eventImages;
    @SerializedName("EventSocialMedias")
    private List<EventSocialMedia> eventSocialMedia;
    @SerializedName("EventContacts")
    private List<EventContact> eventContacts;



    /*//Single

    public int UserID { get; set; }
            [Ignore]
    public User User { get; set; }
    public int ProvinceID { get; set; }
            [Ignore]
    public Province Province { get; set; }
    public int EventTypeID { get; set; }
            [Ignore]
    public EventType EventType { get; set; }
    public int EventStatusID { get; set; }
            [Ignore]
    public EventStatus EventStatus { get; set; }
    public int EventMainImageID { get; set; }
            [Ignore]
    public EventMainImage EventMainImage { get; set; }
    public int EventCategoryID { get; set; }
            [Ignore]
    public EventCategory EventCategory { get; set; }*/


    public Event() {
        this.eventTickets = new ArrayList<>();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getCheckin() {
        return checkin;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {

        this.eventCategoryID = eventCategory.getId();
        this.eventCategory = eventCategory;
    }

    public List<EventTicket> getEventTickets() {
        return eventTickets;
    }

    public void setEventTickets(List<EventTicket> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public List<EventRestriction> getEventRestrictions() {
        return eventRestrictions;
    }

    public void setEventRestrictions(List<EventRestriction> eventRestrictions) {
        this.eventRestrictions = eventRestrictions;
    }

    public List<EventImage> getEventImages() {
        return eventImages;
    }

    public void setEventImages(List<EventImage> eventImages) {
        this.eventImages = eventImages;
    }

    public List<EventSocialMedia> getEventSocialMedia() {
        return eventSocialMedia;
    }

    public void setEventSocialMedia(List<EventSocialMedia> eventSocialMedia) {
        this.eventSocialMedia = eventSocialMedia;
    }

    public List<EventContact> getEventContacts() {
        return eventContacts;
    }

    public void setEventContacts(List<EventContact> eventContacts) {
        this.eventContacts = eventContacts;
    }

    public int getEventCategoryID() {
        return eventCategoryID;
    }

    public void setEventCategoryID(int eventCategoryID) {
        this.eventCategoryID = eventCategoryID;
    }
}
