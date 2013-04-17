package keyf.clueless.data.card;

import keyf.clueless.data.Weapon;

/**
 * A {@link Card} that hold a {@link Weapon}.
 *
 * @author Justin
 */
public enum WeaponCard implements Card
{
    KNIFE(Weapon.KNIFE),
    CANDLESTICK(Weapon.CANDLESTICK),
    REVOLVER(Weapon.REVOLVER),
    ROPE(Weapon.ROPE),
    LEAD_PIPE(Weapon.LEAD_PIPE),
    WRENCH(Weapon.WRENCH);

    /**
     * The weapon this card represents.
     */
    private final Weapon weapon;

    private WeaponCard(Weapon weapon)
    {
        this.weapon = weapon;
    }

    @Override
    public Weapon getItem()
    {
        return weapon;
    }
}