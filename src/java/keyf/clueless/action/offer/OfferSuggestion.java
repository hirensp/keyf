package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.Suggestion;
import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.data.Suspect;
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
        json.put("Room options", possibleRoom);
        
        for(Suspect possSus : Suspect.values())
        {
            json.put("Suspect options", new JSONArray().put(new JSONArray(possSus)));
        }
        
        for(Weapon possWeap : Weapon.values())
        {
            json.put("Weapon options", new JSONArray().put(new JSONArray(possWeap)));
        }
        
        json.put("message", "I will suggest:");
        return json.toString();
    }
    
}