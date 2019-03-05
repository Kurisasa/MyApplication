package com.example.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class MyDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    EditText txtView;


    public interface MyDialogFragmentListener {

        void onFinishEditDialog(String inputText);

    }

    public MyDialogFragment(){

    }

    public static MyDialogFragment newInstance(String title) {

        MyDialogFragment frag = new MyDialogFragment();

        Bundle args = new Bundle();

        args.putString("title", title);

        frag.setArguments(args);

        return frag;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_pop_up, container, false);
        return rootView;
    }

    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        // Get field from view

        txtView = (EditText) view.findViewById(R.id.txtView);

        // Fetch arguments from bundle and set title

//        String title = getArguments().getString("title", "Payment");
//
//        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field

        txtView.requestFocus();

        getDialog().getWindow().setSoftInputMode(

                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        txtView.setOnEditorActionListener(this);

    }

    // Call this method to send the data back to the parent fragment

    public void sendBackResult() {

        // Notice the use of `getTargetFragment` which will be set when the dialog is displayed

        MyDialogFragmentListener listener = (MyDialogFragmentListener) getTargetFragment();

        listener.onFinishEditDialog(txtView.getText().toString());

        dismiss();

    }

    // Fires whenever the textfield has an action performed

    // In this case, when the "Done" button is pressed

    // REQUIRES a 'soft keyboard' (virtual keyboard)

    @Override

    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (EditorInfo.IME_ACTION_DONE == actionId) {

            // Return input text back to activity through the implemented listener

            MyDialogFragmentListener listener = (MyDialogFragmentListener) getActivity();

            listener.onFinishEditDialog(txtView.getText().toString());

            // Close the dialog and return back to the parent activity

            dismiss();

            return true;

        }

        return false;

    }
}