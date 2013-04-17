package keyf.clueless.data.location;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import keyf.clueless.data.Item;

/**
 * Represents a room.
 *
 * @author Justin
 */
public enum Room implements Location, Item
{
    STUDY,
    HALL,
    LOUNGE,
    LIBRARY,
    BILLIARD_ROOM,
    DINING_ROOM,
    CONSERVATORY,
    BALLROOM,
    KITCHEN;

    // Neighbors have to be declaired here instead of as a Constructor argument
    // because of illigal foward referencing (some Rooms have other Rooms as
    // neighbors).
    static
    {
        STUDY.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.STUDY_LIBRARY,
                        Hallway.STUDY_HALL,
                        KITCHEN)));

        HALL.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.STUDY_HALL,
                        Hallway.HALL_BILLIARD_ROOM,
                        Hallway.HALL_LOUNGE)));

        LOUNGE.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.HALL_LOUNGE,
                        Hallway.LOUNGE_DINING_ROOM,
                        CONSERVATORY)));

        LIBRARY.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.STUDY_LIBRARY,
                        Hallway.LIBRARY_BILLIARD_ROOM,
                        Hallway.LIBRARY_CONSERVATORY)));

        BILLIARD_ROOM.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.LIBRARY_BILLIARD_ROOM,
                        Hallway.HALL_BILLIARD_ROOM,
                        Hallway.BILLIARD_ROOM_DINING_ROOM,
                        Hallway.BILLIARD_ROOM_BALLROOM)));

        DINING_ROOM.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.BILLIARD_ROOM_DINING_ROOM,
                        Hallway.LOUNGE_DINING_ROOM,
                        Hallway.DINING_ROOM_KITCHEN)));

        CONSERVATORY.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.LIBRARY_CONSERVATORY,
                        Hallway.CONSERVATORY_BALLROOM,
                        LOUNGE)));
        BALLROOM.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.CONSERVATORY_BALLROOM,
                        Hallway.BILLIARD_ROOM_BALLROOM,
                        Hallway.BALLROOM_KITCHEN)));

        KITCHEN.neighbors = Collections.<Location>unmodifiableSet(
                new HashSet(Arrays.asList(
                        Hallway.BALLROOM_KITCHEN,
                        Hallway.DINING_ROOM_KITCHEN,
                        STUDY)));
    }

    /**
     * Not final because this has to be set in the static block.
     */
    private Set<Location> neighbors;

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Location> getNeighbors()
    {
        return neighbors;
    }

    /**
     * Always returns {@code false} (rooms can hold any number of {@link
     * Suspect}s.
     *
     * @return Always {@code false}
     */
    @Override
    public boolean isSingleOccupancy()
    {
        return false;
    }
}