package com.osgi.android.adaptableapplication;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import android.content.res.Resources;
import android.util.Log;

public class BundleController {
	
	private String absolutePath;
	private Resources res;
	private BundleContext context;
	
	private File bundlesDir;
	private File newBundlesDir;
	private File cacheDir;
	
	public final static int CANTINSTALL = -1;
	public final static int COMPLETEINSTALL = 1;

	
	public BundleController(BundleContext context,String absolutePath,Resources res) {
		// TODO Auto-generated constructor stub
		this.absolutePath = absolutePath;
		this.res = res;
		this.context = context;
		//directory 생성
		makeDir();
		try {
			installAndStartBundles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void makeDir(){
		
		bundlesDir = new File(absolutePath+ "/felix/bundle");
		if(!bundlesDir.exists()){
			if(!bundlesDir.mkdirs()){
				throw new IllegalStateException("Unable to create bundlesDir dir");
			}
		}
		newBundlesDir = new File(absolutePath+"/felix/newbundle");
		if(!newBundlesDir.exists()){
			if(!newBundlesDir.mkdirs()){
				throw new IllegalStateException("Unable to create newBundleDir dir");
			}
		}
		cacheDir = new File(absolutePath+"/felix/cache");
		if (!cacheDir.exists()) {
        	if (!cacheDir.mkdirs()) {
        		throw new IllegalStateException("Unable to create felixcache dir");
        	}
        }
	}
	//번들 설치 
	private void installAndStartBundles()throws Exception
	{
		InputStream is = res.openRawResource(R.raw.textbundle);
		Bundle textBundle = context.installBundle(absolutePath+"felix/bundle/textbundle.jar",is);
		
		textBundle.start();
		Log.i("bundleInfo",Integer.toString(textBundle.getState()));
	}
	
	public String getBundleDatas(String bundleName) 
	{
		String bundleData = null;
		for(Bundle b : context.getBundles())
		{
			if(b.getSymbolicName().equals("TextBundle"))
			{
				
				for(ServiceReference<?> ref : b.getRegisteredServices())
				{
					Object service = b.getBundleContext().getService(ref);
					try {
						Method getText = service.getClass().getDeclaredMethod("getText", null);
		
						try {
							bundleData = (String) getText.invoke(service,null);
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (NoSuchMethodException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
			}
		}
		return bundleData;
		
	}

}
