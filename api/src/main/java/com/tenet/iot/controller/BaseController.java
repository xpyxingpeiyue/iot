package com.tenet.iot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.tenet.iot.util.Render;
import com.tenet.iot.util.RequestContextHolderUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

@SuppressWarnings({"unused"})
@ResponseBody
public class BaseController {
    private static final Log log = LogFactory.get();

    protected Object render() {
        return Render.success();
    }

    protected Object renderFile(File file) {
        return renderFile(file, file.getName());
    }

    protected Object renderFile(File file, String aliasName) {
        if (file != null) {
            HttpServletResponse response = getResponse();
            response.setContentType(FileUtil.getMimeType(file.getPath()));
            response.setContentLengthLong(FileUtil.size(file));
            response.setCharacterEncoding("UTF-8");
            if (StrUtil.isNotBlank(aliasName)) {
                response.setHeader("Content-Disposition", "attachment;filename=" + aliasName);
            }
            try {
                OutputStream os = response.getOutputStream();
                FileUtil.writeToStream(file, os);
            } catch (IOException e) {
                log.error(e);
            }
        }
        return null;
    }

    protected Object render(Object object) {
        return Render.success(object);
    }

    protected Object renderError(String message) {
        return Render.fail(message);
    }

    protected Object renderError(int status, String message) {
        getResponse().setStatus(status);
        return Render.fail(message);
    }

    protected Object renderError(String message, Object object) {
        return Render.fail(message, object);
    }

    protected HttpServletRequest getRequest() {
        return RequestContextHolderUtil.getRequest();
    }

    protected HttpServletResponse getResponse() {
        return RequestContextHolderUtil.getResponse();
    }

    protected Locale getLocal() {
        return RequestContextHolderUtil.getLocale();
    }
}
