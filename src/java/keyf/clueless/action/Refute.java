package keyf.clueless.action;

import java.util.Set;
import java.util.TreeSet;
import keyf.clueless.Game;
import keyf.clueless.State;
import keyf.clueless.data.Item;


/**
 *
 * @author jonathanpomper
 */
public class Refute implements Action{
    
    private final Item refuteItem;

    public Refute(Item item)
    {
        this.refuteItem=item;
    }
    
    /**
     *Response to a suggestion
     * @param game
     */
    @Override
    public void performAction(Game game)
    {
        Set<PossibleAction> possibleAction = new TreeSet();
        //An object of possibleAccusation is needed. But no constructor is 
        //provided. am I missing something?
        State newState = new State(possibleAction);
        //Updates messages and log all players via game.setState().
        game.setState(game.getTurnManager().getCurrentPlayer(), newState);
    }
}
