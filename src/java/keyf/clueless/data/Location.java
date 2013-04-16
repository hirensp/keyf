package keyf.clueless.data;

import java.util.Set;

/**
 * Represents either a {@link Room} or {@link Hallway}.
 * 
 * @author Justin
 */
public interface Location 
{
    /**
     * Returns other {@link Location}s that are reachable from this {@link 
     * Location}. (Note that this class has no knowledge of {@link Character} 
     * locations.)
     * 
     * @return {@link Location}s that are reachable from this {@link Location}.
     */
    public Set<? extends Location> getNeighbors();
}