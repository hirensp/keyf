package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.EndTurn;
import keyf.clueless.action.offer.OfferAction;

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
}