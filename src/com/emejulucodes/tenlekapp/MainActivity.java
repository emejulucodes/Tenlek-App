package com.emejulucodes.tenlekapp;

import android.widget.Toast;
import android.widget.SeekBar;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.DialogInterface;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.app.AlertDialog; 
import android.app.NotificationManager;
import android.app.Notification;
import android.view.Menu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.MenuInflater; 
import android.view.MenuItem;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;
    private SeekBar mSeekBar;
private boolean haveNetworkConnection() {
		      boolean haveConnectedWifi = false;
		      boolean haveConnectedMobile = false;

		      ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		      NetworkInfo[] netInfo = cm.getAllNetworkInfo();

		  for (NetworkInfo ni : netInfo) {
		     if (ni.getTypeName().equalsIgnoreCase("WIFI"))
		        if (ni.isConnected())
		            haveConnectedWifi = true;
		    if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
		        if (ni.isConnected())
		            haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
		}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mSeekBar.setProgress(newProgress);
        }


       Toast.makeText(getApplicationContext(),"Welcome!", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 
        Notification notification = new Notification.Builder(MainActivity.this)
       .setContentTitle("Tenlek App")
       .setContentText("Hello, welcome to Tenlek app") 
       .setSmallIcon(R.drawable.notification) 
       .setAutoCancel(true) 
       .build();
       notificationManager.notify(0,notification);
 

        webView = (WebView)findViewById(R.id.web);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        }); 
if(haveNetworkConnection()){
        webView.getSettings()
        .setJavaScriptEnabled(true);
      mSeekBar = (SeekBar)findViewById(R.id.web_sbr);

        webView.loadUrl("https://tenlekeschool.com");
  
        } else {
            webView.loadUrl("file:///android_asset/error.html");
           }
  }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        
				AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this); 
                  alert.setTitle("Exit");//Title 
                   alert.setMessage("Are you sure you want to Exit");//message 
                   alert.setIcon(R.drawable.logo);//Icon 
                   //Yes Button 
                  alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
                       @Override 
                      public void onClick(DialogInterface dialogInterface, int i) { 
                          finish(); 
    
                      } 
                   }); 
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() { 
                     @Override 
                      public void onClick(DialogInterface dialogInterface, int i) { 
                         dialogInterface.cancel(); 
                     } 
                 }); 
                   alert.create(); 
                  alert.show(); 
              
				return true;
	
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.layout.menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.About:

                Dialog dia = new Dialog(MainActivity.this);
                dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dia.getWindow()
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dia.setContentView(R.layout.about);
                dia.setTitle("About");
                dia.show();


			return true;
			case R.id.Exit:

				AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this); 
                   alert.setTitle("Exit");//Title 
                   alert.setMessage("Are you sure you want to Exit");//message 
                   alert.setIcon(R.drawable.logo);//Icon 
                   //Yes Button 
                   alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
                       @Override 
                      public void onClick(DialogInterface dialogInterface, int i) { 
                          MainActivity.this.finish(); 
    
                      } 
                   }); 
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() { 
                     @Override 
                      public void onClick(DialogInterface dialogInterface, int i) { 
                         dialogInterface.cancel(); 
                     } 
                 }); 
                   alert.create(); 
                  alert.show(); 
              
				return true;

    case R.id.Dev:

                Intent i = new Intent(MainActivity.this, DevInfo.class);
                startActivity(i);
               
 return true;

   case R.id.Share:

Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Learn a lot at tenlek E-school");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://tenlekeschool.com");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
  return true;

	}
		return super.onOptionsItemSelected(item);
	}
}