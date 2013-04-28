package keyf.clueless.action;

import keyf.clueless.data.Item;
import keyf.clueless.data.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Allows a Player to refute a {@link Suggestion}.
 *
 * @author Justin
 */
public class OfferRefutal implements OfferAction
{
    private final Set<Item> cards = new HashSet<Item>();

    public OfferRefutal(Player playerMakingRefutal)
    {
        cards.addAll(playerMakingRefutal.getCards());
    }

    /**
     * Returns the "cards" that a player has available to refute a suggestion.
     *
     * @return never {@code null}, never empty.
     */
    public Set<Item> getCards()
    {
        return cards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMatchingAction(Action action)
    {
        return action instanceof Refute;
    }
}