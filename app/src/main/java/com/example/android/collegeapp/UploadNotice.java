package com.example.android.collegeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class UploadNotice extends AppCompatActivity {


    private CardView addImage;
    ImageView noticeImageView;
    private EditText NoticeTitle;
    private Button uploadNoticeBtn;

    private final int REQ = 1;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_notice);


        noticeImageView = findViewById(R.id.noticeImageView);
        addImage = findViewById(R.id.addImage);
        NoticeTitle = findViewById(R.id.noticeTitle);
        uploadNoticeBtn = findViewById(R.id.uploadNoticeBtn);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGaller();
            }
        });

        uploadNoticeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NoticeTitle.getText().toString().isEmpty()){
                    NoticeTitle.setError("Empty");
                    NoticeTitle.requestFocus();
                }else if (bitmap==null){
                    uploadData();
                }else {
                    uploadImage();
                }
            }
        });

    }

    private void uploadImage() {

    }
    private void uploadData() {
    }

    private void openGaller() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            noticeImageView.setImageBitmap(bitmap);
        }
    }
} 