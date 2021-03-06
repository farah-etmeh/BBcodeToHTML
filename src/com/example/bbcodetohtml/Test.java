package com.example.bbcodetohtml; 


import java.io.IOException;

import com.example.bbcodetohtml.MainActivity.MyJavaScriptInterface;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Gallery.LayoutParams;

import org.json.JSONException;
import org.kefirsf.bb.BBProcessorFactory;
import org.kefirsf.bb.TextProcessor;

public class Test extends Activity {
	
	String summary = "";
	String[] strings= {"[b]Hello[/b]",
			 "[u]Hello[/u][:)]",
			 "[i]Hello[/i][:(]",	
			 "[i][i]Hello[/i]pla pla pla[/i]",				 
			 "[s]Hello[/s]", 
			 "\n",
			 "[color=red]Hello[/color]",
			 "[color=#FF0000]Hello[/color]",
			 "[size=9]Hello[/size]",
			 "[font=Verdana]Hello[/font]",
			 "[align=left]left aligned text[/align]",
			 "[left]left aligned text[/left]",
			 "[align=center]centered text[/align]",
			 "[center]centered text[/center]",
			 "[align=right]right aligned text[/align]",
			 "[right]right aligned text[/right]",
			 "[quote=\"Name\"]Hello[/quote]",
			 "[quote]Hello[/quote]",
			 "[quote=Name]Hello[/quote]",
			 "[code]Hello[/code]",
			 "[list] [*]Red [*]Blue [*]Yellow [/list]",
			 "[list=1] [*]Red [*]Blue [*]Yellow [/list]",
			 "[list=a] [*]Red [*]Blue [*]Yellow [/list]",
			 "[list=A] [*]Red [*]Blue [*]Yellow [/list]",
			 "[list=i] [*]Red [*]Blue [*]Yellow [/list]",
			 "[list=I] [*]Red [*]Blue [*]Yellow [/list]",
			 "[url]http://www.example.com/[/url]",
			 "[url=http://www.example.com/]Example[/url]",
			 "[email]user@domain.com[/email]",
			 "[email=user@domain.com]email me[/email]",
			 "[img]http://domain.com/image.jpg[/img]",
			 "[u][color=red]Hello[/color][/u]"
			 }; 
	
	private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		setContentView(R.layout.test_layout);
		try {
			SmiliesConverter.readFile(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summary = "" ; 
		TableLayout tablel_attributes =(TableLayout) findViewById(R.id.table);
		tablel_attributes.removeAllViews(); 
		TextProcessor processor = BBProcessorFactory.getInstance().create();
		
		for (int i = 0; i < strings.length; i++) {
			TableRow row=new TableRow(Test.this); 
			row.setLayoutParams(new LayoutParams(
					LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));
			row.setPadding(0,10, 0,10);
			
			TextView text_bbcode= new TextView(Test.this); 
			text_bbcode.setLayoutParams(new android.widget.TableRow.LayoutParams( LayoutParams.MATCH_PARENT,  LayoutParams.WRAP_CONTENT,0.5f));
			text_bbcode.setTextColor(Color.BLUE); 
			text_bbcode.setText(strings[i]);
			row.addView(text_bbcode);

			TextView text_html= new TextView(Test.this); 
			text_html.setLayoutParams(new android.widget.TableRow.LayoutParams( LayoutParams.MATCH_PARENT,  LayoutParams.WRAP_CONTENT,0.5f));   
			text_html.setPadding (10, 0,8,0);  

			
			String inhtml = SmiliesConverter.ConverteSmiles((processor.process(strings[i]))); 
			text_html.setText(inhtml);
			text_html.setSingleLine(false);
			row.addView(text_html);
	
			if(inhtml.contains("[") )
				text_html.setTextColor(Color.RED);
			else 
				summary = summary+"</br>"+inhtml ;
			
			tablel_attributes.addView(row);			
			
		
		}
		
		WebView webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
		webView.loadUrl("about:blank");
		webView.loadData(BBcodeToHtmlConverter.bbcode(summary,this), "text/html; charset=UTF-8", null);
		webView.loadDataWithBaseURL("fake://not/needed", BBcodeToHtmlConverter.bbcode(summary,this), "text/html","utf-8", "");

//		Button btn = (Button) findViewById(R.id.button1);
//		btn.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				summary = "" ; 
//				TableLayout tablel_attributes =(TableLayout) findViewById(R.id.table);
//				tablel_attributes.removeAllViews(); 
//				
//				for (int i = 0; i < strings.length; i++) {
//					TableRow row=new TableRow(Test.this); 
//					row.setLayoutParams(new LayoutParams(
//							LayoutParams.FILL_PARENT,
//							LayoutParams.WRAP_CONTENT));
//					row.setPadding(0,10, 0,10);
//					
//					TextView text_bbcode= new TextView(Test.this); 
//					text_bbcode.setLayoutParams(new android.widget.TableRow.LayoutParams( LayoutParams.MATCH_PARENT,  LayoutParams.WRAP_CONTENT,0.5f));
//					text_bbcode.setTextColor(Color.BLUE); 
//					text_bbcode.setText(strings[i]);
//					row.addView(text_bbcode);
//
//					TextView text_html= new TextView(Test.this); 
//					text_html.setLayoutParams(new android.widget.TableRow.LayoutParams( LayoutParams.MATCH_PARENT,  LayoutParams.WRAP_CONTENT,0.5f));   
//					text_html.setPadding (10, 0,8,0);  
//
//					
//					String inhtml = SmiliesConverter.ConverteSmiles(BBcodeToHtmlConverter.bbcode(strings[i],Test.this)); 
//					text_html.setText(inhtml);
//					text_html.setSingleLine(false);
//					row.addView(text_html);
//			
//					if(inhtml.contains("[") )
//						text_html.setTextColor(Color.RED);
////					else 
//						summary = summary+"</br>"+inhtml ;
//					
//					tablel_attributes.addView(row);			
//					
//				
//				}
//				
//				WebView webView = (WebView) findViewById(R.id.webView1);
//				webView.getSettings().setJavaScriptEnabled(true);
//				webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
//				webView.loadUrl("about:blank");
//				webView.loadDataWithBaseURL("fake://not/needed", summary, "text/html","utf-8", "");
//
//				
//		
//			}
//		});
//		
		
//		Button btn2 = (Button) findViewById(R.id.button2);
//		btn2.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				TextProcessor processor = BBProcessorFactory.getInstance().create();
//				
//				summary = "" ; 
//				TableLayout tablel_attributes =(TableLayout) findViewById(R.id.table);
//				tablel_attributes.removeAllViews(); 
//				
//				for (int i = 0; i < strings.length; i++) {
//					TableRow row=new TableRow(Test.this); 
//					row.setLayoutParams(new LayoutParams(
//							LayoutParams.FILL_PARENT,
//							LayoutParams.WRAP_CONTENT));
//					row.setPadding(0,10, 0,10);
//					
//					TextView text_bbcode= new TextView(Test.this); 
//					text_bbcode.setLayoutParams(new android.widget.TableRow.LayoutParams( LayoutParams.MATCH_PARENT,  LayoutParams.WRAP_CONTENT,0.5f));
//					text_bbcode.setTextColor(Color.BLUE); 
//					text_bbcode.setText(strings[i]);
//					row.addView(text_bbcode);
//
//					TextView text_html= new TextView(Test.this); 
//					text_html.setLayoutParams(new android.widget.TableRow.LayoutParams( LayoutParams.MATCH_PARENT,  LayoutParams.WRAP_CONTENT,0.5f));   
//					text_html.setPadding (10, 0,8,0);  
//
//					
//					String inhtml = SmiliesConverter.ConverteSmiles((processor.process(strings[i]))); 
//					text_html.setText(inhtml);
//					text_html.setSingleLine(false);
//					row.addView(text_html);
//			
//					if(inhtml.contains("[") )
//						text_html.setTextColor(Color.RED);
////					else 
//						summary = summary+"</br>"+inhtml ;
//					
//					tablel_attributes.addView(row);			
//				
//				}
//				
//				WebView webView = (WebView) findViewById(R.id.webView1);
//				webView.getSettings().setJavaScriptEnabled(true);
//				webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
//				webView.loadUrl("about:blank");
//				webView.loadDataWithBaseURL("fake://not/needed",summary, "text/html","utf-8", "");
//				
//				
//		
//			}
//		});
//		
		

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
	 
	 
}
