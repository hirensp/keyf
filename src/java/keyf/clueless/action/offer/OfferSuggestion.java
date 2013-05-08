package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.action.Suggestion;
import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.data.location.Room;

/**
 *
 * @author justin
 */
public class OfferSuggestion implements OfferAction
{
    /**
     * The room that the player can use when making a suggestion (his/her
     * current room)
     */
    private final Room possibleRoom;

    public OfferSuggestion(Room possibleRoom)
    {
        this.possibleRoom = possibleRoom;
    }

    public Room getPossibleRoom()
    {
        return possibleRoom;
    }

    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Suggestion;
    }

    @Override
    public String getJsonString()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}