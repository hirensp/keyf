package keyf.clueless.action.offer;

import keyf.clueless.action.Accusation;
import keyf.clueless.action.Action;

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

    /**
     * {
     *     "name": "Accuse",
     *     "options": [ [ All Suspects ], [ All Weapons ], [ All Rooms ] ],
     *     "message": " I will accuse: "
     * }
     *
     * @return the above
     */
    @Override
    public String getJsonString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}