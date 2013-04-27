package keyf.clueless.data;

import static keyf.util.ParamUtil.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import keyf.clueless.data.card.Card;

/**
 * Represents a human playing a single game.
 *
 * @author Justin
 */
public class Player
{
    private final String identifier;
    private final Suspect suspect;
    private final Set<? extends Item> cards;

    /**
     * Creates a new instance.
     *
     * @param identifier the name of this Player, must not be {@code null} or
     *     blank ("")
     * @param suspect the suspect of this Player, must not be {@code null}
     * @param cards this Player's cards, must not be {@code null}, contain
     *     {@code null}, or be {@link Collection#isEmpty() empty}.
     */
    public Player(String identifier, Suspect suspect, Set<? extends Item> cards)
    {
        this.identifier = requireNonNull(identifier);
        this.suspect = requireNonNull(suspect);
        this.cards = Collections.unmodifiableSet(
                requireNonNullAndContainsNonNullAndNotEmpty(cards));
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public Suspect getSuspect()
    {
        return suspect;
    }

    public Set<? extends Item> getCards()
    {
        return cards;
    }
}