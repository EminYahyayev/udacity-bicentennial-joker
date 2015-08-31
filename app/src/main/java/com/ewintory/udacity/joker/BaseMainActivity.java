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

package com.ewintory.udacity.joker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ewintory.udacity.joker.taks.JokeAsyncTask;
import com.ewintory.udacity.joker.views.JokeActivity;

import java.io.IOException;

public abstract class BaseMainActivity extends AppCompatActivity
        implements JokeAsyncTask.Callback {

    private ProgressBar mProgressBar;
    private JokeAsyncTask mAsyncTask;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void tellJoke(View view) {
        loadJoke();
    }

    protected final void loadJoke(){
        mProgressBar.setVisibility(View.VISIBLE);
        if (mAsyncTask != null) {
            mAsyncTask.cancel(true);
        }
        mAsyncTask = new JokeAsyncTask(this);
        mAsyncTask.execute();
    }

    @Override
    public void onJokeSuccess(String joke) {
        mProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(this, JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE, joke);
        startActivity(intent);
    }

    @Override
    public void onJokeError(IOException e) {
        mProgressBar.setVisibility(View.GONE);
        showToast(e.getMessage());
    }

    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
