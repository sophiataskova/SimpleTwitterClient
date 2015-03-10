package com.sophiataskova.apps.smartshoppinglist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sophiataskova.apps.smartshoppinglist.models.EbayItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EbayItemsArrayAdapter extends ArrayAdapter<EbayItem> {

    public EbayItemsArrayAdapter(Context context, ArrayList<EbayItem> ebayItems) {
        super(context, 0, ebayItems);
    }

    // todo viewholder pattern

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final EbayItem ebayItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ebay_item, parent, false);
        }
        ImageView profilePic = (ImageView) convertView.findViewById(R.id.profile_photo);
        TextView userName = (TextView) convertView.findViewById(R.id.user);
        TextView name = (TextView) convertView.findViewById(R.id.user_name);
        TextView body = (TextView) convertView.findViewById(R.id.body);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.time_stamp);
        if (userName != null) {
            name.setText(ebayItem.getTitle());
            timeStamp.setText("$"+ebayItem.getPrice());
            body.setText(ebayItem.getUrl());
            profilePic.setImageResource(android.R.color.transparent);
            Picasso.with(getContext()).load(ebayItem.getImageUrl()).into(profilePic);
        }

//        profilePic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ProfileActivity.class);
//                intent.putExtra("username", ebayItem.getUser().getScreenName());
//                getContext().startActivity(intent);
//            }
//        });
//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), TweetDetailsActivity.class);
//                intent.putExtra("id", ebayItem.getUid());
//                getContext().startActivity(intent);
//            }
//        });

        return convertView;
    }
}
