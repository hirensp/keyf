package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.EndTurn;
import org.json.JSONObject;

/**
 * Allows a Player to end his/her turn.
 *
 * @author justin
 */
public class OfferEndTurn implements OfferAction
{
    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof EndTurn;
    }

    /**
     * {
     *     "name": "End Turn"
     * }
     * @return
     */
    @Override
    public String getJsonString()
    {
        JSONObject json = new JSONObject();
        json.put("name", "End Turn");
        json.put("action", "EndTurn");
        return json.toString();
    }
}