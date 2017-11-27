package com.event.listener;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;

import com.event.module.DataSourceModule;
import com.event.module.FilterModule;
import com.event.resource.ParticipantResource;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApplicationContextListener extends GuiceServletContextListener {

	private static final Set<Object> singletons = new HashSet<>();

	private static Injector staticInjector;
	private Injector injector;
	
	public static Set<Object> getSingletons() {
		return singletons;
	}

	public static Injector getStaticInjector() {
		return staticInjector;
	}
	
	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(new DataSourceModule(), new FilterModule());
		staticInjector = injector;
		return injector;
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		startPersistService();
		addResources();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		stopPersistService();
	}
	
	private void startPersistService() {
		PersistService persistService = injector.getInstance(PersistService.class);
		try {
			persistService.start();
		} catch (IllegalStateException e) {
			// Ignore
		}
	}

	private void stopPersistService() {
		PersistService persistService = injector.getInstance(PersistService.class);
		persistService.stop();
	}
	
	private void addResources() {
		getSingletons().add(injector.getInstance(ParticipantResource.class));
	}
	
}
