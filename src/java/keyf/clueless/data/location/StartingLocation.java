package keyf.clueless.data.location;

import java.util.Collections;
import java.util.Set;

/**
 * Special starting locations of players, they can never be re-entered, but are
 * necessary so that NPCs do not occupy a hallway space.
 *
 * @author Justin
 */
public enum StartingLocation implements Location
{
    COL_MUSTARD_START(Hallway.LOUNGE_DINING_ROOM),

    PROF_PLUM_START(Hallway.STUDY_LIBRARY),

    MR_GREEN_START(Hallway.CONSERVATORY_BALLROOM),

    MRS_PEACOCK_START(Hallway.LIBRARY_CONSERVATORY),

    MISS_SCARLET_SART(Hallway.HALL_LOUNGE),

    MRS_WHITE_START(Hallway.BALLROOM_KITCHEN);

    private final Set<Hallway> neighbors;

    private StartingLocation(Hallway hallway)
    {
        this.neighbors = Collections.singleton(hallway);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public Set<Hallway> getNeighbors()
    {
        return neighbors;
    }

    /**
     * Always returns {@code true}.
     *
     * @return Always {@code true}
     */
    @Override
    public boolean isSingleOccupancy()
    {
        return true;
    }
}