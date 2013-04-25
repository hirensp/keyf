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
    private final Set<PossibleAction> availableAtions;

    private final String logMessage;

    public State(Set<PossibleAction> availableActions, String logMessage)
    {
        this.availableAtions = availableActions;
        this.logMessage = logMessage;
    }
}