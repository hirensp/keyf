package keyf.clueless.action.offer;

import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.data.location.Location;

import java.util.Collections;
import java.util.Set;
import keyf.clueless.action.Action;
import keyf.clueless.action.Move;

/**
 *
 * @author justin
 */
public class OfferMove implements OfferAction
{
    private final Set<Location> possibleLocations;
    
    /**
     * Creates a new OfferMove.
     *
     * @param possibleLocations The set of Locations a player could move.
     */
    public OfferMove(Set<Location> possibleLocations)
    {
        this.possibleLocations = Collections.unmodifiableSet(possibleLocations);
    }

    public Set<Location> getPossibleLocations()
    {
        return possibleLocations;
    }

    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Move;
    }
}