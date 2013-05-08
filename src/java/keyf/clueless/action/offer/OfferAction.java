package keyf.clueless.action.offer;

import keyf.clueless.action.Action;
import keyf.clueless.data.Player;

/**
 * Represents something a {@link Player} can do (such as Move, Suggest, Accuse,
 * etc). Allows the player to form a corresponding {@link Action}.
 *
 * @author justin
 */
public interface OfferAction
{
    /**
     * Returns {@code true} if the {@code action} corresponds to this {@link
     * OfferAction} (e.g., {@link Move} corresponds to {@link PossibleMove}).
     *
     * @param action The action to check for correspondence.
     *
     * @return {@code true} if the {@code action} corresponds to this {@link
     *     OfferAction}, {@code false} otherwise.
     */
    boolean isMatchingAction(Action action);

    /**
     * Returns a JSON representation of this object to send to clients.
     * All should be something like:
     * {
     *     "name": "Human Readable Name",
     *     // might be empty
     *     "options": [[ -set of options- ], [ -another set of options- ]],
     *     "message": "some kind of message that gives meaning to the options"
     * }
     *
     * @return never {@code null}.
     */
    String getJsonString();
}