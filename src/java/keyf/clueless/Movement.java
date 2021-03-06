package keyf.clueless;

import keyf.clueless.data.Item;
import keyf.clueless.data.location.Location;
import org.json.JSONObject;

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

    public String getJsonString()
    {
        JSONObject json = new JSONObject();
        
        json.put("what", what.getDescription());
        json.put("where", where.getDescription());
        return json.toString();
    }
}
