package ir.maktabsharif.userprofile.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class WhiteListFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Set<String> whiteSet = new HashSet<>();
        try (InputStream resourcesAsStream = getClass().getResourceAsStream("/whitelist.txt");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourcesAsStream))
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                whiteSet.add(line);
            }

        } catch (IOException e) {
            e.fillInStackTrace();
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String path = httpServletRequest.getServletPath();
        if (whiteSet.contains(path)) {
            httpServletRequest.getRequestDispatcher(path).forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
