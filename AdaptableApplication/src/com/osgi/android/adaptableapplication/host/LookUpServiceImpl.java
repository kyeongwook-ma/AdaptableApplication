package com.osgi.android.adaptableapplication.host;

import java.util.HashMap;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import android.content.res.Resources;

public class LookUpServiceImpl implements LookUpService {
	
	private Resources res;
	private BundleContext context;
	private HashMap<String, String> lookupMap;
	private ServiceRegistration registration;
	private String absolutePath;

	public LookUpServiceImpl(BundleContext context) {
		this.context = context;
	}
	
	@Override
	public Object lookup(String name) {
		return lookupMap.get(name);
	}

}
