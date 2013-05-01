/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package keyf.clueless.server;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import keyf.clueless.GameManager;
import static keyf.clueless.server.ServletContextAttributeKeys.*;

/**
 * Web application lifecycle listener. Responsible for creating the {@link 
 * GameManager} on web app startup (and removing it on web app shutdown).
 *
 * @author justin
 */
@WebListener()
public class GameManagerInitializer implements ServletContextListener
{
    @Override
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext servletContext = event.getServletContext();
        servletContext.setAttribute(GAME_MANAGER, new GameManager());
    }

    @Override
    public void contextDestroyed(ServletContextEvent event)
    {
        ServletContext servletContext = event.getServletContext();
        servletContext.removeAttribute(GAME_MANAGER);
    }
}
