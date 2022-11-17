package org.example;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.*;

import static spark.Spark.*;

public class HospitalAPI {
    public static void main(String[] args) {
        HashMap <Integer, Event> db = new HashMap<Integer,Event>();
        db.put(1, new Event(1,9,10,2022,1,1));
        db.put(2, new Event(2,6,10,2022,2,2));
        port(8080);

        // Method to get the current date
        get("/currentdate", (req, res)->{
            res.type("application/json");
            return new JSONObject().put("date", Calendar.getInstance().get(Calendar.DATE));
        });

        //Method to get the current day
        get("/currentday", (req, res)->{
            res.type("application/json");
            return new JSONObject().put("day", Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
        });

        // Method to get the current month
        get("/currentmonth", (req, res)->{
            res.type("application/json");
            return new JSONObject().put("month", Calendar.getInstance().get(Calendar.MONTH));
        });

        //Method to get the current year
        get("/currentyear", (req, res)->{
            res.type("application/json");
            return new JSONObject().put("year", Calendar.getInstance().get(Calendar.YEAR));
        });

        //Method to add an event
        post("/event", (req, res) -> {
            res.type("application/json");
            JSONObject request = new JSONObject(req.body());
            Event newEvent = new Event(request.getInt("EventID"),request.getInt("patientId"),request.getInt("doctorId"),request.getInt("date"),request.getInt("month"),request.getInt("year"));
            db.put(newEvent.eventId, newEvent);
            res.status(201);
            return new JSONObject().put("success", "Event added!");
        });

        //Method to update an event
        put("/event/:eventID", (req, res) -> {
            res.type("application/json");
            JSONObject request = new JSONObject(req.body());
            Event updatedEvent = new Event(request.getInt("EventID"),request.getInt("patientId"),request.getInt("doctorId"),request.getInt("date"),request.getInt("month"),request.getInt("year"));
            db.put(Integer.valueOf(req.params("eventID")), updatedEvent);
            res.status(200);
            return new JSONObject().put("success", "Updated the Event!");
        });

        //Method to delete an event
        delete("/event/:eventID", (req, res) -> {
            res.type("application/json");
            db.remove(Integer.valueOf(req.params("eventID")));
            res.status(200);
            return new JSONObject().put("success", "Deleted the Event!");
        });

        //Method to get all events on particular date
        get("/event", (req, res)->{
            res.type("application/json");
            ArrayList<Event> events = new ArrayList<Event>();
            db.get(Integer.valueOf(req.queryParams("date")));
            Iterator dbIterator = db.entrySet().iterator();
            while (dbIterator.hasNext()) {
                Map.Entry mapElement
                        = (Map.Entry)dbIterator.next();
                Event event = (Event)mapElement.getValue() ;
                if((event.date == Integer.valueOf(req.queryParams("date"))) && (event.month == Integer.valueOf(req.queryParams("month"))) && (event.year == Integer.valueOf(req.queryParams("year")))) {
                    events.add(event);
                }
            }
            String json = new Gson().toJson(events);
            return new JSONObject().put("eventlist", json);
        });
    }
}