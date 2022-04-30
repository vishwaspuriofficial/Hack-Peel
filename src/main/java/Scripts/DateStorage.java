package Scripts;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;



public class DateStorage {
    ArrayList<Event> events;

    public DateStorage(ArrayList<Event> _events){
        events = _events;
    }

    public void saveToFile(){

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

    
}
