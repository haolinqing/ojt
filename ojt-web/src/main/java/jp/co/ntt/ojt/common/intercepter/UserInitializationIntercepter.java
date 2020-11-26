package jp.co.ntt.ojt.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import jp.co.ntt.ojt.domain.model.User;
import jp.co.ntt.ojt.domain.service.user.LoginUserDetails;

public class UserInitializationIntercepter implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(object != null && object instanceof LoginUserDetails) {
			LoginUserDetails userDetails = (LoginUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(userDetails.getUser().getStatus().equals(User.INIT)) {
				response.sendRedirect(request.getContextPath() + "/password/change?form");
				return false;
			}
		}
		return true;
	}
	
}
