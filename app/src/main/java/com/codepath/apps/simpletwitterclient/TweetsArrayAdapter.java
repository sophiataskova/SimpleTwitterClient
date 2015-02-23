package com.codepath.apps.simpletwitterclient;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.simpletwitterclient.activities.TweetDetailsActivity;
import com.codepath.apps.simpletwitterclient.models.Tweet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, ArrayList<Tweet> tweets) {
        super(context, 0, tweets);
    }

    // todo viewholder pattern

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }
        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_photo);
        TextView userName = (TextView) convertView.findViewById(R.id.user);
        TextView name = (TextView) convertView.findViewById(R.id.user_name);
        TextView body = (TextView) convertView.findViewById(R.id.body);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.time_stamp);
        if (userName != null) {
            userName.setText("@" + tweet.getUser().getScreenName());

            name.setText(tweet.getUser().getName());
            body.setText(tweet.getBody());
            profilePic.setImageResource(android.R.color.transparent);
            timeStamp.setText(tweet.getCreatedAt());
            Picasso.with(getContext()).load(tweet.getUser().getProfilePicUrl()).into(profilePic);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TweetDetailsActivity.class);
                intent.putExtra("id", tweet.getUid());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
