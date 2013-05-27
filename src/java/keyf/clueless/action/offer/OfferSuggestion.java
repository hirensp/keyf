package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.Suggestion;
import keyf.clueless.data.Weapon;
import keyf.clueless.data.location.Room;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author justin
 */
public class OfferSuggestion implements OfferAction
{
    /**
     * The room that the player can use when making a suggestion (his/her
     * current room)
     */
    private final Room possibleRoom;

    public OfferSuggestion(Room possibleRoom)
    {
        this.possibleRoom = possibleRoom;
    }

    public Room getPossibleRoom()
    {
        return possibleRoom;
    }

    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Suggestion;
    }

    /**
     * {
     *     "name": "Suggest",
     *     "options": [ [ suspects ], [ weapons ] ],
     *     "message": "I will suggest:"
     * }
     */
    @Override
    public String getJsonString()
    {
        JSONObject json = new JSONObject();
        json.put("name", "Suggestion");

        json.accumulate("options", possibleRoom.toString());

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

        json.put("message", "I will suggest:");
        json.put("action", "Suggestion");

        return json.toString();
    }

}