package com.codepath.apps.simpletwitterclient.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.simpletwitterclient.R;
import com.codepath.apps.simpletwitterclient.TweetsArrayAdapter;
import com.codepath.apps.simpletwitterclient.models.Tweet;

import java.util.ArrayList;


public class TweetsListFragment extends Fragment {

    protected TweetsArrayAdapter tweetsArrayAdapter;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;
    protected SwipeRefreshLayout swipeContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        tweetsArrayAdapter = new TweetsArrayAdapter(getActivity(), tweets);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
//        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);

        lvTweets = (ListView) v.findViewById(R.id.lv_tweets);
        lvTweets.setAdapter(tweetsArrayAdapter);
        return v;
    }

    public void clearAll() {
        tweetsArrayAdapter.clear();
    }

    public SwipeRefreshLayout getSwipeContainer() {
        return swipeContainer;
    }

    public TweetsArrayAdapter getTweetsArrayAdapter() {
        return tweetsArrayAdapter;
    }
}
