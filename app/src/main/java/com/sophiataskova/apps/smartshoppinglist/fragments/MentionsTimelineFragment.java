package com.sophiataskova.apps.smartshoppinglist.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.melnykov.fab.FloatingActionButton;
import com.sophiataskova.apps.smartshoppinglist.EbayRequests;
import com.sophiataskova.apps.smartshoppinglist.EndlessScrollListener;
import com.sophiataskova.apps.smartshoppinglist.R;
import com.sophiataskova.apps.smartshoppinglist.TwitterApplication;
import com.sophiataskova.apps.smartshoppinglist.TwitterClient;
import com.sophiataskova.apps.smartshoppinglist.models.EbayItem;
import com.sophiataskova.apps.smartshoppinglist.models.Tweet;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MentionsTimelineFragment extends TweetsListFragment {

    private TwitterClient client;
//    private SwipeRefreshLayout swipeContainer;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;
    private int mCurrentPage;
    private String mPaginatedUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentPage = 1;
        client = TwitterApplication.getRestClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        lvTweets = (ListView) v.findViewById(R.id.lv_tweets);
        lvTweets.setAdapter(getmEbayItemsArrayAdapter());
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.attachToListView(lvTweets);

        return v;
    }

    @Override
    public void onStart() {
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                mEbayItemsArrayAdapter.clear();
                makeRequest(0);
            }
        });
        makeRequest(0);
        super.onStart();
    }

//    private void populateTimeLine(int page) {
//        client.getMentionsTimeline(page, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                Log.d("Debug", response.toString());
//                getmEbayItemsArrayAdapter().addAll(EbayItem.fromJSONArray(response));
////                persistTweets();
//                swipeContainer.setRefreshing(false);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                if (errorResponse != null) {
//                    Log.d("Debug", errorResponse.toString());
//                }
//            }
//        });
//    }

    public void customLoadMoreDataFromApi(int offset) {
        mCurrentPage = offset + 1;
        makeRequest(offset);

    }

    public void makeRequest(final int offset) {
        mCurrentPage = offset;
        mPaginatedUrl = EbayRequests.sSearchByKeywordUrl.concat("ipod" + EbayRequests.sPageNumberUrl + 1 + EbayRequests.sPaginationUrl + 10);
        Log.i("INFO", "searchUrl is " + mPaginatedUrl);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        if (isNetworkAvailable()){

            asyncHttpClient.get(mPaginatedUrl, new JsonHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.i("Debug", "error is "+ errorResponse);
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Log.d("DEBUG", response.toString());
                    JSONArray ebayItemsResult = null;
                    try {
                        ebayItemsResult = response.getJSONArray("findItemsByKeywordsResponse").getJSONObject(0).getJSONArray("searchResult").getJSONObject(0).getJSONArray("item");
//                        if (mCurrentPage == 0) {
//                            imageResults.clear();
//                        }

                        ((ArrayAdapter) getmEbayItemsArrayAdapter()).addAll(EbayItem.fromJsonArray(ebayItemsResult));
                        if (mEbayItemsArrayAdapter.isEmpty()) {
//                            findViewById(R.id.no_results).setVisibility(View.VISIBLE);
//                            gvResults.setVisibility(View.GONE);
                        } else {
//                            findViewById(R.id.no_results).setVisibility(View.GONE);
//                            gvResults.setVisibility(View.VISIBLE);
                            swipeContainer.setRefreshing(false);
                        }
                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Network problem with fetching the data", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
//                    Log.i("INFO", imageResults.toString());
                }
            });
        }
        else {
            Toast.makeText(getActivity(), "No network connectivity", Toast.LENGTH_SHORT).show();
        }
    }

}
