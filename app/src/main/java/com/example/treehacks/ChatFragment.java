package com.example.treehacks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hound.android.fd.DefaultRequestInfoFactory;
import com.hound.android.fd.HoundSearchResult;
import com.hound.android.fd.Houndify;
import com.hound.core.model.sdk.HoundResponse;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.hound.android.fd.Houndify.REQUEST_CODE;

public class ChatFragment extends Fragment {

    EditText etMessage;
    Button btSend;
    RecyclerView rvChat;
    ArrayList<Message> mMessages;
    ChatAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate((R.layout.fragment_chat), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMessages = new ArrayList<>();
        mAdapter = new ChatAdapter(getActivity(), mMessages);

        btSend = view.findViewById(R.id.btSend);
        rvChat = view.findViewById(R.id.rvChat);
        etMessage = view.findViewById(R.id.etMessage);
                mMessages.add(new Message("Hello how can I help you?",true));
        mMessages.add(new Message("I have a shortness of breath",false));
        mMessages.add(new Message("Is this a new problem?",true));
        mMessages.add(new Message("No I have had this problem for a few weeks",false));
        mMessages.add(new Message("I would recommend resting and setting up an appointment to see me",true));
        mAdapter.notifyDataSetChanged();
        rvChat.setAdapter(mAdapter);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity() );
        linearLayoutManager.setReverseLayout(false);
        rvChat.setLayoutManager(linearLayoutManager);



        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etMessage.getText().toString().length() == 0){
                    return;
                }
                mMessages.add(new Message(etMessage.getText().toString(),false));
                etMessage.setText("");
                mAdapter.notifyDataSetChanged();
                rvChat.scrollToPosition(mMessages.size()-1);
            }
        });
    }


}


