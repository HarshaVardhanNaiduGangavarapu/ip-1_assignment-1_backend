package com.authentication.Common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request1 = (HttpServletRequest) request;
		// HttpServletResponse response1 = (HttpServletResponse) response;
		String pagename = request1.getRequestURI();
		if (!pagename.contains("/rest/")) {
			pagename = pagename.substring(pagename.lastIndexOf("/") + 1, pagename.length());
			System.out.println("Enter to pagename = " + pagename);
			if (!pagename.trim().equalsIgnoreCase("") && !pagename.trim().endsWith(".css") && !pagename.trim().endsWith(".js")
					&& !pagename.trim().endsWith(".html")
					&& !pagename.trim().equalsIgnoreCase("login.jsp")
					&& !pagename.trim().equalsIgnoreCase("Registration")
					&& !pagename.trim().equalsIgnoreCase("validateLogin")
					&& !pagename.trim().equalsIgnoreCase("register.jsp")){
				if (request1.getSession() == null || request1.getSession().getAttribute("empSrno") == null) {
					request1.getSession().invalidate();
					//
					System.out.println("Enter to pagename111 = ");
					request1.getRequestDispatcher("/").forward(request, response);

				}
			}
			
		}

		chain.doFilter(request, response);

	}

}
