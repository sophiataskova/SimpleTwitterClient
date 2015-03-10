package com.sophiataskova.apps.smartshoppinglist.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sophiataskova.apps.smartshoppinglist.TwitterApplication;
import com.sophiataskova.apps.smartshoppinglist.TwitterClient;
import com.sophiataskova.apps.smartshoppinglist.fragments.UserTimelineFragment;
import com.sophiataskova.apps.smartshoppinglist.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONObject;

public class ProfileActivity extends ActionBarActivity {
    TwitterClient client;
    User user;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.sophiataskova.apps.smartshoppinglist.R.layout.activity_profile);
        username = getIntent().getStringExtra("username");


        String screenName = getIntent().getStringExtra("screen_name");
        if (savedInstanceState == null) {
            UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance("test screen name");
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(com.sophiataskova.apps.smartshoppinglist.R.id.fl_container, userTimelineFragment);
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        client = TwitterApplication.getRestClient();
        if (username == null) {
            client.getUserInfo(new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    user = User.fromJSON(response);
                    getSupportActionBar().setTitle("@" + user.getScreenName());
                    populateProfileHeader(user);
                }
            });
        } else {
            client.userLookup(username, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    user = User.fromJSON(response);
                    getSupportActionBar().setTitle("@" + user.getScreenName());
                    populateProfileHeader(user);
                    UserTimelineFragment userTimelineFragment = UserTimelineFragment.newInstance(username);
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(com.sophiataskova.apps.smartshoppinglist.R.id.fl_container, userTimelineFragment);
                    ft.commit();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.i("DEBUG", errorResponse.toString());
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        }
        super.onResume();
    }

    private void populateProfileHeader(User user) {
        TextView tvName = (TextView) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.tv_name);
        TextView tvTagline = (TextView) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.tv_tagline);
        TextView tvFollowers = (TextView) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.tv_followers);
        TextView tvFollowing = (TextView) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.tv_following);
        ImageView ivProfileImage = (ImageView) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.iv_profile_image);
        tvName.setText(user.getName());
        tvTagline.setText(user.getTagLine());
        tvFollowers.setText(user.getFollowersCount() + " followers");
        tvFollowing.setText(user.getFollowingCount() + " following");
        Picasso.with(this).load(user.getProfilePicUrl()).into(ivProfileImage);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.sophiataskova.apps.smartshoppinglist.R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.sophiataskova.apps.smartshoppinglist.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
