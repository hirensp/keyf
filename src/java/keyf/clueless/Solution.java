package keyf.clueless;

import keyf.clueless.data.card.RoomCard;
import keyf.clueless.data.card.SuspectCard;
import keyf.clueless.data.card.WeaponCard;

/**
 * The "envelope" that contains the solution to the murder: the particular
 * {@link SuspectCard suspect}, {@link WeaponCard weapon} and {@link RoomCard
 * room}.
 * 
 * @author justin
 */
public class Solution
{
    private final SuspectCard muderer;
    private final WeaponCard weapon;
    private final RoomCard room;

    /**
     * Creates a new solution.
     *
     * @param muderer The Suspect that committed the murder
     * @param weapon The weapon the murderer used
     * @param room The room he done it in!
     */
    public Solution(SuspectCard muderer,
                    WeaponCard weapon,
                    RoomCard room)
    {
        this.muderer = muderer;
        this.weapon = weapon;
        this.room = room;
    }

    /**
     * Returns the suspect who committed the murder
     *
     * @return never {@code null}
     */
    public SuspectCard getMuderer()
    {
        return muderer;
    }

    /**
     * Returns the weapon used to commit the murder.
     *
     * @return never {@code null}
     */
    public WeaponCard getWeapon()
    {
        return weapon;
    }

    /**
     * Return the room the murder was committed in.
     *
     * @return never {@code null}
     */
    public RoomCard getRoom()
    {
        return room;
    }

    /**
     * Compares to {@link Solution}s, returning equal if and only if the {@link
     * #getMurderer() muderer}, {@link #getWeapon() weapon}, and {@link
     * #getRoom() room} of this instance and the {@code obj} are equal.
     *
     * @param obj The object to test for equality against this instance.
     *
     * @return {@code true} of these two objects are equal, {@code false}
     *     otherwise.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean equal = false;

        if (obj instanceof Solution)
        {
            Solution that = (Solution) obj;

            equal = this.muderer == that.muderer
                    && this.weapon == that.weapon
                    && this.room == that.room;
        }

        return equal;
    }

    /**
     * Returns a hash for this object.
     * <p/>
     * Note: in Java, if we override equals, we MUST override hashCode.
     *
     * @return a hash for this object.
     */
    @Override
    public int hashCode()
    {
        return muderer.hashCode() + weapon.hashCode() + room.hashCode();
    }


}