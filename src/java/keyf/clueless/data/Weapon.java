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
}