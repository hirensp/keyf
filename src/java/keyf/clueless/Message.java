package keyf.clueless;

import static keyf.util.ParamUtil.*;

import java.util.Set;
import keyf.clueless.data.card.Card;

/**
 * Contains a message for a user.
 * @author jonathanpomper
 */
public class Message {
    private final Set<Card> cards;
    private final String msg;
    private final String logUpdate;

   /**
    * Creates a message for a user.
    *
    * @param cards set of cards to be displayed
    * @param msg message to be displayed in speech/thought bubble
    * @param logUpdate update to be added to the game log
    */
    public Message(Set<Card> cards, String msg, String logUpdate) {
       this.cards = requireNonNullAndContainsNonNull(cards);
       this.msg = requireNonNull(msg);
       this.logUpdate = requireNonNull(logUpdate);
    }
}