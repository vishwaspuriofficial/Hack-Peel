package Scripts;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class Event {
    String title;

    String startTime;
    String endTime;

    ArrayList<String> repeatDate;
    LinkedList<Event> linkedEvents;

    int stressLevel;

    public Event(String _title, String _startTime, String _endTime, ArrayList<String> _repeatDate, LinkedList<Event> _linkedEvents, int _stressLevel){
        title = _title;
        startTime = _startTime;
        endTime = _endTime;
        repeatDate = _repeatDate;
        linkedEvents = _linkedEvents;
        stressLevel = _stressLevel;
    }

    public void saveEventToText(){

    }

}
