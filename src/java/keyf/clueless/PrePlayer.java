package keyf.clueless;

import keyf.clueless.data.Suspect;
import keyf.util.ParamUtil;

/**
 * Necessary but not sufficient information for creating a {@link Player}.
 */
class PrePlayer
{
    private final String name;
    private final Suspect suspect;

    public PrePlayer(String name, Suspect suspect)
    {
        this.name = ParamUtil.requireNonBlank(name);
        this.suspect = ParamUtil.requireNonNull(suspect);
    }

    public String getName()
    {
        return name;
    }

    public Suspect getSuspect()
    {
        return suspect;
    }

}
