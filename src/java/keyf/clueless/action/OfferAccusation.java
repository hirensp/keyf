package keyf.clueless.action;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import keyf.clueless.data.location.Room;

/**
 *
 * @author justin
 */
public class OfferAccusation extends OfferOffer
{
    private final Set<Room> possibleRooms
            = Collections.unmodifiableSet(EnumSet.allOf(Room.class));

    public Set<Room> getPossibleRooms()
    {
        return possibleRooms;
    }

    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Accusation;
    }
}