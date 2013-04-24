package keyf.clueless.action;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import keyf.clueless.data.Suspect;
import keyf.clueless.data.Weapon;

/**
 * The abstraction of a Suggestion and an Accusation.
 *
 * @author justin
 */
abstract class PossibleOffer implements PossibleAction
{
    /**
     * All the possible suspects a player could use when making a suggestion.
     */
    private final Set<Suspect> possibleSuspects
            = Collections.unmodifiableSet(EnumSet.allOf(Suspect.class));

    /**
     * All the possible weapons a player could use when making a suggestion.
     */
    private final Set<Weapon> possibleWeapons
            = Collections.unmodifiableSet(EnumSet.allOf(Weapon.class));

    public Set<Suspect> getPossibleSuspects()
    {
        return possibleSuspects;
    }

    public Set<Weapon> getPossibleWeapons()
    {
        return possibleWeapons;
    }
}
