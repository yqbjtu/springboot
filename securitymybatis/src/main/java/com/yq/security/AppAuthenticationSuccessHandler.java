package com.yq.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AppAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	// Spring Security 通过RedirectStrategy对象负责所有重定向事务
		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		/*
		 * 重写handle方法，方法中通过RedirectStrategy对象重定向到指定的url
		 * */
		@Override
		protected void handle(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication)
				throws IOException {
			// 通过determineTargetUrl方法返回需要跳转的url 
			String targetUrl = determineTargetUrl(authentication);
			// 重定向请求到指定的url
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}

		/*
		 * 从Authentication对象中提取角色提取当前登录用户的角色，并根据其角色返回适当的URL。
		 */
		protected String determineTargetUrl(Authentication authentication) {
			String url = "";

			// 获取当前登录用户的角色权限集合
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

			List<String> roles = new ArrayList<String>();

			// 将角色名称添加到List集合
			for (GrantedAuthority a : authorities) {
				roles.add(a.getAuthority());
			}

			// 判断不同角色跳转到不同的url
			if (isAdmin(roles)) {
				url = "/admin";
			} else if (isUser(roles)) {
				url = "/home";
			} else {
				url = "/accessDenied";
			}
			System.out.println("url = " + url);
			return url;
		}

		private boolean isUser(List<String> roles) {
			if (roles.contains("ROLE_USER")) {
				return true;
			}
			return false;
		}

		private boolean isAdmin(List<String> roles) {
			if (roles.contains("ROLE_ADMIN")) {
				return true;
			}
			return false;
		}

		public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
			this.redirectStrategy = redirectStrategy;
		}

		protected RedirectStrategy getRedirectStrategy() {
			return redirectStrategy;
		}
}
