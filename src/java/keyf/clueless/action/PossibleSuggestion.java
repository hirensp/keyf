package keyf.clueless.action;

import keyf.clueless.data.location.Room;

/**
 *
 * @author justin
 */
public class PossibleSuggestion extends PossibleOffer
{
    /**
     * The room that the player can use when making a suggestion (his/her
     * current room)
     */
    private final Room possibleRoom;

    public PossibleSuggestion(Room possibleRoom)
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
}