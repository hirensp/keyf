package keyf.clueless.action;

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