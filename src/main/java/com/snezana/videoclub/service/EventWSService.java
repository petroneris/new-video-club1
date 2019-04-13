package com.snezana.videoclub.service;

import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snezana.videoclub.controller.EventWSHandler;
import com.snezana.videoclub.model.EvtWSMessage;

/**
 * Service for sending WebSocket events
 *
 */
@Service
public class EventWSService {
	
//	private static final Logger LOG = LoggerFactory.getLogger(EventWSService.class);
	
	@Autowired
	private EventWSHandler eventWSHandler;

	public void sendEventWS(EvtWSMessage event) {

		try {
			eventWSHandler.sendEventWS(event);
		} catch (JsonProcessingException e) {			
//			LOG.info("catch for JsonProcessingException");
			e.printStackTrace();
		} catch (IOException e) {		
//			LOG.info("catch for IOException");
			e.printStackTrace();
		}
	}
}
