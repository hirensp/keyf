package keyf.clueless.action.offer;

import keyf.clueless.action.Accusation;
import keyf.clueless.action.Action;
import keyf.clueless.action.offer.OfferAction;

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