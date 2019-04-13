package com.snezana.videoclub.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.snezana.videoclub.controller.LoginController;

/** 
 * Custom filter for session timeout detection
 */
public class CustomFilter extends GenericFilterBean {
	
//	private static final Logger LOG = LoggerFactory.getLogger(CustomFilter.class);
		
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
//		LOG.info("LoginController.paramPage=" + LoginController.paramPage);
		try {
			if (req.getRequestURI().equals(req.getContextPath() + "/login") && LoginController.paramPage == 1) {
				LoginController.paramPage = 0;
				resp.sendRedirect(req.getContextPath() + "/login?timeout=true");
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			// Log Exception
			e.printStackTrace();
		}
	}
}
