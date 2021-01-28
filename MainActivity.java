package com.example.linkpost;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

@SuppressLint({ "NewApi", "JavascriptInterface" }) 
public class MainActivity extends ActionBarActivity {

	private WebView webView;	
	private WebSettings webSettingviewhist;
	private ProgressBar web_pbarviewhist;	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	ActionBar bar = getActionBar();
    	bar.hide();
    	
        // ������������� �� ���� �����
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);   	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        try{
            webView = (WebView) findViewById(R.id.webView1);
            // TODO ������� ������� ��� �����
            webSettingviewhist = webView.getSettings();
            // TODO ����������� ������� ��� �����
        	webSettingviewhist.setBuiltInZoomControls(false);
        	webView.clearHistory();
        	webView.clearFormData();
        	webView.clearCache(true);
        	webView.setWebViewClient(new WebViewClient()); 
        	
        	webView.setScrollContainer(false);
        	webView.setVerticalScrollBarEnabled(false);
        	webView.setHorizontalScrollBarEnabled(false);
        	webView.setBackgroundColor(Color.TRANSPARENT);
        	
        	web_pbarviewhist = (ProgressBar)findViewById(R.id.progB1ahistory);
        	
        	String url = "http://linkpost.proects.com/";
            webSettingviewhist.setJavaScriptEnabled(true);
            webSettingviewhist.setAllowFileAccess(true);
            webView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
            webView.loadUrl(url);    	
        	
        }catch(Exception w){}       
    }

    // TODO ��������� ����� onStop
    public void onStop() { 
    	finish();
    	super.onStop(); 
    }   
    
    // �������� ����� ������ �� js
    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

    }   
    
	 // TODO ������������ ��������� ��������
    public class WebViewClient extends android.webkit.WebViewClient {
    	
         @Override
         public void onPageStarted(WebView view, String url, Bitmap favicon) {
        	 
        	 // TODO ��� ������ ��������
        	 super.onPageStarted(view, url, favicon);
        	 web_pbarviewhist.setVisibility(View.VISIBLE);
         }
         
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {

          // TODO ���� �������� ����
          view.loadUrl(url);
          return true;
         }
         
         @Override
         public void onReceivedError(WebView view, int errorCode, String description, String Url) {
        	// TODO ������ ����������	
        	 web_pbarviewhist.setVisibility(View.VISIBLE);
             // TODO ���� ������ ��������
         	Toast.makeText(getApplicationContext(), "�� ������� �������� ������ � �������!", Toast.LENGTH_SHORT).show();	 
         }
         
         @Override
         public void onPageFinished(WebView view, String url) {
          // TODO ���� �������� �����������
          super.onPageFinished(view, url);
          web_pbarviewhist.setVisibility(View.GONE);
         }
        }    
}
