package keyf.clueless.action.offer;

import keyf.clueless.action.Accusation;
import keyf.clueless.action.Action;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author justin
 */
public class OfferAccusation implements OfferAction
{
    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Accusation;
    }

    /**
     * {
     *     "name": "Accuse",
     *     "options": [ [ All Suspects ], [ All Weapons ], [ All Rooms ] ],
     *     "message": " I will accuse: "
     * }
     *
     * @return the above
     */
    @Override
    public String getJsonString()
    {
  
        JSONObject json = new JSONObject();
        json.put("name", "Accuse");
        
        for(Suspect possSus : Suspect.values())
        {
            json.put("Suspect options", new JSONArray().put(new JSONArray(possSus)));
        }
        
        for(Weapon possWeap : Weapon.values())
        {
            json.put("Weapon options", new JSONArray().put(new JSONArray(possWeap)));
        }
        
        for(Room possRoom : Room.values())
        {
            json.put("Room options", new JSONArray().put(new JSONArray(possRoom)));
        }
        json.put("message", "I will Accuse:");
        return json.toString();
    }
}