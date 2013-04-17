package keyf.clueless.data.card;

import keyf.clueless.data.location.Room;

/**
 * A card that represents a room.
 *
 * @author Justin
 */
public enum RoomCard implements Card
{
    STUDY(Room.STUDY),
    HALL(Room.HALL),
    LOUNGE(Room.LOUNGE),
    LIBRARY(Room.LIBRARY),
    BILLIARD_ROOM(Room.BILLIARD_ROOM),
    DINING_ROOM(Room.DINING_ROOM),
    CONSERVATORY(Room.CONSERVATORY),
    BALLROOM(Room.BALLROOM),
    KITCHEN(Room.KITCHEN);

    private final Room room;

    private RoomCard(Room room)
    {
        this.room = room;
    }

    @Override
    public Room getItem()
    {
        return room;
    }
}