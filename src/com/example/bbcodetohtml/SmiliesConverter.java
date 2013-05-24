package com.example.bbcodetohtml;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;

import android.R.string;
import android.content.Context;
import android.util.Log;

/*
 * class to convert bbcode smiles to html images to show it in the browser
 * it's read from json file contain the name of the image and the code of the smily  
 * */
public class SmiliesConverter {
	
	static Map<String , String> smiles = new  HashMap<String , String>(); 
	
	
	public static void readFile(Context context) throws IOException, JSONException {
		
		InputStream is = context.getResources().openRawResource(R.raw.smilies);
		BufferedReader bis = new BufferedReader(new InputStreamReader(is));
		StringBuffer b = new StringBuffer();
		String line = "";
		while((line = bis.readLine()) != null){
			b.append(line);
		}
		bis.close();
		
		JSONArray data = new JSONArray(b.toString());
		
		StringBuffer toursBuffer = new StringBuffer();
		for (int i = 0; i < data.length(); i++) {
			smiles.put(data.getJSONObject(i).getString("SmilyCode"),data.getJSONObject(i).getString("SmilyImage")) ;
			Log.d("koko",data.getJSONObject(i).getString("SmilyCode")+"  " +data.getJSONObject(i).getString("SmilyImage"));
		}
	}
	
	public static String ConverteSmiles (String bbcode) {
        for (Map.Entry entry: smiles.entrySet()) {
        	bbcode =  bbcode.replace((String)entry.getKey(), "<img src=\""+"file:///android_asset/"+entry.getValue().toString()+"\"/>");
        }
        return bbcode ; 
	} 
	
	public void Initial () {
		
	}
	
	
}
