package Scripts;

import Execution.Main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.io.File;
import java.io.FileWriter;


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



    public static void addEventToDate(LinkedList<Event> _event) throws ParseException {
        HashMap<String, LinkedList<Event>> plannedDatesData = Main.getPlannedDatesData();
        plannedDatesData.put(_event.get(0).getDate(), _event);
    }

    public static void deleteEventAtDate(String date, LinkedList<Event> _event){
        HashMap<String, LinkedList<Event>> plannedDatesData = Main.getPlannedDatesData();
        plannedDatesData.remove(date,_event);
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

    public static LinkedList<Event>[] getSuggestions(String _date, Event _event) throws CloneNotSupportedException {
        LinkedList<Event> mainEvents = Main.getPlannedDatesData().get(_date);

        //Algorithm part
        ArrayList<Float> startTimeLst = new ArrayList<>();
        ArrayList<Float> availableTimeSlots = new ArrayList<>();

        //Getting the dates from string to float, and storing all these busy slots to start time lst
        for(Event event : mainEvents){
            String[] split = event.startTime.split(":");
            float hour = Float.parseFloat(split[0]);
            float minute = Float.parseFloat((split[1]))/10;
            if(minute == 0.3){
                minute += 0.2;
            }
            float time = hour+minute;
            startTimeLst.add(time);
        }
        Collections.sort(startTimeLst);


        //Find all open timeslots
        float currentTime = 0.0f; //float flaw

        //Very high potential to be very bad (just smth in case I can't find a better solution)
        for(int i = 0; i < startTimeLst.size();){
            if(currentTime == startTimeLst.get(i)){
                i++;
            }
            else{
                availableTimeSlots.add(currentTime);
            }
            currentTime += 0.50;

        }

        //preparing the list of possible solutions
        LinkedList<Event>[] possibleSolutions = new LinkedList[2];
        Arrays.fill(possibleSolutions,new LinkedList<Event>());
        ArrayList<Float> maybeTimeSlots = new ArrayList<Float>();

        //Get Possible solutions from list of open timeslots, *Get them 1-4 hours away from each other* (if events are static)
        int space = 0;
        int solutionIndex = 0;
        for(int i = 0; i < availableTimeSlots.size(); i++){
            if(solutionIndex == 2){
                break;
            }

            if(space == 0){

                Event solEvent = (Event) _event.clone();
                float newStartTime = availableTimeSlots.get(i);
                float newEndTime;
                if(newStartTime - (newStartTime-0.5f) == 0.5){
                    newStartTime -= 0.20;
                }
                newEndTime = newStartTime + 0.30f;
                solEvent.startTime = String.valueOf(newStartTime);
                solEvent.endTime = String.valueOf(newEndTime);

                possibleSolutions[solutionIndex].add(solEvent);

                solutionIndex += 1;
                space = 5;
            }
            else{
                space -= 5;
                maybeTimeSlots.add(availableTimeSlots.get(i));
            }
        }

        if (solutionIndex != 2){
            for(int i = 0; i < solutionIndex; i++){
                Event solEvent = (Event) _event.clone();
                float newStartTime = maybeTimeSlots.get(i);
                float newEndTime = 0;
                if(newStartTime - (newStartTime-0.5f) == 0.5){
                    newStartTime -= 0.20;
                }
                newEndTime = newStartTime+0.30f;

                solEvent.startTime = String.valueOf(newStartTime);
                solEvent.endTime = String.valueOf(newEndTime);

                possibleSolutions[solutionIndex].add(solEvent);
            }
        }

        return possibleSolutions;
    }


    
}
