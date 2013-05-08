package keyf.clueless.data;

import keyf.clueless.data.location.StartingLocation;

/**
 *
 * @author Justin
 */
public enum Suspect implements Item
{
    COL_MUSTARD(StartingLocation.COL_MUSTARD_START, "COL_MUSTARD"),
    PROF_PLUM(StartingLocation.PROF_PLUM_START, "PROF_PLUM"),
    MR_GREEN(StartingLocation.MR_GREEN_START, "MR_GREEN"),
    MRS_PEACOCK(StartingLocation.MRS_PEACOCK_START, "MRS_PEACOCK"),
    MISS_SCARLET(StartingLocation.MISS_SCARLET_START, "MISS_SCARLET"),
    MRS_WHITE(StartingLocation.MRS_WHITE_START, "MRS_WHITE");

    private final StartingLocation startingLocation;
    private final String description;

    private Suspect(StartingLocation startingLocation, String description)
    {
        this.startingLocation = startingLocation;
        this.description = description;
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
    public String getDescription()
    {
        return description;
    }
}
