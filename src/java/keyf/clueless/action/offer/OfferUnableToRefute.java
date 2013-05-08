package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.UnableToRefute;

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
        return "{ \"name\": \"Unable to Refute\"";
    }
}