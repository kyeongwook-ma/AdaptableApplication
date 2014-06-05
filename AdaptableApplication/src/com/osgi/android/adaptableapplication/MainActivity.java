package com.osgi.android.adaptableapplication;

import java.util.Properties;

import org.apache.felix.framework.Felix;
import org.osgi.framework.BundleException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//FelixProperties ����
	
	private Properties felixProperties;
	
	//Bundle�� Controll�ϴ� controller���� 
	
	private BundleController bundleController;
	
	//displayData��
	private TextView displayDataView;
	private String displayDatas;
	
	//Felix FrameWork
	Felix m_felix = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Properties ����
        felixProperties = new FelixConfig(this.getFilesDir().getAbsolutePath()).getConfigProps();
        //FelixFrameWork ������ ����
        m_felix = new Felix(felixProperties);
        
        try {
			m_felix.start();
			
			Log.i("Felix ����","����");
		} catch (BundleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //BundleController ���� (����鵵 ���� �����ǰ� m_felix ���鿡 ��ġ�ȴ�.
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
