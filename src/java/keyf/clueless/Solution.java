package keyf.clueless;

import keyf.clueless.data.location.Room;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.*;

/**
 * The "envelope" that contains the solution to the murder: the particular
 * {@link Suspect suspect}, {@link Weapon weapon} and {@link Room
 * room}.
 * 
 * @author justin
 */
public class Solution
{
    private final Suspect muderer;
    private final Weapon weapon;
    private final Room room;

    /**
     * Creates a new solution.
     *
     * @param muderer The Suspect that committed the murder
     * @param weapon The weapon the murderer used
     * @param room The room he done it in!
     */
    public Solution(Suspect muderer,
                    Weapon weapon,
                    Room room)
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
    public Suspect getMurderer()
    {
        return muderer;
    }

    /**
     * Returns the weapon used to commit the murder.
     *
     * @return never {@code null}
     */
    public Weapon getWeapon()
    {
        return weapon;
    }

    /**
     * Return the room the murder was committed in.
     *
     * @return never {@code null}
     */
    public Room getRoom()
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