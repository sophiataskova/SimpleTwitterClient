package com.codepath.apps.simpletwitterclient.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.simpletwitterclient.EndlessScrollListener;
import com.codepath.apps.simpletwitterclient.R;
import com.codepath.apps.simpletwitterclient.TweetsArrayAdapter;
import com.codepath.apps.simpletwitterclient.TwitterApplication;
import com.codepath.apps.simpletwitterclient.TwitterClient;
import com.codepath.apps.simpletwitterclient.models.Category;
import com.codepath.apps.simpletwitterclient.models.Item;
import com.codepath.apps.simpletwitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TimeLineActivity extends ActionBarActivity implements TweetDialogFragment.ComposeTweetDialogListener {

    private TwitterClient client;
    private TweetsArrayAdapter tweetsArrayAdapter;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;
    private SwipeRefreshLayout swipeContainer;
    private int mCurrentPage;
    private TweetDialogFragment mComposeDialog;
    private Category mTweetsCategory;
    private Item mTweetItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);
        mCurrentPage = 1;
        lvTweets = (ListView) findViewById(R.id.lv_tweets);
        tweets = new ArrayList<>();
        tweetsArrayAdapter = new TweetsArrayAdapter(this, tweets);
        lvTweets.setAdapter(tweetsArrayAdapter);
        client = TwitterApplication.getRestClient();
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                tweetsArrayAdapter.clear();
                populateTimeLine(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });
        populateTimeLine(0);
//        setupDatabase();
    }

    private void setupDatabase() {
        mTweetsCategory = new Category();
//        mTweetsCategory.remoteId = 1;
        mTweetsCategory.name = "Tweets";
        mTweetsCategory.save();
    }

    public void customLoadMoreDataFromApi(int offset) {
        mCurrentPage = offset + 1;
        populateTimeLine(offset);

    }

    private void populateTimeLine(int page) {
        client.getHomeTimeline(page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("Debug", response.toString());
//                if (mTweetItem != null) {
//                    ActiveAndroid.beginTransaction();
//                    try {
//                        for (Item item : mTweetItem.getAll(mTweetsCategory)) {
//                            tweetsArrayAdapter.addAll(Tweet.fromItem(item));
//                        }
//                        ActiveAndroid.setTransactionSuccessful();
//                    }
//                    finally {
//                        ActiveAndroid.endTransaction();
//                    }
//
//                }
                tweetsArrayAdapter.addAll(Tweet.fromJSONArray(response));
//                for (int i = 0; i < tweetsArrayAdapter.getCount(); i++) {
//                    persistTweet(tweetsArrayAdapter.getItem(i));
//                }
                swipeContainer.setRefreshing(false);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                if (errorResponse != null) {
                    Log.d("Debug", errorResponse.toString());
                }
            }
        });
    }

    private void persistTweet(Tweet tweet) {
        mTweetItem = new Item();
                //, tweet.getUser().getScreenName(), tweet.getUser().getProfilePicUrl(), tweet.getBody(), tweet.getCreatedAt(), tweet.getUid(), tweet.getUser().getUid(), mTweetsCategory);
//        mTweetItem.remoteId = 1;
        mTweetItem.category = mTweetsCategory;
        mTweetItem.name = tweet.getCreatedAt();
//        mTweetItem.name = tweet.getUser().getName();
//        mTweetItem.screenName = tweet.getUser().getScreenName();
//        mTweetItem.profileImage = tweet.getUser().getProfilePicUrl();
//        mTweetItem.body = tweet.getBody();
//        mTweetItem.timeStamp = tweet.getCreatedAt();
//        mTweetItem.localId = tweet.getUid();
        mTweetItem.save();
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
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        if (inputText.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.edit_empty_string_error), Toast.LENGTH_SHORT).show();
        } else {
            createTweet(inputText);
            tweetsArrayAdapter.clear();
            populateTimeLine(0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_compose) {
            showComposeDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showComposeDialog() {
        mComposeDialog = TweetDialogFragment.newInstance(getResources().getString(R.string.compose));
        mComposeDialog.show(getSupportFragmentManager(), "fragment_compose_tweet");
    }
}
