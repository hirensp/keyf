package keyf.clueless.data;

import keyf.clueless.data.location.Room;

/**
 *
 * @author Justin
 */
public enum Weapon implements Item
{
    KNIFE(Room.KITCHEN, "KNIFE"),
    CANDLESTICK(Room.DINING_ROOM, "CANDLESTICK"),
    REVOLVER(Room.STUDY, "REVOLVER"),
    ROPE(Room.BALLROOM, "ROPE"),
    LEAD_PIPE(Room.CONSERVATORY, "LEAD_PIPE"),
    WRENCH(Room.LOUNGE, "WRENCH");

    private final Room startingRoom;
    private final String description;

    private Weapon(Room startingRoom, String description)
    {
        this.startingRoom = startingRoom;
        this.description = description;
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
    public String getDescription()
    {
        return description;
    }
}
