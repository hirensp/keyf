package keyf.clueless.data.card;

import keyf.clueless.data.Suspect;

/**
 * A {@link Card} that holds a {@link Suspect}.
 * 
 * @author Justin
 */
public enum SuspectCard implements Card
{
    COL_MUSTARD(Suspect.COL_MUSTARD),
    PROF_PLUM(Suspect.PROF_PLUM),
    MR_GREEN(Suspect.MR_GREEN),
    MRS_PEACOCK(Suspect.MRS_PEACOCK),
    MISS_SCARLET(Suspect.MISS_SCARLET),
    MRS_WHITE(Suspect.MRS_WHITE);

    private final Suspect suspect;

    private SuspectCard(Suspect suspect)
    {
        this.suspect = suspect;
    }

    @Override
    public Suspect getItem()
    {
        return suspect;
    }
}