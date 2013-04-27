package keyf.clueless.data.location;

import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;

import java.util.Set;

/**
 * Represents a place where a {@link Suspect} or {@link Weapon} can be located
 * (a {@link Room} or {@link Hallway}, for example).
 *
 * @author Justin
 */
public interface Location
{
    /**
     * Returns other {@link Location}s that are reachable from this {@link
     * Location}. (Note that this class has no knowledge of {@link Suspect}
     * locations.)
     *
     * @return {@link Location}s that are reachable from this {@link Location}.
     */
    public Set<? extends Location> getNeighbors();

    /**
     * Returns whether or not this location can hold one occupant ({@link
     * Suspect}). For example, a {@link Room} can hold any number of occupants,
     * so returns {@code false}, while a {@link Hallway} supports only a single
     * occupant, so returns {@code true}.
     *
     * @return {@code true} if this room can only hold a single occupant, {@code
     *     false} otherwise.
     */
    public boolean isSingleOccupancy();
}