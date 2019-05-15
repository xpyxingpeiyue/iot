package com.tenet.iot.util;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xing
 */
@SuppressWarnings("unused")
public class RenderHelper {
    private static final Log log = LogFactory.get();
    private HttpServletResponse response;

    private RenderHelper(HttpServletResponse response) {
        this.response = response;
    }

    public static RenderHelper use(HttpServletResponse response) {
        return new RenderHelper(response);
    }

    public void render() {
        write(Render.success());
    }

    public void render(Object object) {
        write(Render.success(object));
    }

    public void renderError(String message) {
        write(Render.fail(message));
    }

    public void renderError(int status, String message) {
        response.setStatus(status);
        write(Render.fail(message));
    }

    public void renderError(String message, Object object) {
        write(Render.fail(message, object));
    }

    private void write(Render render) {
        try {
            response.getWriter().write(JSONUtil.toJsonStr(render));
        } catch (IOException e) {
            log.error(e);
        }
    }
}
