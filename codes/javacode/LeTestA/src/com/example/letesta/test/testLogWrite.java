package com.example.letesta.test;

   
    
import com.example.letesta.LogWrite;
import android.content.Intent;
import android.os.SystemClock;
import android.test.ActivityUnitTestCase;
import android.test.InstrumentationTestCase;
import android.util.Log;
 
 public class testLogWrite  extends InstrumentationTestCase  {    
	 private LogWrite sample = null;  
	    /*
	     * ��ʼ����
	     * @see junit.framework.TestCase#setUp()
	     */
	    @Override
	    protected void setUp()  {
	        try {
	            super.setUp();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        Intent intent = new Intent(); 
	        //sample = (Sample) getInstrumentation().startActivitySync(intent);
	        sample=new LogWrite();
	    }
	  
	    /*
	     * ������������Դ����
	     * @see android.test.InstrumentationTestCase#tearDown()
	     */
	    @Override
	    protected void tearDown()  {
	        sample.finish();
	        try {
	            super.tearDown();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	    /*
	     * ����ܲ���
	     */
	    public void testActivity() throws Exception {
	       // Log.v("testActivity", "test the Activity");
	        SystemClock.sleep(1500);
	        //getInstrumentation().runOnMainSync(new PerformClick(button));
	        SystemClock.sleep(3000);
	      //  assertEquals("Hello Android", text.getText().toString());
	    }
	  
//	    /*
//	     * ģ�ⰴť����Ľӿ�
//	     */
//	    private class PerformClick implements Runnable {
//	        Button btn;
//	        public PerformClick(Button button) {
//	            btn = button;
//	        }
//	  
//	        public void run() {
//	            btn.performClick();
//	        }
//	    }
	  
	    /*
	     * �������еķ���
	     */
	    public void testAdd() throws Exception{
	        String tag = "testAdd";
	        sample.write(tag);
	        Log.v(tag, "test the method");
	        //Log.v(tag, "test the method");
	       // int test = sample.add(1, 1);
	       // assertEquals(2, test);
	    }

 }   
