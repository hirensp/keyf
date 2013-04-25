package keyf.clueless;

import java.util.Set;
import keyf.clueless.action.PossibleAction;

/**
 * Represents the state of a {@link Game}. This is the indivisible state of a
 * {@link Game}.
 * 
 * @author justin
 */
public class State
{
    private final Set<PossibleAction> availableActions;

    public State(Set<PossibleAction> availableActions)
    {
        this.availableActions = availableActions;
    }
}