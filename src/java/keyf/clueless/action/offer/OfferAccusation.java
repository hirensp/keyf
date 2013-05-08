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

        JSONArray jsonSuspects = new JSONArray();
        for(Suspect suspect : Suspect.values())
        {
            jsonSuspects.put(suspect.toString());
        }
        json.accumulate("options", jsonSuspects);

        JSONArray jsonWeapons = new JSONArray();
        for(Weapon weapon : Weapon.values())
        {
            jsonWeapons.put(weapon.toString());
        }
        json.accumulate("options", jsonWeapons);

        JSONArray jsonRooms = new JSONArray();
        for(Room room : Room.values())
        {
            jsonRooms.put(room.toString());
        }
        json.accumulate("options", jsonRooms);

        json.put("message", "I will Accuse:");
        json.put("action", "OfferAccusation");

        return json.toString();
    }
}