package keyf.clueless.data.location;

import keyf.clueless.data.Item;
import keyf.clueless.data.Suspect;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a room.
 *
 * @author Justin
 */
public enum Room implements Location, Item
{
    // Neighbors have to be declaired here instead of as a Constructor argument
    // because of illigal foward referencing (some Rooms have other Rooms as
    // neighbors) and because Rooms and Hallways reference each other, creating
    // a circular reference.

    STUDY
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.STUDY_LIBRARY,
                        Hallway.STUDY_HALL,
                        KITCHEN)));
        }
    },

    HALL
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                    new HashSet<Location>(Arrays.asList(
                            Hallway.STUDY_HALL,
                            Hallway.HALL_BILLIARD_ROOM,
                            Hallway.HALL_LOUNGE)));
        }
    },

    LOUNGE
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.HALL_LOUNGE,
                        Hallway.LOUNGE_DINING_ROOM,
                        CONSERVATORY)));
        }
    },

    LIBRARY
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.STUDY_LIBRARY,
                        Hallway.LIBRARY_BILLIARD_ROOM,
                        Hallway.LIBRARY_CONSERVATORY)));
        }
    },

    BILLIARD_ROOM
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.LIBRARY_BILLIARD_ROOM,
                        Hallway.HALL_BILLIARD_ROOM,
                        Hallway.BILLIARD_ROOM_DINING_ROOM,
                        Hallway.BILLIARD_ROOM_BALLROOM)));
        }
    },

    DINING_ROOM
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.BILLIARD_ROOM_DINING_ROOM,
                        Hallway.LOUNGE_DINING_ROOM,
                        Hallway.DINING_ROOM_KITCHEN)));
        }
    },

    CONSERVATORY
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.LIBRARY_CONSERVATORY,
                        Hallway.CONSERVATORY_BALLROOM,
                        LOUNGE)));
        }
    },

    BALLROOM
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.CONSERVATORY_BALLROOM,
                        Hallway.BILLIARD_ROOM_BALLROOM,
                        Hallway.BALLROOM_KITCHEN)));
        }
    },

    KITCHEN
    {
        @Override
        public Set<Location> getNeighbors()
        {
            return Collections.unmodifiableSet(
                new HashSet<Location>(Arrays.asList(
                        Hallway.BALLROOM_KITCHEN,
                        Hallway.DINING_ROOM_KITCHEN,
                        STUDY)));
        }
    };

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

        for (Room room : Room.values())
        {
            if (room.name().equals(name))
            {
                valid = true;
                break;
            }
        }

        return valid;
    }
}