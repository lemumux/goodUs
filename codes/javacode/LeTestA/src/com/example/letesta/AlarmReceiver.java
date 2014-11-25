package com.example.letesta;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


// 接着，你需要一个广播接收的类：
public class AlarmReceiver extends BroadcastReceiver {

	private NotificationManager manager;

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "闹钟提醒!", Toast.LENGTH_SHORT)
		.show();
		//System.out.println("收到广播");
		//Intent it=new Intent(context,ShowMusic.class);
	//	it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//context.startActivity(it);

		
		
		//manager = (NotificationManager) context
		//		.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		//// 例如这个id就是你传过来的
		//String id = intent.getStringExtra("id");
		// MainActivity是你点击通知时想要跳转的Activity
		//Intent playIntent = new Intent(context, MainActivity.class);
		//playIntent.putExtra("id", id);
		//PendingIntent pendingIntent = PendingIntent.getActivity(context, 1,
		//		playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		//NotificationCompat.Builder builder = new NotificationCompat.Builder(
		//		context);
		//builder.setContentTitle("title").setContentText("提醒内容")
		//		.setSmallIcon(R.drawable.folder)
		//		.setDefaults(Notification.DEFAULT_ALL)
		//		.setContentIntent(pendingIntent).setAutoCancel(true)
		//		.setSubText("二级text");
	//	manager.notify(1, builder.build());
		//Toast.makeText(getApplicationContext(), "闹钟提醒!", Toast.LENGTH_SHORT)
		//		.show();
	}
}