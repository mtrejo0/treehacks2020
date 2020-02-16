package com.example.treehacks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hound.android.fd.DefaultRequestInfoFactory;
import com.hound.android.fd.HoundSearchResult;
import com.hound.android.fd.Houndify;
import com.hound.core.model.sdk.HoundResponse;

public class FragmentHandler extends AppCompatActivity {
    private static final int REQUEST_CODE = 100;
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        final Houndify houndify = Houndify.get(this);
        houndify.setClientId("QNVXRd2992opalCLSpgOrg==");
        houndify.setClientKey("gs2V7q0NDz8ACzns3WcJF-uQZiVHlZmpWMMtOLF6mzCoyF1a8qikloZjo4u462RSVm9piPOX6zYSn32oNV1g8A==");
        houndify.setRequestInfoFactory(new DefaultRequestInfoFactory(this));

        // define manager to decide which fragment to display
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new HomeFragment();
                switch (item.getItemId()) {
                    case R.id.home_fragment:
                        fragment = new HomeFragment();
                        break;
                    case R.id.chat_fragment:
                        fragment = new ChatFragment();
                        break;
                    default:
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home_fragment);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            final HoundSearchResult result = Houndify.get(this).fromActivityResult(resultCode, data);
            final HoundResponse houndResponse = result.getResponse();

        }
    }

}
