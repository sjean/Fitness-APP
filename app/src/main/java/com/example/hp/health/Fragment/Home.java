package com.example.hp.health.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.health.R;

public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    private View _view;
    private TextView username;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_home, container, false);
        username = (TextView)_view.findViewById(R.id.name);
        String _name;
        _name = getActivity().getIntent().getStringExtra("username");
        username.setText("Welcome "+_name+"!  This app is designed for your health.");
        return _view;
    }

}
