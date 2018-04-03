package com.example.natasyajuliette.recipestory.Profile;

import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natasyajuliette.recipestory.R;
import com.example.natasyajuliette.recipestory.Utils.FirebaseMethods;
import com.example.natasyajuliette.recipestory.Utils.UniversalImageLoader;
import com.example.natasyajuliette.recipestory.models.User;
import com.example.natasyajuliette.recipestory.models.UserAccountSettings;
import com.example.natasyajuliette.recipestory.models.UserSettings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by natasyajuliette on 01/03/18.
 */

public class EditProfileFragment extends android.support.v4.app.Fragment {

    // firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods mFirebaseMethods;
    private String UserID;

    //EditProfile Fragment widgets
    private EditText mDisplayName, mUsername, mWebsite, mDescription, mEmail, mPhoneNumber;
    private CircleImageView mProfilePhoto;
    private TextView mChangeProfilePhoto;

    // variable
    private UserSettings mUserSettings;

    private static final String TAG = "EditProfileFragment";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
        mDisplayName = (EditText) view.findViewById(R.id.display_name);
        mUsername = (EditText) view.findViewById(R.id.username);
        mWebsite = (EditText) view.findViewById(R.id.website);
        mDescription = (EditText) view.findViewById(R.id.description);
        mEmail = (EditText) view.findViewById(R.id.email);
        mPhoneNumber = (EditText) view.findViewById(R.id.phoneNumber);
        mChangeProfilePhoto = (TextView) view.findViewById(R.id.changeProfilePhoto);
        mFirebaseMethods = new FirebaseMethods(getActivity());

        //setProfileImage();

        setupFirebaseAuth();

        //back arrow for getting back to the Profile Activity
        ImageView backArrow = (ImageView) view.findViewById(R.id.backarrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to Profile Activity Page");
                getActivity().finish();
            }
        });

        ImageView checkmark = (ImageView) view.findViewById(R.id.saveChanges);
        checkmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: attempting to save changes");
                saveProfileSettings();
            }
        });
        return view;
    }

    private void setupProfileWidget(UserSettings userSettings) {
        Log.d(TAG, "setupProfileWidget: setting widgets with data retriecing from firebase databse" + userSettings.toString());

        mUserSettings = userSettings;
        User user = userSettings.getUser();
        UserAccountSettings settings = userSettings.getSettings();

        UniversalImageLoader .setImage(settings.getProfile_photo(), mProfilePhoto, null, "");

        mDisplayName.setText(settings.getDisplay_name());
        mUsername.setText(settings.getUsername());
        mWebsite.setText(settings.getWebsite());
        mDescription.setText(settings.getDescription());
        mEmail.setText(user.getEmail());
        mPhoneNumber.setText(String.valueOf(user.getPhone_number()));


    }

    /**
     * Retrieved the data contained from the widgets and submit it to the database
     */
    private void saveProfileSettings() {
        final String displayName = mDisplayName.getText().toString();
        final String username = mUsername.getText().toString();
        final String website = mWebsite.getText().toString();
        final String description = mDescription.getText().toString();
        final String email = mEmail.getText().toString();
        final long phoneNumber = Long.parseLong(mPhoneNumber.getText().toString());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                // case 1 : the user did not change their username
                if (mUserSettings.getUser().getUsername().equals(username)) {

                }
                // case 2 : the user change their username, need to check for uniqueness
                else {
                    checkIfUsernameExists(username);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
    Check if Username exists in the database
     */
    private void checkIfUsernameExists(final String username) {
        Log.d(TAG, "checkIfUsernameExists: checking if its exists from the database");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child(getString(R.string.dbname_users))
                .orderByChild(getString(R.string.field_username))
                .equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (!dataSnapshot.exists()) {
                    //add username
                    mFirebaseMethods.updateUsername(username);
                    Toast.makeText(getActivity(), "Saved Username", Toast.LENGTH_SHORT).show();

                }
                for (DataSnapshot singleDataSnapshot: dataSnapshot.getChildren()) {
                    if (singleDataSnapshot.exists()) {
                        Log.d(TAG, "onDataChange: FOUND A MATCH " + singleDataSnapshot.getValue(User.class).getUsername());
                        Toast.makeText(getActivity(), "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*
    ------------------------------------ Firebase ---------------------------------------------
     */

    /**
     * Setup the firebase auth object
     */
    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        UserID = mAuth.getCurrentUser().getUid();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // retrieve user information from the database
                setupProfileWidget(mFirebaseMethods.getUserSettings(dataSnapshot));

                // retrieve images for the user
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}