package system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

public class AppChain implements ApplicationContextAware {

    private Logger LOGGER = LoggerFactory.getLogger(AppChain.class);

    private ApplicationContext applicationContext;
    private List<App> apps = new ArrayList<>();

    public void addApp(App app) {
        apps.add(app);
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    private void init() throws Throwable {
        LOGGER.info("开始系统初始化执行链...");
        try {
            App.context = this.applicationContext;
            for (App app : apps) {
                app.contextInitialize();
            }
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage(), throwable);
            System.exit(1);
        }
    }

}
