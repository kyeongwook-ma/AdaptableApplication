package com.osgi.android.adaptableapplication.host;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class LookUpServiceTracker extends ServiceTracker{
	
	public LookUpServiceTracker(BundleContext context) {
		super(context, LookUpService.class.getName(), null);
	}

}
