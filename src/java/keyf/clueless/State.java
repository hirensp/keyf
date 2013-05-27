package keyf.clueless;

import java.util.ArrayList;
import static keyf.util.ParamUtil.requireNonNullAndContainsNonNull;

import keyf.clueless.action.Action;
import keyf.clueless.action.offer.OfferAction;
import keyf.clueless.data.Player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.json.JSONObject;

/**
 * Represents the state of a {@link Game} for a particular {@link Player}.
 *
 * @author justin
 */
public class State
{
    /**
     * All actions that a Player can currently make, in the order which they
     * should be seen from left to right.
     */
    private final List<OfferAction> availableActions;

    /**
     * Actions that a player will still be able to make, but should not
     * currently have access to (such as when the player is waiting for a
     * response from a suggestion).
     */
    private final List<OfferAction> temporarilyUnavailableActions;

    // TODO --we might need a parameter that indicates who is saying the
    //        "suspectMessage"

    /**
     * A format string used for the speech bubble of a Character (may be
     * different for each Player)
     */
    private final String suspectMessage;

    /**
     * Message that appears in the game log (will be the same for each player).
     */
    private final String logMessage;

    /**
     * An ID so that we can tell one state from another
     */
    private final UUID id;

    /**
     * The set of things that have moved since the previous state
     */
    private final Set<Movement> thingsThatMoved = new HashSet<Movement>();

    /**
     * Creates a new state.
     *
     * @param availableActions actions that are currently available to the
     *     Player
     * @param temporarilyUnavailableActions actions that the user cannot
     *     currently use, but will be able to in the future
     * @param suspectMessage message as said by the suspect
     * @param logMessage message displayed in the log
     */
    public State(List<OfferAction> availableActions,
                 List<OfferAction> temporarilyUnavailableActions,
                 String suspectMessage,
                 String logMessage)
    {
        this(availableActions,
             temporarilyUnavailableActions,
             suspectMessage,
             logMessage,
             Collections.<Movement>emptySet());
    }

    public State(List<OfferAction> availableActions,
                 List<OfferAction> temporarilyUnavailableActions,
                 String suspectMessage,
                 String logMessage,
                 Set<Movement> thingsThatMoved)
    {
        this.availableActions = Collections.unmodifiableList(
                requireNonNullAndContainsNonNull(availableActions));

        this.temporarilyUnavailableActions = Collections.unmodifiableList(
                requireNonNullAndContainsNonNull(temporarilyUnavailableActions));

        this.suspectMessage = suspectMessage;

        this.logMessage = logMessage;

        this.id = UUID.randomUUID();

        this.thingsThatMoved.addAll(thingsThatMoved);
    }

    public List<OfferAction> getAvailableActions()
    {
        return availableActions;
    }

    public List<OfferAction> getTemporarilyUnavailableActions()
    {
        return temporarilyUnavailableActions;
    }

    public String getSuspectMessage()
    {
        return suspectMessage;
    }

    public String getLogMessage()
    {
        return logMessage;
    }

    public UUID getId()
    {
        return id;
    }

    /**
     * {
     *     "suspectMessage": "message",
     *     "logMessage": "log message",
     *     "actions": [ available actions ],
     *     "thingsThatMoved": [ things that moved ]
     * }
     *
     * @return
     */
    public String getJsonString()
    {
        JSONObject json = new JSONObject();

        for(Movement movement : thingsThatMoved)
        {
            json.accumulate("movements", movement.getJsonString());
        }
        for(OfferAction action : availableActions)
        {
            json.accumulate("actions", action.getJsonString());
        }
        json.put("suspectMessage", suspectMessage);
        json.put("logMessage", logMessage);
        return json.toString();
    }

    /**
     * A convenient way to build new {@list State}s from preexisting {@list
     * State}s.
     */
    public static class Builder
    {
        private final List<OfferAction> availableActions;
        private final List<OfferAction> temporarilyUnavailableActions;
        private String suspectMessage;
        private String logMessage;
        private final Set<Movement> thingsThatMoved = new HashSet<Movement>();

        public Builder(State state)
        {
            this.availableActions = new ArrayList<OfferAction>(
                    state.getAvailableActions());

            this.temporarilyUnavailableActions = new ArrayList<OfferAction>(
                    state.getTemporarilyUnavailableActions());
        }

        public Builder setLogMessage(String logMessage)
        {
            this.logMessage = logMessage;
            return this;
        }

        public Builder setSuspectMessage(String suspectMessage)
        {
            this.suspectMessage = suspectMessage;
            return this;
        }

        /**
         * Adds the {@code action} to the list of availableActcions.
         *
         * @param action the action to add.
         *
         * @return this Builder instance.
         */
        public Builder addAction(OfferAction action)
        {
            availableActions.add(action);
            return this;
        }

        /**
         * Removes the {@code action} from the list of availableActions.
         *
         * @param action the action to remove
         *
         * @return this Builder instance.
         */
        public Builder removeAction(Action action)
        {
            for (Iterator<OfferAction> it = availableActions.iterator();
                 it.hasNext();)
            {
                OfferAction possibleAction = it.next();
                if (possibleAction.isMatchingAction(action))
                {
                    it.remove();
                    break;
                }
            }

            return this;
        }

        public Builder clearActions()
        {
            availableActions.clear();
            return this;
        }

        /**
         * Makes all actions temporarily unavailable (should be called after
         * a call to {@link #addAction} or (@link #removeAction}).
         *
         * @return this Builder instance.
         */
        public Builder makeAllActionsTemporarilyUnavailable()
        {
            temporarilyUnavailableActions.addAll(availableActions);
            availableActions.clear();
            return this;
        }

        /**
         * Makes all actions available.
         *
         * @return this Builder instance.
         */
        public Builder makeAllActionsAvailable()
        {
            availableActions.addAll(temporarilyUnavailableActions);
            temporarilyUnavailableActions.clear();
            return this;
        }

        public Builder setThingsThatMoved(Set<Movement> moved)
        {
            thingsThatMoved.addAll(moved);
            return this;
        }

        /**
         * Builds the new State.
         *
         * @return the new State object.
         */
        public State build()
        {
            return new State(
                    availableActions,
                    temporarilyUnavailableActions,
                    suspectMessage,
                    logMessage,
                    thingsThatMoved);
        }
    }
}