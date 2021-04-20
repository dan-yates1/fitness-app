package com.example.fitnessapp.layouts;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProfileActivity extends AppCompatActivity {
    TextView mName, mEmail;
    ImageButton mEditButton;
    ImageView mProfilePic;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    BottomNavigationView mBottomNav;

    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setUpInterface();
        buildNavBar();
        getUserData();
    }


    private void getUserData() {
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        // Retrieve user's data from database
        DocumentReference docRef = fStore.collection("users").document(userId);
        docRef.addSnapshotListener(this, (value, error) -> {
            mEmail.setText(value.getString("email"));
            mName.setText(value.getString("fName"));
        });
    }

    private void setUpInterface() {
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDbRef = FirebaseDatabase.getInstance().getReference("uploads");

        mName = findViewById(R.id.nameTextView);
        mEmail = findViewById(R.id.emailTextView);
        mEditButton = findViewById(R.id.editButton);
        mProfilePic = findViewById(R.id.profilePic);

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    public void buildNavBar() {
        mBottomNav = findViewById(R.id.bottomNavBar);
        mBottomNav.setSelectedItemId(R.id.nav_profile);
        mBottomNav.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                case R.id.nav_exercises:
                    startActivity(new Intent(getApplicationContext(), ExercisesActivity.class));
                case R.id.nav_profile:
                    return true;
                case R.id.nav_workout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
            }
            return false;
        });
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileRef = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}