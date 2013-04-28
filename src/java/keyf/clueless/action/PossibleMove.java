package keyf.clueless.action;

import keyf.clueless.data.location.Location;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author justin
 */
public class PossibleMove implements OfferAction
{
    private final Set<Location> possibleLocations;
    
    /**
     * Creates a new PossibleMove.
     *
     * @param possibleLocations The set of Locations a player could move.
     */
    public PossibleMove(Set<Location> possibleLocations)
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