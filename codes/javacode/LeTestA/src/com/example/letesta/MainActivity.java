package com.example.letesta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.http.util.EncodingUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

 
import android.R.string;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.Element;
import android.support.v4.app.NotificationCompat;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private Button timesetButton1;
	private Button timesetButton2;
	private Button musicPlay; 
	private TimePicker timepicker1;
	private TextView textview1;
	private String timehour;
	private String timemin;
	private ListView listView1;
	private String pathdd = "/storage/sdcard0";
	private ArrayList<Map<String, Object>> data;
	private String musicPath;
	private String dataPath = "LeData.txt";
	private static final String dicpath = "/storage/sdcard0/1Lemumu";
	private static final String INFORS_PATH = dicpath + "/infors.xml";
	private String path;
	private String time;
	private MediaPlayer mp;
	private String nullString = "111";
    private int stateS=0;
    private MediaController mediaC;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		timesetButton1 = (Button) findViewById(R.id.settime_button1);
		timesetButton2 = (Button) findViewById(R.id.settime_buttonsubmit);
		musicPlay= (Button) findViewById(R.id.buttonplay1);
		timepicker1 = (TimePicker) findViewById(R.id.timePicker1);
		textview1 = (TextView) findViewById(R.id.textview1);
		listView1 = (ListView) findViewById(R.id.listView1);
		timepicker1.setIs24HourView(true);
		// ����MediaPlayer����,��raw�ļ����µ�lovefool.mp3 28.
		// mp = MediaPlayer.create(this, Uri.fromFile(new File(path)));
		timepicker1
				.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

					@Override
					public void onTimeChanged(TimePicker view, int hourOfDay,
							int minute) {
						// TODO Auto-generated method stub
						// ʱ��ı䴦��
						timehour = hourOfDay > 9 ? String.valueOf(hourOfDay)
								: "0" + String.valueOf(hourOfDay);
						timemin = minute > 9 ? String.valueOf(minute) : "0"
								+ String.valueOf(minute);
						// c.set(year,month,hourOfDay,minute,second);
						stateS=1;
					}
				});

		timesetButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timepicker1.setVisibility(View.VISIBLE); 
			}
		});
		timesetButton2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
               if(stateS==1)
               {
				// TODO Auto-generated method stub
				timepicker1.setVisibility(View.GONE);
				textview1.setText(timehour + ":" + timemin);
				time = timehour + ":" + timemin;
				createXmlFile(path, time);
				canclealarm();
				setalarm();
               }
               stateS=0;
			}
		});
		musicPlay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				palyMusic();
			}
		});
		// inituserdata();
		xmlPullParseXML();
		createXmlFile(path, time);
		initData(pathdd);
		// listView1
		listView1.setOnItemClickListener(this);
		//setalarm();
		setAlarmClick();
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			// �˴�д������¼�
			pathdd = pathdd.substring(0, pathdd.lastIndexOf("/"));
			if (pathdd.startsWith("/storage")) {
				initData(pathdd);
				canclealarm();
				setalarm();
				Toast.makeText(this, "ddd", Toast.LENGTH_LONG).show();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public ArrayList<Map<String, Object>> getData11(String path) {
		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> dataa = new HashMap<String, Object>();
		dataa.put("content", "ddddddddddd");
		dataa.put("image", R.drawable.folder);
		data.add(dataa);
		return data;
	}

	public int getData(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		if (files.length == 0) {
			return 0;
		}
		data = new ArrayList<Map<String, Object>>();
		for (File currentFile : files) {
			String fileName = currentFile.getName();
			int drawN = R.drawable.folder;
			HashMap<String, Object> dataa = new HashMap<String, Object>();
			// �ж���һ���ļ��л���һ���ļ�
			if (currentFile.isDirectory()) {
				drawN = R.drawable.folder;
			} else { // ȡ���ļ���
				// �����ļ������ж��ļ����ͣ����ò�ͬ��ͼ��
				if (fileName.endsWith(".mp3")) {
					drawN = R.drawable.musical24;
				} else {
					drawN = R.drawable.documents32;
				}
			}
			dataa.put("content", fileName);
			dataa.put("image", drawN);
			data.add(dataa);
		}
		return files.length;
	}

	// ��ʼ��������
	private void initData(String path) {
		if (listView1 == null)
			return;
		if (getData(path) == 0) {
			return;
		}
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.activity_a,
				new String[] { "content", "image" }, new int[] {
						R.id.item_content, R.id.item_icon });

		listView1.setAdapter(sa);
		ArrayAdapter<String> adapter;
		ArrayList<String> data = new ArrayList<String>();

		for (int i = 0; i < 10; i++) {
			data.add(" " + i);
		}
		adapter = new ArrayAdapter<String>(this, R.layout.activity_a, data);
		// listView1.setAdapter(adapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String tex = "";
		// Toast.makeText(
		// this,
		// "position:" + position + "  item:"
		// + parent.getItemAtPosition(position).toString() ,

		// Toast.LENGTH_LONG).show();
		// adapter.getItem(position)
		@SuppressWarnings("unchecked")
		Map<String, Object> maps = (Map<String, Object>) parent
				.getItemAtPosition(position);
		tex = maps.get("content").toString();
		if (maps.get("image").equals(R.drawable.folder)) {

			pathdd += "/" + tex;
			initData(pathdd);
		} else if (maps.get("image").equals(R.drawable.musical24)) {
			musicPath = pathdd + "/" + tex;
			// setData(musicPath);
			// saveUserInfo(musicPath);
			path = musicPath;
			createXmlFile(path, time);
		}
		Toast.makeText(this, musicPath, Toast.LENGTH_LONG).show();
	}

	// ����ѡ����ļ���ַ
	@SuppressWarnings("deprecation")
	private void setData(String data) {
		String res = "";
		try {

			// �õ���Դ�е�Raw������
			InputStream in = getResources().getAssets().open(dataPath);
			// �õ����ݵĴ�С
			int length = in.available();
			byte[] buffer = new byte[length];
			// ��ȡ����
			in.read(buffer);
			// ��test.txt�ı�������ѡ����ʵı��룬���������������
			res = EncodingUtils.getString(buffer, "BIG5");
			// �ر�
			in.close();

			res = data;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// xmlPullParser����xml�ļ�
	private void writeXmlPullParseXML(String path, String time) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser xmlPullParser = factory.newPullParser();

			xmlPullParser.setInput(Thread.currentThread()
					.getContextClassLoader().getResourceAsStream(INFORS_PATH),
					"UTF-8");

			int eventType = xmlPullParser.getEventType();
			try {
				while (eventType != XmlPullParser.END_DOCUMENT) {
					String nodeName = xmlPullParser.getName();
					switch (eventType) {
					case XmlPullParser.START_TAG:
						if ("path".equals(nodeName)) {
						} else if ("time".equals(nodeName)) {

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
		}

	}

	private boolean saveuserdata(string key, string value) {
		try {
			File f = new File(Environment.getExternalStorageDirectory(),
					"info.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			NodeList nl = doc.getElementsByTagName("VALUE");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.print("���ƺ���:"
						+ doc.getElementsByTagName("NO").item(i)
								.getFirstChild().getNodeValue());
				System.out.println("������ַ:"
						+ doc.getElementsByTagName("ADDR").item(i)
								.getFirstChild().getNodeValue());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean inituserdata() {

		try {
			Document doc;
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element root = (Element) doc.createElement("Result");
			doc.appendChild((Node) root);
			Element path = (Element) doc.createElement("Path");
			Element name = (Element) doc.createElement("Name");
			((Node) root).appendChild((Node) path);
			((Node) root).appendChild((Node) name);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream("info.xml"));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			System.out.println("����XML�ļ��ɹ�!");
		} catch (TransformerConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (TransformerException e) {
			System.out.println(e.getMessage());
		} catch (ParserConfigurationException e) {

			System.out.println(e.getMessage());

		}
		return true;

	}

	// ����xml�ļ�
	private void createXmlFile(String path, String time) {
		File destDir = new File(dicpath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		File linceseFiled = new File(INFORS_PATH);
		if (!linceseFiled.exists()) {
			linceseFiled.delete();
		}
		File linceseFile = new File(INFORS_PATH);
		try {
			linceseFile.createNewFile();
		} catch (IOException e) {
			Log.e("IOException", "exception in createNewFile() method");
		}
		FileOutputStream fileos = null;
		try {
			fileos = new FileOutputStream(linceseFile);
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "can't create FileOutputStream");
		}
		XmlSerializer serializer = Xml.newSerializer();
		try {
			serializer.setOutput(fileos, "UTF-8");
			serializer.startDocument(null, true);
			serializer.startTag(null, "infors");
			serializer.startTag(null, "infor");
			serializer.startTag(null, "path");
			serializer.text(path);
			serializer.endTag(null, "path");
			serializer.startTag(null, "time");
			serializer.text(time);
			serializer.endTag(null, "time");
			serializer.endTag(null, "infor");
			serializer.endTag(null, "infors");
			serializer.endDocument();
			serializer.flush();
			fileos.close();
		} catch (Exception e) {
			Log.e("Exception", "error occurred while creating xml file");
		}
		// Toast.makeText(getApplicationContext(), "����xml�ļ��ɹ�!",
		// Toast.LENGTH_SHORT).show();

	}

	// xmlPullParser����xml�ļ�
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

	private void setalarm() {
		if (!time.equals(nullString)) {
			int hour=  Integer.parseInt(time.substring(0, time.lastIndexOf(":")));
			int min=  Integer.parseInt(time.substring(time.lastIndexOf(":")+1));
			Toast.makeText(getApplicationContext(), String.valueOf(hour)+"."+String.valueOf(min), Toast.LENGTH_SHORT)
			.show();
			// ������������
			Calendar alendar = Calendar.getInstance();
			alendar.set(Calendar.YEAR, alendar.get(Calendar.YEAR));
			alendar.set(Calendar.MONTH, alendar.get(Calendar.MONTH));
			alendar.set(Calendar.DAY_OF_MONTH,
					alendar.get(Calendar.DAY_OF_MONTH));
			alendar.set(Calendar.HOUR_OF_DAY, hour);
			alendar.set(Calendar.MINUTE, min);
			alendar.set(Calendar.SECOND, 0);
			Calendar nowss = Calendar.getInstance();
			long alongtime = nowss.getTimeInMillis();
			int acout = 111;
			Intent intent = new Intent("cn.pocketdigi.update.alarm");
			intent.setAction("something");
			intent.setType("something");
			intent.setData(Uri.EMPTY);
			intent.addCategory("something");
			intent.setClass(this, MainActivity.class);
			// ���ϸ�intent���õ��ĸ����������������㷢��ϵͳ����������ģ�������ȡ����֮ǰ���������������ĸ����ԣ������ϸ���ȣ���������ҪһЩ�Ƚ϶��ص����ԣ�������������ظ����json��ĳЩ�ض��ֶΡ�
			// ��Ȼintent��Ҳ���Է�һЩ��Ҫ���ݵ���Ϣ��
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					this, 0, intent, 0);
			// alarmCount������Ҫ��¼���������������뱣֤��������alarmCount������ͬ�����һ��������0�Ϳ��ԡ�
			AlarmManager am = (AlarmManager) MainActivity.this
					.getSystemService(Context.ALARM_SERVICE);
			am.set(AlarmManager.RTC_WAKEUP, alongtime, pendingIntent);
			// �������ӵ�����ͷ��ͳ�ȥ�ˡ�time����Ҫ�����ѵ�ʱ�䣬��λ���룬ע�ⲻ��ʱ����һ���������ѵ��������Ҹ����ľͿ��ԣ�����Ȥ�����ѣ�����ȥgoogleһ�£��ⷽ������Ϸǳ��࣬һ�����֣���һ�¾�֪�������ˡ�
		
			mediaC=new MediaController(this);
		
		
		}
	}
	
	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;
	private void setAlarmClick(){
		
		
		Calendar cur_cal = Calendar.getInstance();
		cur_cal.setTimeInMillis(System.currentTimeMillis());
		cur_cal.add(Calendar.SECOND, 50);
		Log.d("Testing", "Calender Set time:" + cur_cal.getTime());
		
		Intent intent = new Intent(MainActivity.this, ServiceClass.class);
		PendingIntent pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);
		AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(), 10*1000, pintent);
		
		 
		Log.d("Testing", "Intent created");  
		Log.d("Testing", "alarm manager set");
		Toast.makeText(this, "ServiceClass.onCreate()", Toast.LENGTH_LONG)
				.show();

	}

	private void canclealarm() {
		// ȡ����������
		int acout = 111;
		Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
		intent.setAction("something");
		intent.setType("something");
		intent.setData(Uri.EMPTY);
		intent.addCategory("something");
		intent.setClass(MainActivity.this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				MainActivity.this, acout, intent, 0);
		// alarmCount��Ӧ�����趨ʱ��alarmCount,
		AlarmManager am = (AlarmManager) MainActivity.this
				.getSystemService(Context.ALARM_SERVICE);
		am.cancel(pendingIntent);
	}
  
	private void palyMusic()
	{
		Log.d(path, "Service got created");
        String mpath=getPath();
        if(!mpath.equals(nullString))
        {
        	  MediaPlayer mediaPlayer1 = MediaPlayer.create(this,R.raw.reg);
        	  mediaPlayer1.start(); 
        }
		
	}
	
	private String getPath() {
		String returnString = nullString;
		File linceseFile = new File(INFORS_PATH);
		if (!linceseFile.exists()) { 
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
								returnString = xmlPullParser.nextText();
							} else if ("time".equals(nodeName)) { 
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
		return returnString; 
	}

	
	
}
