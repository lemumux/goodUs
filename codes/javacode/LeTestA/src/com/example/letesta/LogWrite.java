package com.example.letesta;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.AssetManager;
import android.view.Menu;
import android.widget.TextView;

public class LogWrite extends Activity{
	public static String fileName=getDate()+".tex";
	
	public static void write(String text) {
		try {
			File f = new File(android.os.Environment.getExternalStorageDirectory()+"/"+fileName); 
			FileOutputStream fileOS=new FileOutputStream(f);
			fileOS.write(text.getBytes());
			fileOS.close();
			//BufferedWriter buf = new BufferedWriter (new OutputStreamWriter(fileOS));
			//buf.write(str,0,str.length());
			//buf.flush();
			//buf.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static String getDate()
	{
		String ret="";
		Calendar alendar = Calendar.getInstance();
		ret+= alendar.get(Calendar.YEAR);
		ret+= alendar.get(Calendar.MONTH)<10?"0"+alendar.get(Calendar.MONTH):alendar.get(Calendar.MONTH);
		ret+= alendar.get(Calendar.DAY_OF_MONTH)<10?"0"+alendar.get(Calendar.DAY_OF_MONTH):alendar.get(Calendar.DAY_OF_MONTH);
		return ret;
	}
}