package keyf.clueless.action;

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
}