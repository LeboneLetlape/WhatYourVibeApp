package za.co.whatyourvibe.LogicLayer.Models;


import com.google.gson.annotations.SerializedName;

public class EventTicket {

    @SerializedName("ID")
    private int id;
    @SerializedName("TicketID")
    private int ticketID;
    @SerializedName("Ticket")
    private Ticket ticket;
    @SerializedName("Price")
    private double price;
    @SerializedName("IsActive")
    private boolean isActive;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
