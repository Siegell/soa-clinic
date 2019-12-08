package by.siegell.soa.clinic.web;

import by.siegell.soa.clinic.ioc.IoCConfigurer;
import by.siegell.soa.clinic.ioc.IoCException;
import by.siegell.soa.clinic.pool.ConnectionPool;
import by.siegell.soa.clinic.pool.PoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            IoCConfigurer.configure();
            ConnectionPool.getInstance().init();
        } catch (IoCException | PoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}
