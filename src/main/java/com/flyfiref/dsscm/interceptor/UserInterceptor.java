package com.flyfiref.dsscm.interceptor;

import com.flyfiref.dsscm.pojo.User;
import com.flyfiref.dsscm.tools.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class UserInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();//获取session
        User user = (User) session.getAttribute(Constants.USER_SESSION);//获取user
        String uri = request.getRequestURI();//获取请求路径
        String[] parts = uri.split("/");//根据斜杠拆分请求路径
        String lastPart = parts[parts.length - 1];//获取请求路径最后一部分
        String secondLastPart = parts[parts.length - 2];//获取请求路径倒数第二部分
        if(1 == user.getUserRole()){//是管理员就放行
            return true;
        }else if (lastPart.matches("\\d+")){//lastPart是数字，也就是访问的是RESTful，以id结尾
            int id= Integer.parseInt(lastPart);//转换为int，就是访问的user_id
            if (id==user.getId()) {//不是管理员，但是id匹配，此时只能访问view
                if("view".equals(secondLastPart)){
                    return true;
                }
            }
        }//其他情况跳到无权访问页面，并return false
        response.sendRedirect(request.getContextPath() + "/sys/role/error.html");
        return false;
    }
}
