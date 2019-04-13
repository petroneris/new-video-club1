package com.snezana.videoclub.configuration;

import javax.servlet.http.HttpSessionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Listener used for HTTP session tracking.
 */
public class SessionListener extends HttpSessionEventPublisher {
	
	private static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

	/* Session listener will be triggered when the session is created... */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(15 * 60); // for session timeout maxInt = 15 min
		super.sessionCreated(event);
		LOG.info("==== Session is created ====");
	}

	/* ...and destroyed with this method. */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		super.sessionDestroyed(event);
		LOG.info("==== Session is destroyed ====");
	}

}
