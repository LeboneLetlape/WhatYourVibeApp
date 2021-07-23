package za.co.whatyourvibe.LogicLayer.Data;

import java.util.ArrayList;
import java.util.List;

import za.co.whatyourvibe.LogicLayer.Models.Event;

public class EventRepo {

    public static List<Event> GetEvents()
    {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());
        return events;
    }
}
