package keyf.clueless.action.offer;

import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.data.Item;
import keyf.clueless.data.Player;

import java.util.HashSet;
import java.util.Set;
import keyf.clueless.action.Action;
import keyf.clueless.action.Refute;
import org.json.JSONArray;
import org.json.JSONObject;

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

    /**
     * {
     *     "name": "Refute",
     *     "options:" [ [ Items ] ],
     *     "message:" "I will refute with: "
     * }
     */
    @Override
    public String getJsonString()
    {
        JSONObject json = new JSONObject();
        json.put("name", "Refute");
        for(Item card : cards)
        {
            json.put("options", new JSONArray().put(new JSONArray(card)));
        }
        json.put("message", "I will refute with:");
        return json.toString();
    }
}