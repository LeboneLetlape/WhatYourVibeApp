package za.co.whatyourvibe.LogicLayer.Data;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.LookupTicket;

public class LookupTicketRepo {
    public static List<LookupTicket> GetTicketLookups()
    {
        List<LookupTicket> lookupTickets = new ArrayList<>();
        lookupTickets.add(new LookupTicket(1,"Single"));
        lookupTickets.add(new LookupTicket(2,"Double"));
        lookupTickets.add(new LookupTicket(3,"VIP"));
        lookupTickets.add(new LookupTicket(4,"VVIP"));
        lookupTickets.add(new LookupTicket(5,"VVVIP"));
        return lookupTickets;
    }
}
