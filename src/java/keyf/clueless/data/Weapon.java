package keyf.clueless.data;

import keyf.clueless.data.location.Room;

/**
 *
 * @author Justin
 */
public enum Weapon implements Item
{
    KNIFE(Room.KITCHEN),
    CANDLESTICK(Room.DINING_ROOM),
    REVOLVER(Room.STUDY),
    ROPE(Room.BALLROOM),
    LEAD_PIPE(Room.CONSERVATORY),
    WRENCH(Room.LOUNGE);

    private final Room startingRoom;

    private Weapon(Room startingRoom)
    {
        this.startingRoom = startingRoom;
    }

    /**
     * Returns the {@link Room} where the weapon should be at the begining of
     * the game.
     *
     * @return never {@code null}.
     */
    public Room getStartingRoom()
    {
        return startingRoom;
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

        for (Weapon weapon : Weapon.values())
        {
            if (weapon.name().equals(name))
            {
                valid = true;
                break;
            }
        }

        return valid;
    }
}