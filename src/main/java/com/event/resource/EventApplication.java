package com.event.resource;

import java.util.Set;

import javax.ws.rs.core.Application;

import com.event.listener.ApplicationContextListener;

public class EventApplication extends Application {

	@Override
	public Set<Object> getSingletons() {
		return ApplicationContextListener.getSingletons(); 
	}
}
