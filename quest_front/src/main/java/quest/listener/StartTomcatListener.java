package quest.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StartTomcatListener implements ServletContextListener {

	
    public void contextInitialized(ServletContextEvent sce)  { 
        //Singleton.getInstance();
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
        //Singleton.getInstance().getEmf().close();
    }
	
}
