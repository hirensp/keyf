package keyf.clueless.action;

/**
 *
 * @author justin
 */
public class OfferAccusation implements OfferAction
{
    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Accusation;
    }
}