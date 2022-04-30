package Scripts;

import Execution.Main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;


public class DateStorage {
    ArrayList<Event> events;

    public DateStorage(ArrayList<Event> _events){
        events = _events;
    }


    public ArrayList<Event> getEvents(){
        ArrayList<Event> _event = new ArrayList<>();

        Event curEvent = events.get(0);
        for(int i = 0; i < events.size()-1; i++){
            if(curEvent.title.equals(events.get(i+1).title)){ //If Adj is equal, add the time
                curEvent.timeInterval += 30;
            }
            else{
                _event.add(curEvent);
                curEvent = events.get(i+1);
            }
        }

        return _event;

    }



    public void addEventToDate(String date, LinkedList<Event> _event){
        HashMap<String, LinkedList<Event>> plannedDatesData = Main.getPlannedDatesData();
        plannedDatesData.put(date, _event);
    }

    public void deleteEventAtDate(String date, String title){
        HashMap<String, LinkedList<Event>> plannedDatesData = Main.getPlannedDatesData();
        plannedDatesData.remove(date,title);
    }

    public int getEventDay(LocalDate date){
        int eventDay = 0;
        switch(date.getDayOfWeek().toString()){
            case "MONDAY":
                eventDay = 1;
                break;
            case "TUESDAY":
                eventDay = 2;
                break;
            case "WEDNESDAY":
                eventDay = 3;
                break;
            case "THURSDAY":
                eventDay = 4;
                break;
            case "FRIDAY":
                eventDay = 5;
                break;
            case "SATURDAY":
                eventDay = 6;
                break;
            case "SUNDAY":
                eventDay = 7;
                break;
        }
        return eventDay;
    }

    public LinkedList<Event> getMerge(String _date){

        HashMap<String, LinkedList<Event>> plannedDatesData = Main.getPlannedDatesData();
        LinkedList<Event> mainEvents = (LinkedList<Event>) plannedDatesData.get(_date).clone();
        LinkedList<Event> repeatedEvents = Main.getRepeatingEvents();

        String[] splitted = _date.split("/");
        int day = Integer.parseInt(splitted[0]);
        int month = Integer.parseInt(splitted[1]);
        int year = Integer.parseInt(splitted[2]);

        LocalDate date = LocalDate.of(year, month, day);

        int eventDay = getEventDay(date);

        //Merge repeated and main events
        for(Event event : repeatedEvents) {
            for (String repeatDay : event.repeatDate){
                if(Integer.parseInt(repeatDay) == eventDay)//Check to see if main events list of the date chosen requires the merge of repeated events
                {
                    mainEvents.add(event); //Adds
                }
            }
        }
        return mainEvents;
    }

    public LinkedList<Event>[] getSuggestions(String _date, Event _event){
        getMerge(_date);
        //Algorithm part

        ArrayList<Float> x = new ArrayList<>();
        //for(Event event : mainEvents){

          //  x.add()
        //}

        return null;
    }

    
}
