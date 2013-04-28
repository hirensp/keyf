package keyf.clueless.action;

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
