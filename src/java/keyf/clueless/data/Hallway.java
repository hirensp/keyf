package keyf.clueless.data;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

/**
 * Represents a hallway.
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

    // TODO add initial starting locations of players that link to hallways (but
    // no hallway links back).

    /**
     * Note that all hallways only have only Rooms for neighbors.
     */
    private final Set<Room> neighbors;

    /**
     * Create new instance.
     *
     * @param neighbors Only a List to make it easier to construct
     */
    private Hallway(Room room1, Room room2)
    {
        Set<Room> neighbors = EnumSet.<Room>of(room1, room2);

        // Make unmodifiable so that we don't accidentally change it!
        this.neighbors = Collections.unmodifiableSet(neighbors);
    }

    @Override
    public Set<Room> getNeighbors()
    {
        return neighbors;
    }
}