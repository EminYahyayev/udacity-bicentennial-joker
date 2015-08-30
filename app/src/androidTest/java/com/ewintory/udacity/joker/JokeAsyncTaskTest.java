package com.ewintory.udacity.joker;

import android.test.AndroidTestCase;

import com.ewintory.udacity.joker.taks.JokeAsyncTask;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public final class JokeAsyncTaskTest extends AndroidTestCase {

    private final CountDownLatch mCountDownLatch = new CountDownLatch(1);
    private String mJoke;

    public void testVerifyNonEmptyResponse() {
        JokeAsyncTask task = new JokeAsyncTask(new JokeAsyncTask.Callback() {
            @Override public void onJokeSuccess(String joke) {
                mJoke = joke;
                mCountDownLatch.countDown();
            }

            @Override public void onJokeError(IOException e) {
                fail(e.getMessage());
            }
        });
        task.execute();

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS);
            assertNotNull("We have a joke: ", mJoke);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }
    }
}