package com.example.letesta;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;


// ���ţ�����Ҫһ���㲥���յ��ࣺ
public class AlarmReceiver extends BroadcastReceiver {

	private NotificationManager manager;

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "��������!", Toast.LENGTH_SHORT)
		.show();
		//System.out.println("�յ��㲥");
		//Intent it=new Intent(context,ShowMusic.class);
	//	it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//context.startActivity(it);

		
		
		//manager = (NotificationManager) context
		//		.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		//// �������id�����㴫������
		//String id = intent.getStringExtra("id");
		// MainActivity������֪ͨʱ��Ҫ��ת��Activity
		//Intent playIntent = new Intent(context, MainActivity.class);
		//playIntent.putExtra("id", id);
		//PendingIntent pendingIntent = PendingIntent.getActivity(context, 1,
		//		playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		//NotificationCompat.Builder builder = new NotificationCompat.Builder(
		//		context);
		//builder.setContentTitle("title").setContentText("��������")
		//		.setSmallIcon(R.drawable.folder)
		//		.setDefaults(Notification.DEFAULT_ALL)
		//		.setContentIntent(pendingIntent).setAutoCancel(true)
		//		.setSubText("����text");
	//	manager.notify(1, builder.build());
		//Toast.makeText(getApplicationContext(), "��������!", Toast.LENGTH_SHORT)
		//		.show();
	}
}