package keyf.clueless;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import keyf.clueless.data.location.Hallway;
import keyf.clueless.data.location.Location;
import keyf.clueless.data.location.Room;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;

/**
 * Represents the board, maintains the location of the {@link Suspect}s and
 * {@link Weapon}s.
 *
 * @author Justin
 */
public class Board
{
    /**
     * Maintains the location of each {@link Suspect} on this Board.
     */
    private final Map<Suspect, Location> suspectLocations
            = new EnumMap<Suspect, Location>(Suspect.class);

    /**
     * Maintains the location of each {@link Weapon} on this Board.
     */
    private final Map<Weapon, Room> weaponRooms
            = new EnumMap<Weapon, Room>(Weapon.class);

    public Board()
    {
        // TODO fill in the suspectLocations and weaponLocations
    }

    /**
     * Return the {@link Location} of the {@code suspect} on this Board.
     *
     * @param suspect the suspect whose location is desired.
     *
     * @return never {@code null}
     */
    public Location getLocation(Suspect suspect)
    {
        return suspectLocations.get(suspect);
    }

    /**
     * Returns the room in which the {@code weapon} is currently located.
     *
     * @param weapon the weapon whose Room is desired
     *
     * @return never {@code null}
     */
    public Room getRoom(Weapon weapon)
    {
        return weaponRooms.get(weapon);
    }

    /**
     * Return the {@link Location}s where the suspect is able to move relative
     * to his current location and the location of other {@link Suspect}s (a
     * {@link Suspect} cannot, for example, move into a {@link Hallway} occupied
     * by another {@link Suspect}).
     *
     * @param suspect the suspect whose movable locations is desired
     *
     * @return never {@code null} (may be empty)
     */
    public Set<Location> getAvailableLocations(Suspect suspect)
    {
        Set<Location> availableLocations = new HashSet<Location>(
                suspectLocations.get(suspect).getNeighbors());

        // Subtract the Hallways of all other Suspects (current suspect doesn't
        // matter because they can't move back to their current location...)
        for (Location suspectLocation : suspectLocations.values())
        {
            // Remove Locations that can only hold one Suspect (i.e., Hallways).
            if (suspectLocation.isSingleOccupancy())
            {
                availableLocations.remove(suspectLocation);
            }
        }

        return availableLocations;
    }

    /**
     * Moves the {@code suspect} to the {@code location}; if the suspect cannot
     * be moved to that location (i.e., {@link #getAvailableLocations does not
     * contain {@code location}), a {@link RuntimeException} is thrown.
     *
     * @param suspect The suspect to move
     * @param location The location where the suspect is to be moved
     *
     * @throws RuntimeException if the {@code suspect} is not able to move to
     *     the {@code location}
     */
    public void setLocation(Suspect suspect, Location location)
    {
        if (getAvailableLocations(suspect).contains(location))
        {
            suspectLocations.put(suspect, location);
        }
        else
        {
            throw new RuntimeException(
                    "Illegal call to setLocation(Suspect, Location");
        }
    }

    /**
     * Moves the {@code weapon} to the {@code room}. A {@link Weapon} can be
     * moved to any {@link Room}.
     *
     * @param weapon The weapon to move
     * @param room The room to move the {@code weapon} to
     */
    public void setRoom(Weapon weapon, Room room)
    {
        weaponRooms.put(weapon, room);
    }
}