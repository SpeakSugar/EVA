package filters;

import com.cloud.base.SystemInitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 前台前端语言设置filter(基于Cookie)
 */
@Component
@WebFilter(urlPatterns = "/*", filterName = "languageFilter")
@Order(1)
public class LanguageFilter implements Filter {

    private Logger LOGGER = LoggerFactory.getLogger(LanguageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("语言过滤器初始化...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        LOGGER.info("进入语言过滤器...");
        String language = getLanguageFromCookies((HttpServletRequest) request);
        SystemInitConfig.setLanguage(language);
        chain.doFilter(request, response);
    }

    private String getLanguageFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if ("service_language".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }

}
