package com.codepath.apps.simpletwitterclient.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.simpletwitterclient.EbayItemsArrayAdapter;
import com.codepath.apps.simpletwitterclient.R;
import com.codepath.apps.simpletwitterclient.models.EbayItem;

import java.util.ArrayList;


public class TweetsListFragment extends Fragment {

    protected EbayItemsArrayAdapter mEbayItemsArrayAdapter;
    private ArrayList<EbayItem> ebayItems;
    private ListView lvTweets;
    protected SwipeRefreshLayout swipeContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ebayItems = new ArrayList<>();
        mEbayItemsArrayAdapter = new EbayItemsArrayAdapter(getActivity(), ebayItems);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
//        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);

        lvTweets = (ListView) v.findViewById(R.id.lv_tweets);
        lvTweets.setAdapter(mEbayItemsArrayAdapter);
        return v;
    }

    public void clearAll() {
        mEbayItemsArrayAdapter.clear();
    }

    public SwipeRefreshLayout getSwipeContainer() {
        return swipeContainer;
    }

    public EbayItemsArrayAdapter getmEbayItemsArrayAdapter() {
        return mEbayItemsArrayAdapter;
    }

    public Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
