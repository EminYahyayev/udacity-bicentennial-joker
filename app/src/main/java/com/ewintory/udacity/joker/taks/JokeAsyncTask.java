/*
 * Copyright 2015.  Emin Yahyayev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ewintory.udacity.joker.taks;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.ewintory.udacity.joker.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.lang.ref.WeakReference;


public final class JokeAsyncTask extends AsyncTask<Void, Void, Pair<IOException, String>> {
    private static String TAG = JokeAsyncTask.class.getSimpleName();

    private static MyApi myApiService = null;
    private WeakReference<Callback> mCallback;

    public JokeAsyncTask(Callback c) {
        mCallback = new WeakReference<>(c);
    }

    @Override
    protected Pair<IOException, String> doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://bicentennial-joker.appspot.com/_ah/api/");
                    // options for running against local devappserver
                    // - 10.0.3.3 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
//                    .setRootUrl("http://10.0.3.3:8080/_ah/api/")
//                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
//                        @Override
//                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
//                            abstractGoogleClientRequest.setDisableGZipContent(true);
//                        }
//                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        try {
            Log.d(TAG, "Executing.");
            String joke = myApiService.tellJoke().execute().getData();
            Log.d(TAG, "Executed.");
            return new Pair<>(null, joke);
        } catch (IOException ex) {
            return new Pair<>(ex, null);
        }
    }

    @Override
    protected void onPostExecute(Pair<IOException, String> result) {
        if (mCallback == null || mCallback.get() == null) {
            Log.w(TAG, "Task is detached.");
            return;
        }

        if (result.first != null) {
            mCallback.get().onJokeError(result.first);
            Log.e(TAG, "Exception: " + result.first.getMessage());
        } else if (result.second != null) {
            mCallback.get().onJokeSuccess(result.second);
            Log.d(TAG, "Joke: " + result.second);
        }
    }

    public interface Callback {
        void onJokeSuccess(String joke);

        void onJokeError(IOException e);
    }
}
