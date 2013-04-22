package keyf.clueless;

/**
 * Contains a message for a user.
 * @author jonathanpomper
 */
public class Message {
    private Set<Card> cards;
    private String msg;
    private String logUpdate;
   
    /**
   * Creates a new game. The true criminal, murder weapon, and room are
   * decided, and all cards are assigned to the {@code players}.
   * 
   * @param cards set of cards to be displayed
   * @param msg msg to be displayed in speech/thought bubble
   * @param logUpdate update to be added to the game log
   */
    
    public Message(Set<Card> cards, String msg, String logUpdate){
       this.cards=cards;
       this.msg=msg;
       this.logUpdate=logUpdate;
    }
}
