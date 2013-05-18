package keyf.clueless.data.location;

import keyf.clueless.data.Suspect;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Represents a hallway. Named by the {@link Room}s that separate them.
 *
 * @author Justin
 */
public enum Hallway implements Location
{
    STUDY_HALL(
            Room.STUDY,
            Room.HALL),

    HALL_LOUNGE(
            Room.HALL,
            Room.LOUNGE),

    STUDY_LIBRARY(
            Room.STUDY,
            Room.LIBRARY),

    HALL_BILLIARD_ROOM(
            Room.HALL,
            Room.BILLIARD_ROOM),

    LOUNGE_DINING_ROOM(
            Room.LOUNGE,
            Room.DINING_ROOM),

    LIBRARY_BILLIARD_ROOM(
            Room.LIBRARY,
            Room.BILLIARD_ROOM),

    BILLIARD_ROOM_DINING_ROOM(
            Room.BILLIARD_ROOM,
            Room.DINING_ROOM),

    LIBRARY_CONSERVATORY(
            Room.LIBRARY,
            Room.CONSERVATORY),

    BILLIARD_ROOM_BALLROOM(
            Room.BILLIARD_ROOM,
            Room.BALLROOM),

    DINING_ROOM_KITCHEN(
            Room.DINING_ROOM,
            Room.KITCHEN),

    CONSERVATORY_BALLROOM(
            Room.CONSERVATORY,
            Room.BALLROOM),

    BALLROOM_KITCHEN(
            Room.BALLROOM,
            Room.KITCHEN);

    /**
     * Note that all hallways only have only Rooms for neighbors.
     */
    private final Set<Room> neighbors;

    /**
     * Create new instance.
     *
     * @param room1 the first room adjacent to this Hallway
     * @param room2 the remaining rooms adjacent to this hallway
     */
    private Hallway(Room room1, Room... room2)
    {
        Set<Room> neighbors = EnumSet.of(room1, room2);

        // Make unmodifiable so that we don't accidentally change it!
        this.neighbors = Collections.unmodifiableSet(neighbors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Room> getNeighbors()
    {
        return neighbors;
    }

    /**
     * Always returns {@code true} (hallways can only hold one {@link Suspect}.
     *
     * @return Always {@code true}
     */
    @Override
    public boolean isSingleOccupancy()
    {
        return true;
    }

    @Override
    public String getDescription()
    {
        return this.name();
    }

    /**
     * Returns {@code true} if this {@code enum} contains a value with the given
     * {@code name}
     *
     * @param itemName The name of the {@code enum}.
     *
     * @return {@code true} if the {@code enum} contains a value with the given
     *     {@code name}, {@code false} otherwise.
     */
    public static boolean isValid(String name)
    {
        boolean valid = false;

        for (Hallway hallway : Hallway.values())
        {
            if (hallway.name().equals(name))
            {
                valid = true;
                break;
            }
        }

        return valid;
    }
}