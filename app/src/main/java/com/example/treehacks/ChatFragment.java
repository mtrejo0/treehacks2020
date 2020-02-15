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

import com.hound.android.fd.DefaultRequestInfoFactory;
import com.hound.android.fd.HoundSearchResult;
import com.hound.android.fd.Houndify;
import com.hound.core.model.sdk.HoundResponse;

import static android.content.ContentValues.TAG;
import static com.hound.android.fd.Houndify.REQUEST_CODE;

public class ChatFragment extends Fragment {

    Button button;
    TextView tvText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate((R.layout.fragment_chat), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvText = view.findViewById(R.id.textView);
        button = view.findViewById(R.id.button);

        final Houndify houndify = Houndify.get(getContext());
        houndify.setClientId("QNVXRd2992opalCLSpgOrg==");
        houndify.setClientKey("gs2V7q0NDz8ACzns3WcJF-uQZiVHlZmpWMMtOLF6mzCoyF1a8qikloZjo4u462RSVm9piPOX6zYSn32oNV1g8A==");
        houndify.setRequestInfoFactory(new DefaultRequestInfoFactory(getContext()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Houndify.get(getContext()).voiceSearch(getActivity(), REQUEST_CODE);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        final HoundSearchResult result = Houndify.get(getActivity()).fromActivityResult(resultCode, data);
        final HoundResponse houndResponse = result.getResponse();
        Log.d("yessir", "onActivityResult:"+houndResponse);
    }
}


