package keyf.clueless.data;

import static keyf.util.ParamUtil.*;

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
    private final Set<Card> cards;

    public Player(String identifier, Suspect suspect, Set<Card> cards)
    {
        this.identifier = requireNonNull(identifier);
        this.suspect = requireNonNull(suspect);
        this.cards = Collections.unmodifiableSet(
                requireNonNullAndContainsNonNull(cards));
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public Suspect getSuspect()
    {
        return suspect;
    }

    public Set<Card> getCards()
    {
        return cards;
    }
}