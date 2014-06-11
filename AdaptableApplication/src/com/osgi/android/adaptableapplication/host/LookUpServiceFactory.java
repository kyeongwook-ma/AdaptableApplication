package com.osgi.android.adaptableapplication.host;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceFactory;
import org.osgi.framework.ServiceRegistration;

public class LookUpServiceFactory implements ServiceFactory {
	
	@Override
	public Object getService(Bundle bundle, ServiceRegistration arg1) {
		return new LookUpServiceImpl(bundle.getBundleContext());
	}

	@Override
	public void ungetService(Bundle bundle, ServiceRegistration arg1, Object arg2) {
		
	}
}
