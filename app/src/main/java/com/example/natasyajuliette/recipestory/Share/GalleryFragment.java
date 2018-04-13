package com.example.natasyajuliette.recipestory.Share;


import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.natasyajuliette.recipestory.Profile.ProfileActivity;
import com.example.natasyajuliette.recipestory.R;

/**
 * Created by natasyajuliette on 23/02/18.
 */

public class GalleryFragment extends Fragment {
    private static final String TAG = "GalleryFragment";
    
    // widgets
    private GridView gridView;
    private ImageView galleryImage;
    private ProgressBar mProgressBar;
    private Spinner directorySpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        galleryImage = (ImageView) view.findViewById(R.id.galleryImageView);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        directorySpinner = (Spinner) view.findViewById(R.id.spinnerDirectory);
        
        mProgressBar.setVisibility(View.GONE);
        
        Log.d(TAG, "onCreateView: started.");
        
        ImageView shareClose = (ImageView) view.findViewById(R.id.ivCloseShare);
        shareClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: closing the gallery fragment");
                getActivity().finish();
            }
        });

        TextView nextScreen = (TextView) view.findViewById(R.id.tvNext);
        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to the next screen");
            }
        });
        return view;
    }
}
