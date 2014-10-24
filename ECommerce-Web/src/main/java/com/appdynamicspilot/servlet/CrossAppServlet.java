package com.appdynamicspilot.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.net.URL;
import javax.servlet.ServletConfig;

/**
 * Created by aleftik on 10/24/14.
 */
public class CrossAppServlet extends HttpServlet {
    private String host = null;
    public void init(ServletConfig config) {

        String envSuffix = config.getInitParameter("VAR");
        if (envSuffix == null) {
            envSuffix = "ENVLBR_PORT_80_TCP_ADDR";
        }
        Map<String, String> envs = System.getenv();

        for (String env:envs.keySet()) {
            if (env.equals(envSuffix)) {
                host = envs.get(env);
            }
        }

    }
    public void sevice(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (host != null) {
            URL url = new URL("http://" + host + "/rest/items/all");
            url.openConnection().getContent();
        }
        response.setStatus(204);
    }
}
