package com.osgi.android.adaptableapplication.host;

import java.io.InputStream;
import java.util.HashMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import android.content.res.Resources;

public class HostActivator implements BundleActivator {

	private Resources res;
	private BundleContext context;
	private ServiceRegistration registration;
	private String absolutePath;

	public HostActivator(String absoultePath){
		this.absolutePath = absoultePath;
	}
	
	private void registerService(BundleContext bundleContext) {

		LookUpServiceFactory serviceFactory = new LookUpServiceFactory();
		registration = 
				bundleContext.registerService(
						LookUpService.class.getName(), serviceFactory, null);
		
		ServiceTracker tracker = new LookUpServiceTracker(bundleContext);
		tracker.open();
	}
	

	public void start(BundleContext context) {
		// Save a reference to the bundle context.
		this.context = context;
		registerService(context);
	}

	public void stop(BundleContext context) {
		// Unregister the property lookup service.
		registration.unregister();
		this.context = null;
	}

	public BundleContext getContext() {
		return context;
	}

	public Bundle[] getBundles() {
		if (context != null) {
			return context.getBundles();
		}
		return null;
	}

	private void installAndStartBundle(int resId, String bundleName) throws Exception
	{
		InputStream is = res.openRawResource(resId);
		Bundle bundle = context.installBundle(absolutePath + "felix/bundle/" + 
				bundleName + ".jar",is);
		bundle.start();
	}



}
