package com.example.bbcodetohtml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.content.Context;

public class BBcodeToHtmlConverter {

    
    public static String bbcode(String text, Context context) {
    	
    			
     			
         String temp = text;
         Map<String , String> bbMap = new HashMap<String , String>();
         
         bbMap.put("\\[br\\]", "</br>");
         bbMap.put("\\[b\\](.+?)\\[/b\\]", "<b>$1</b>");
         bbMap.put("\\[s\\](.+?)\\[/s\\]", "<s>$1</s>");
         bbMap.put("\\[i\\](.+?)\\[/i\\]",  "<i>$1</i>");
         bbMap.put("\\[u\\](.+?)\\[/u\\]",  "<u>$1</u>");
         bbMap.put("\\[h1\\](.+?)\\[/h1\\]", "<h1>$1</h1>");
         bbMap.put("\\[h2\\](.+?)\\[/h2\\]", "<h2>$1</h2>");
         bbMap.put("\\[h3\\](.+?)\\[/h3\\]", "<h3>$1</h3>");
         bbMap.put("\\[h4\\](.+?)\\[/h4\\]", "<h4>$1</h4>");
         bbMap.put("\\[h5\\](.+?)\\[/h5\\]", "<h5>$1</h5>");
         bbMap.put("\\[h6\\](.+?)\\[/h6\\]", "<h6>$1</h6>");
         bbMap.put("\\[code\\](.+?)\\[/code\\]", "<pre>$1</pre>");
         
         bbMap.put("\\[quote\\](.+?)\\[/quote\\]", "<blockquote>$1</blockquote>");
         bbMap.put("\\[quote\\=(.+?)\\](.+?)\\[/quote\\]","<blockquote>$1 write : </br>$2</blockquote>");
         
         bbMap.put("\\[font\\=(.+?)\\](.+?)\\[/font\\]", "<span style=\"font-family:$1;\">$2</span>");
         bbMap.put("\\[background\\=(.+?)\\](.+?)\\[/background\\]", "<span style=\"background:$1\">$2</span>");
         bbMap.put("\\[center\\](.+?)\\[/center\\]", "<div align=\"center\">$1</div>");
         bbMap.put("\\[left\\](.+?)\\[/left\\]", "<p style=\"display:block; text-align:left\">$1</p>");
         bbMap.put("\\[right\\](.+?)\\[/right\\]", "<p style=\"display:block; text-align:right\">$1</p>");
         bbMap.put("\\[center\\](.+?)\\[/center\\]", "<p style=\"display:block; text-align:center;\">$1</p>");
         bbMap.put("\\[rtl\\](.+?)\\[/rtl\\]", "<div style=\"display:block; dir:rtl\">$1</div>");
         bbMap.put("\\[ltr\\](.+?)\\[/ltr\\]", "<div style=\"display:block; dir:ltr\">$1</div>");
         bbMap.put("\\[align\\=(.+?)\\](.+?)\\[/align\\]", "<div align=\"$1\">$2</div>");
         bbMap.put("\\[color\\=(.+?)\\](.+?)\\[/color\\]", "<span style=\"color:$1\">$2</span>");
         bbMap.put("\\[size\\=([0-9]+?)\\](.+?)\\[/size\\]", "<span style=\"font-size:$1em;\">$2</span>");
         
         bbMap.put("\\[img\\](.+?)\\[/img\\]", "<img src=\"$1\" />");
         bbMap.put("\\[img\\=(.+?),(.+?)\\](.+?)\\[/img\\]", "<img width=\"$1\" height=\"$2\" src=\"$3\" />");
         bbMap.put("\\[email\\](.+?)\\[/email\\]", "<a href=\"mailto:$1\">$1</a>");
         bbMap.put("\\[email\\=(.+?)\\](.+?)\\[/email\\]", "<a href=\"mailto:$1\">$2</a>");
         bbMap.put("\\[url\\](.+?)\\[/url\\]", "<a href=\"$1\">$1</a>");
         bbMap.put("\\[url\\=(.+?)\\](.+?)\\[/url\\]", "<a href=\"$1\">$2</a>");
         bbMap.put("\\[video\\](.+?)\\[/video\\]", "<video src=\"$1\" />");
         
        	
       
         temp = temp.replaceAll("\\n", "</br>");
         
         for (Map.Entry entry: bbMap.entrySet()) {
             temp = temp.replaceAll((String)entry.getKey(), entry.getValue().toString());
         }
         
         temp = temp.replaceAll("\\[list\\]\\[\\*\\](.+?)\\[/list\\]","<ul><li>$1</li></ul>");
         
         temp = temp.replaceAll("\\[list\\=([0-9]+?)\\]\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: decimal\"><li>$2</li></ol>");
         
         temp = temp.replaceAll("\\[list\\=(i)\\]\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: lower-roman\"><li>$2</li></ol>");
         temp = temp.replaceAll("\\[list\\=(I)\\]\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: upper-roman\"><li>$2</li></ol>");
         temp = temp.replaceAll("\\[list\\=([a-z]+?)\\]\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: lower-alpha\"><li>$2</li></ol>");
         temp = temp.replaceAll("\\[list\\=([A-Z]+?)\\]\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: upper-alpha\"><li>$2</li></ol>");
 
         
         temp = temp.replaceAll("\\[list\\](.+?)\\[\\*\\](.+?)\\[/list\\]","<ul><li>$2</li></ul>");
         temp = temp.replaceAll("\\[list\\=([0-9]+?)\\](.+?)\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: decimal\"><li>$3</li></ol>");
         temp = temp.replaceAll("\\[list\\=(i)\\](.+?)\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: lower-roman\"><li>$3</li></ol>");
         temp = temp.replaceAll("\\[list\\=(I)\\](.+?)\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: upper-roman\"><li>$3</li></ol>");
         temp = temp.replaceAll("\\[list\\=([a-z]+?)\\](.+?)\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: lower-alpha\"><li>$3</li></ol>");
         temp = temp.replaceAll("\\[list\\=([A-Z]+?)\\](.+?)\\[\\*\\](.+?)\\[/list\\]","<ol style=\"list-style-type: upper-alpha\"><li>$3</li></ol>");
 
         
         temp = temp.replaceAll("\\[\\*\\](.+?)","</li><li>$1");

        return temp;
        
   		}
   	    
    
    
}