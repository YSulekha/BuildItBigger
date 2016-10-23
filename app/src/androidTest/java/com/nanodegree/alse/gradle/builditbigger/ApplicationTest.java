package com.nanodegree.alse.gradle.builditbigger;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ApplicationTestCase;
import android.text.TextUtils;
import android.util.Log;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    CountDownLatch countdown;
    String joke;
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        countdown = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        countdown.countDown();
    }
    @Test
    public void testJokeTask() throws InterruptedException{
        MyBackendAsyncTask task = new MyBackendAsyncTask();
        task.setListener(new MyBackendAsyncTask.TaskCompleteListener() {
            @Override
            public void onComplete(String s) {
                Log.v("JokeFromTest2",s);
                joke = s;
                countdown.countDown();

            }
        }).execute(new Pair<Context, String>(getContext(),"TestAn"));
        countdown.await();
        assertTrue(!TextUtils.isEmpty(joke));


    }
}
