package com.focusit.jsflight.player.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Sorted list of recorded events
 * 
 * @author Denis V. Kirpichenkov
 *
 */
public class Events
{
    private static final String TIMESTAMP = "timestamp";
    private List<JSONObject> events = new ArrayList<>();

    public List<JSONObject> getEvents()
    {
        return events;
    }

    public List<JSONObject> parse(List<String> content)
    {
        JSONArray rawevents;
        Set<JSONObject> temp = new HashSet<>();
        for (String line : content)
        {
            rawevents = new JSONArray(line);
            for (int i = 0; i < rawevents.length(); i++)
            {
                String event = rawevents.get(i).toString();
                if (!event.contains("flight-cp"))
                {
                    temp.add(new JSONObject(event));
                }
            }
        }
        events.addAll(temp);
        sortEvents();
        return events;
    }

    public List<JSONObject> parse(String content)
    {
        JSONArray rawevents;
        Set<JSONObject> temp = new HashSet<>();
        rawevents = new JSONArray(content);
        for (int i = 0; i < rawevents.length(); i++)
        {
            String event = rawevents.get(i).toString();
            if (!event.contains("flight-cp"))
            {
                temp.add(new JSONObject(event));
            }
        }
        events.addAll(temp);
        sortEvents();
        return events;
    }

    private void sortEvents()
    {
        Collections.sort(events, new Comparator<JSONObject>()
        {
            @Override
            public int compare(JSONObject o1, JSONObject o2)
            {
                return ((Long)o1.getLong(TIMESTAMP)).compareTo(o2.getLong(TIMESTAMP));
            }
        });
    }
}
