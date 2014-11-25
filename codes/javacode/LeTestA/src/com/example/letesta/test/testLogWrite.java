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
	     * 初始设置
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
	     * 垃圾清理与资源回收
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
	     * 活动功能测试
	     */
	    public void testActivity() throws Exception {
	       // Log.v("testActivity", "test the Activity");
	        SystemClock.sleep(1500);
	        //getInstrumentation().runOnMainSync(new PerformClick(button));
	        SystemClock.sleep(3000);
	      //  assertEquals("Hello Android", text.getText().toString());
	    }
	  
//	    /*
//	     * 模拟按钮点击的接口
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
	     * 测试类中的方法
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
