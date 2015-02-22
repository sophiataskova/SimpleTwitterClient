package com.codepath.apps.simpletwitterclient;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TweetDialogFragment extends android.support.v4.app.DialogFragment implements TextView.OnEditorActionListener, View.OnClickListener{
    private EditText mEditText;
    private Button mDoneButton;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    static TweetDialogFragment newInstance(String title) {
        TweetDialogFragment tweetDialogFragment = new TweetDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("prefill", "Enter text");
        tweetDialogFragment.setArguments(args);
        return tweetDialogFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.compose_dialog, container, false);
        mEditText = (EditText) v.findViewById(R.id.txt_new_content);
        mDoneButton = (Button) v.findViewById(R.id.done_button);
        String title = getArguments().getString("title", getResources().getString(R.string.compose));
        String prefill = getArguments().getString("prefill", "");

        getDialog().setTitle(title);
        mEditText.setText(prefill);
        mEditText.requestFocus();
        mEditText.setOnEditorActionListener(this);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyContentChanges();
            }
        });

        return v;
    }

    private void applyContentChanges() {
        ComposeTweetDialogListener listener = (ComposeTweetDialogListener) getActivity();

        listener.onFinishEditDialog(mEditText.getText().toString());
//        getActivity().dismissKeyboard();
        dismiss();

    }

    public interface ComposeTweetDialogListener {
        void onFinishEditDialog(String inputText);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }
}
