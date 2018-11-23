package system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public abstract class App {

    protected Logger LOGGER = LoggerFactory.getLogger(App.class);

    protected static ApplicationContext context;

    //初始化函数,由子类实现
    public abstract void contextInitialize() throws Throwable;

    public static <T> T getBean(String name, Class<T> t) {
        Object bean = context.getBean(name);
        if (t.isAssignableFrom(bean.getClass())) {
            return t.cast(bean);
        } else {
            return null;
        }
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }

}
