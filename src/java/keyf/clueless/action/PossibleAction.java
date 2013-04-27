package keyf.clueless.action;

import keyf.clueless.data.Player;

/**
 * Represents something a {@link Player} can do (such as Move, Suggest, Accuse,
 * etc).
 * 
 * @author justin
 */
public interface PossibleAction
{
    /**
     * Returns {@code true} if the {@code action} corresponds to this {@link
     * PossibleAction} (e.g., {@link Move} corresponds to {@link PossibleMove}).
     *
     * @param action The action to check for correspondence.
     *
     * @return {@code true} if the {@code action} corresponds to this {@link
     *     PossibleAction}, {@code false} otherwise.
     */
    boolean isMatchingAction(Action action);
}