package com.osgi.android.adaptableapplication;

import java.util.Properties;

import org.apache.felix.framework.Felix;
import org.osgi.framework.BundleException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//FelixProperties 생성
	
	private Properties felixProperties;
	
	//Bundle을 Controll하는 controller생성 
	
	private BundleController bundleController;
	
	//displayData들
	private TextView displayDataView;
	private String displayDatas;
	
	//Felix FrameWork
	Felix m_felix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Properties 설정
        felixProperties = new FelixConfig(this.getFilesDir().getAbsolutePath()).getConfigProps();
        //FelixFrameWork 생성과 실행
        m_felix = new Felix(felixProperties);
        
        try {
			m_felix.start();
			
			Log.i("Felix 실행","실행");
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //BundleController 생성 (번들들도 같이 생성되고 m_felix 번들에 설치된다.
        bundleController = new BundleController(m_felix.getBundleContext(),this.getFilesDir().getAbsolutePath(), this.getResources());
       
        displayDataView = (TextView) findViewById(R.id.maintext);
        displayBundleDatas();
        

    }
    private void displayBundleDatas()
    {
    	displayDatas = bundleController.getBundleDatas("TextBundle");
    	displayDataView.setText(displayDatas);
    }
    
    


}
