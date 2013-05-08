package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.EndTurn;

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
        return "{\"name\": \"End Turn\"}";
    }
}