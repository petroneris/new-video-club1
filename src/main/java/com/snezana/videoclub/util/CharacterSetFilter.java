package com.snezana.videoclub.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Implementation of (servlet) Filter that enforces the UTF-8 character set
 * compliance for traffic going in/out of the Tomcat server.
 */
public class CharacterSetFilter implements Filter {

	@Override
	public void destroy() {
		// Do nothing.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		next.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Do nothing.
	}

}
