package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.UnableToRefute;
import org.json.JSONObject;

/**
 * Allows a Player to say that he/she cannot refute a Suggestion.
 */
public class OfferUnableToRefute implements OfferAction
{
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof UnableToRefute;
    }

    /**
     * {
     *     "name": "Unable to Refute"
     * }
     */
    @Override
    public String getJsonString()
    {
        JSONObject json = new JSONObject();
        json.put("name", "Unable to Refute");
        json.put("action", "UnableToRefute");
        return json.toString();
    }
}