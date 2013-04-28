package keyf.clueless.action;

/**
 * Allows a Player to say that he/she cannot refute a Suggestion.
 *
 * TODO - this is where the name "OfferAction" breaks down. Think of a better
 *        name (such that it makes sense for the child classes as well).
 */
public class PossibleUnableToRefute implements OfferAction
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
