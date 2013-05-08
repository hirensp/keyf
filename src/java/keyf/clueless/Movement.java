package keyf.clueless;

import keyf.clueless.data.Item;
import keyf.clueless.data.location.Location;

/**
 *
 * @author Justin
 */
public class Movement
{
    private final Item what;
    private final Location where;

    public Movement(Item what, Location where)
    {
        this.what = what;
        this.where = where;
    }

    public Item getWhat()
    {
        return what;
    }

    public Location getWhere()
    {
        return where;
    }

    public String toJsonString()
    {
        // YONI!!!!!!
        return null;
    }
}
