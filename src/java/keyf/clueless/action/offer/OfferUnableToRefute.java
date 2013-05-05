package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.UnableToRefute;
import keyf.clueless.action.offer.OfferAction;

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
}