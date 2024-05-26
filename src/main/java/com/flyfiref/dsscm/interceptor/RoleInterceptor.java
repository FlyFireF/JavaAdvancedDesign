package com.flyfiref.dsscm.interceptor;

import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.tools.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class RoleInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();//获取session
        User user = (User) session.getAttribute(Constants.USER_SESSION);//获取user
        if (1 != user.getUserRole()) {//如果不是管理员，即role!=1，就拦截
            response.sendRedirect(request.getContextPath() + "/sys/role/error.html");
            return false;
        }
        return true;
    }
}
