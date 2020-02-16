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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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
    Button btnMic;
    final static int REQUEST_CODE = 100;

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
        btnMic = view.findViewById(R.id.btnMic);
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

        btnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMessage.setText("whats up cuuuuhhhhhhhh");
                Houndify.get(getContext()).voiceSearch(getActivity(), REQUEST_CODE);
            }
        });

        final Houndify houndify = Houndify.get(getContext());
        houndify.setClientId("QNVXRd2992opalCLSpgOrg==");
        houndify.setClientKey("gs2V7q0NDz8ACzns3WcJF-uQZiVHlZmpWMMtOLF6mzCoyF1a8qikloZjo4u462RSVm9piPOX6zYSn32oNV1g8A==");
        houndify.setRequestInfoFactory(new DefaultRequestInfoFactory(getContext()));

        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            final HoundSearchResult result = Houndify.get(getActivity()).fromActivityResult(resultCode, data);
            final HoundResponse houndResponse = result.getResponse();
        }
    }


}


