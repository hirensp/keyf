package keyf.clueless.server;

/**
 * A set of Strings used to access ServletContext Attributes.
 *
 * @author justin
 */
// TODO rename to ContextKeys since it now contains servlet context and session keys.
public final class ServletContextAttributeKeys
{
    public static final String GAME_MANAGER = "GameManager";

    public static final String GAME = "Game";

    public static final String SESSION_PLAYER_ID = "playerId";
}
