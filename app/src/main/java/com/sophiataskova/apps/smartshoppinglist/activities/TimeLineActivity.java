package com.sophiataskova.apps.smartshoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.sophiataskova.apps.smartshoppinglist.TwitterClient;
import com.sophiataskova.apps.smartshoppinglist.fragments.HomeTimelineFragment;
import com.sophiataskova.apps.smartshoppinglist.fragments.MentionsTimelineFragment;
import com.sophiataskova.apps.smartshoppinglist.fragments.TweetsListFragment;
import com.sophiataskova.apps.smartshoppinglist.models.Tweet;
import com.sophiataskova.apps.smartshoppinglist.models.TweetModel;
import com.sophiataskova.apps.smartshoppinglist.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.List;

public class TimeLineActivity extends ActionBarActivity implements TweetDialogFragment.ComposeTweetDialogListener {

    private TweetsListFragment mTweetsListFragment;
    private TweetDialogFragment mComposeDialog;
    private TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.sophiataskova.apps.smartshoppinglist.R.layout.activity_time_line);

        ViewPager vpPager = (ViewPager) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.viewpager);
        vpPager.setAdapter(new TweetsPagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(com.sophiataskova.apps.smartshoppinglist.R.id.tabs);
        pagerSlidingTabStrip.setViewPager(vpPager);
    }



//    private void persistTweets() {
//        Log.i("DEBUG", "testing tweets");
//        for (int i = 0; i < mTweetsListFragment.getmEbayItemsArrayAdapter().getCount(); i++) {
//            Tweet tweet = mTweetsListFragment.getmEbayItemsArrayAdapter().getItem(i);
//            UserModel newUserModel = new UserModel(tweet.getUser().getName(), tweet.getUser().getUid(), tweet.getUser().getScreenName(), tweet.getUser().getProfilePicUrl());
//            newUserModel.save();
//            TweetModel tweetModel = new TweetModel(tweet.getBody(), tweet.getUid(), newUserModel, tweet.getCreatedAt());
//            tweetModel.save();
//        }
//    }

    private void retrieveTweetsFromDB() {
        List<TweetModel> allTweetModels = TweetModel.listAll(TweetModel.class);

        for (TweetModel tweetModel1 : allTweetModels) {
            Log.i("DEBUG", "body is " + Tweet.fromTweetModel(tweetModel1).getBody());
            if (tweetModel1.userModel != null) {
                Log.i("DEBUG", "and user screen name is " + User.fromUserModel(tweetModel1.userModel).getScreenName());
            }
        }
    }

    private void createTweet(String tweetText) {
        client.composeTweet(tweetText, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("debug", response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Debug", errorResponse.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.sophiataskova.apps.smartshoppinglist.R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        if (inputText.equals("")) {
            Toast.makeText(this, getResources().getString(com.sophiataskova.apps.smartshoppinglist.R.string.edit_empty_string_error), Toast.LENGTH_SHORT).show();
        } else {
            createTweet(inputText);
            mTweetsListFragment.clearAll();
//            populateTimeLine(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.sophiataskova.apps.smartshoppinglist.R.id.action_compose) {
            showComposeDialog();
            return true;
        } else if (id == com.sophiataskova.apps.smartshoppinglist.R.id.action_profile) {
            openProfile();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }


    private void showComposeDialog() {
        mComposeDialog = TweetDialogFragment.newInstance(getResources().getString(com.sophiataskova.apps.smartshoppinglist.R.string.compose));
        mComposeDialog.show(getSupportFragmentManager(), "fragment_compose_tweet");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        persistTweets();
    }

    public class TweetsPagerAdapter extends FragmentPagerAdapter {

        private String tabTitles[] = {"Home", "Mentions"};

        public TweetsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeTimelineFragment();
            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}
