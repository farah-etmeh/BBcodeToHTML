package com.example.bbcodetohtml; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;


//import org.kefirsf.bb.conf.Configuration;

import android.os.Bundle;
import android.os.Handler;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Gallery.LayoutParams;

public class MainActivity extends Activity {


	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setContentView(R.layout.activity_main);

		try {
			SmiliesConverter.readFile(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			InputStream is = getResources().openRawResource(R.raw.test);
			BufferedReader bis = new BufferedReader(new InputStreamReader(is));
			StringBuffer b = new StringBuffer();
			String line = "";
			
				while((line = bis.readLine()) != null){
					b.append(line+"\n");
			}
			bis.close();
			EditText text = (EditText) findViewById(R.id.editeText111);
			text.setText(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		Button btn = (Button) findViewById(R.id.button1);
//		btn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				EditText text = (EditText) findViewById(R.id.editeText111);
//				
//				WebView webView = (WebView) findViewById(R.id.webView1);
//				webView.getSettings().setJavaScriptEnabled(true);
//				webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
//				webView.loadUrl("about:blank");
//				String htmlCode = BBcodeToHtmlConverter.bbcode(text.getText().toString(),MainActivity.this);
//				htmlCode=SmiliesConverter.ConverteSmiles(htmlCode);
//				webView.loadDataWithBaseURL("fake://not/needed",htmlCode, "text/html","utf-8", "");
//		
//			}
//		});
//		
		EditText text = (EditText) findViewById(R.id.editeText111);
		WebView webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
		webView.loadUrl("about:blank");
		TextProcessor processor = BBProcessorFactory.getInstance().create();
		String htmlCode = processor.process(text.getText().toString());
		htmlCode=SmiliesConverter.ConverteSmiles(htmlCode); 
		webView.loadDataWithBaseURL("fake://not/needed",htmlCode, "text/html","utf-8", "");
		
		Button btn2 = (Button) findViewById(R.id.button2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				EditText text = (EditText) findViewById(R.id.editeText111);
				text.setFocusable(true); 
				WebView webView = (WebView) findViewById(R.id.webView1);
				webView.getSettings().setJavaScriptEnabled(true);
				webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
				webView.loadUrl("about:blank");
				TextProcessor processor = BBProcessorFactory.getInstance().create();
				String htmlCode = processor.process(text.getText().toString());
				htmlCode=SmiliesConverter.ConverteSmiles(htmlCode); 
				webView.loadDataWithBaseURL("fake://not/needed",htmlCode, "text/html","utf-8", "");
		
			}
		});
		
		Button btn_test = (Button) findViewById(R.id.Button_test);
		btn_test.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,Test.class);
				startActivity(intent); 
			}
		});
		
		super.onCreate(savedInstanceState);
	}

	 final class MyJavaScriptInterface
	    {
	        public void ProcessJavaScript(final String scriptname, final String args)
	            {             
	                mHandler.post(new Runnable()
	                    {
	                        public void run()
	                            {
	                                //Do your activities
                            }
	                    });
	            }
	    }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
