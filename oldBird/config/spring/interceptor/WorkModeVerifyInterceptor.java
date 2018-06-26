package com.ecaicn.cbs.webapp.interceptors;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecaicn.cbs.business.enums.WorkMode;
import com.ecaicn.cbs.business.service.WorkContext;
import com.ecaicn.cbs.common.Guard;
import com.ecaicn.cbs.framework.annotation.ClassroomOnly;
import com.ecaicn.cbs.framework.message.ResponseMessage;
import com.ecaicn.cbs.framework.util.JsonUtils;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Raven
 */
public class WorkModeVerifyInterceptor extends HandlerInterceptorAdapter {

    private final WorkContext workContext;

    public WorkModeVerifyInterceptor(final WorkContext workContext) {
        this.workContext = Guard.notNull(workContext);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HandlerMethod.class.isAssignableFrom(handler.getClass())) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.hasMethodAnnotation(ClassroomOnly.class) || method.getBeanType().isAnnotationPresent(ClassroomOnly.class)) {
                boolean valid = this.workContext.getMode().equals(WorkMode.CLASSROOM);
                if (!valid) {
                    this.writeError(response, -1, "只允许在教室模式中访问");
                }
                return valid;
            }
        }
        return true;
    }

    private void writeError(HttpServletResponse response, int code, String message) throws Exception {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter printWriter = response.getWriter();
        printWriter.write(JsonUtils.serialize(ResponseMessage.error(code, message)));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
