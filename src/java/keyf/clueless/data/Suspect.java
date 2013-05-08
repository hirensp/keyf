package keyf.clueless.data;

import keyf.clueless.data.location.StartingLocation;

/**
 *
 * @author Justin
 */
public enum Suspect implements Item
{
    COL_MUSTARD(StartingLocation.COL_MUSTARD_START),
    PROF_PLUM(StartingLocation.PROF_PLUM_START),
    MR_GREEN(StartingLocation.MR_GREEN_START),
    MRS_PEACOCK(StartingLocation.MRS_PEACOCK_START),
    MISS_SCARLET(StartingLocation.MISS_SCARLET_START),
    MRS_WHITE(StartingLocation.MRS_WHITE_START);

    private final StartingLocation startingLocation;

    private Suspect(StartingLocation startingLocation)
    {
        this.startingLocation = startingLocation;
    }

    /**
     * Returns the {@link StartingLocation} of this suspect.
     *
     * @return never {@code null}
     */
    public StartingLocation getStartingLocation()
    {
        return startingLocation;
    }

    @Override
    public String getDescription()
    {
        return this.name();
    }
}
