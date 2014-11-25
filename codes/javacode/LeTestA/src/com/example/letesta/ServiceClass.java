package com.example.letesta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServiceClass extends Service {
	private static final String dicpath = "/storage/sdcard0/Lemumu";
	private static final String INFORS_PATH = dicpath + "/infors.xml";
	private String path;
	private String time;
	private String nullString = "111";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("Testing", "Service got created");
		// Toast.makeText(this, "ServiceClass.onCreate()³É¹¦¡£¡£¡£",
		// Toast.LENGTH_LONG).show();
		// Uri murl=Uri.parse("content://com.example.letesta/people");
		xmlPullParseXML();
		Log.d(path, "Service got created");
		if (!path.equals(nullString)) {
			try {
				MediaPlayer mediaPlayer = new MediaPlayer();
				mediaPlayer.setDataSource(path);
				mediaPlayer.start();
			} catch (Exception e) {

			}
		}
		// mediaPlayer.pause();
	}

	private void xmlPullParseXML() {
		File linceseFile = new File(INFORS_PATH);
		if (!linceseFile.exists()) {
			path = nullString;
			time = nullString;
		} else {
			try {
				XmlPullParserFactory factory = XmlPullParserFactory
						.newInstance();
				XmlPullParser xmlPullParser = factory.newPullParser();
				InputStream inputStream = new FileInputStream(new File(
						INFORS_PATH));
				xmlPullParser.setInput(inputStream, "UTF-8");

				int eventType = xmlPullParser.getEventType();
				try {
					while (eventType != XmlPullParser.END_DOCUMENT) {
						String nodeName = xmlPullParser.getName();
						switch (eventType) {
						case XmlPullParser.START_TAG:
							if ("path".equals(nodeName)) {
								path = xmlPullParser.nextText();
							} else if ("time".equals(nodeName)) {
								time = xmlPullParser.nextText();
							}
							break;
						default:
							break;
						}
						eventType = xmlPullParser.next();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Toast.makeText(this, "ServiceClass.onStart()", Toast.LENGTH_LONG)
				.show();
		Log.d("Testing", "Service got started");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
