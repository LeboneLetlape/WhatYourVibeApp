package za.co.whatyourvibe.LogicLayer.Data;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.EventTicket;
import za.co.whatyourvibe.LogicLayer.Models.LookupTicket;

public class EventTicketRepo {

    public static List<EventTicket> GetTickets(){
        List<EventTicket> eventTickets = new ArrayList<>();
        return eventTickets;
    }
}
