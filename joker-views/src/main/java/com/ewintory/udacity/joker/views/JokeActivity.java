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

package com.ewintory.udacity.joker.views;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public final class JokeActivity extends AppCompatActivity {
    private static final String TAG = JokeActivity.class.getSimpleName();

    public static final String EXTRA_JOKE = "com.ewintory.udacity.joker.views.EXTRA_JOKE";

    private TextView mJokeView;
    private String mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mJoke = getIntent().getStringExtra(EXTRA_JOKE);
        if (TextUtils.isEmpty(mJoke)) {
            Log.e(TAG, "One must specify the joke for JokeActivity");
            finish();
            return;
        }

        setContentView(R.layout.activity_joke);
        mJokeView = (TextView) findViewById(R.id.joke);
        mJokeView.setText(mJoke);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            super.onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }
}
