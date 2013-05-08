package keyf.clueless.action.offer;

import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.data.location.Location;

import java.util.Collections;
import java.util.Set;
import keyf.clueless.action.Action;
import keyf.clueless.action.Move;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author justin
 */
public class OfferMove implements OfferAction
{
    private final Set<Location> possibleLocations;

    /**
     * Creates a new OfferMove.
     *
     * @param possibleLocations The set of Locations a player could move.
     */
    public OfferMove(Set<Location> possibleLocations)
    {
        this.possibleLocations = Collections.unmodifiableSet(possibleLocations);
    }

    public Set<Location> getPossibleLocations()
    {
        return possibleLocations;
    }

    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Move;
    }

    /**
     * {
     *     "name": "Move",
     *     "options": [[ -set of places to move- ]],
     *     "message": "I will move to: "
     * }
     */
    @Override
    public String getJsonString()
    {
        JSONObject json = new JSONObject();
        json.put("name", "Move");
        for (Location location : possibleLocations)
        {
            json.put("options", new JSONArray().put(new JSONArray(possibleLocations)));
        }
        json.put("message", "I will move to:");
        return json.toString();
    }
}