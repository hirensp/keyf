package keyf.clueless.action;

import keyf.clueless.action.Action;

/**
 * Represents something a {@link Player} can do (such as Move, Suggest, Accuse,
 * etc).
 * 
 * @author justin
 */
public interface PossibleAction
{
    boolean isMatchingAction(Action action);
}
