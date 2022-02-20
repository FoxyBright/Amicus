package com.example.amicus;

import static com.example.amicus.MainActivity.facebook;
import static com.example.amicus.MainActivity.logo;
import static com.example.amicus.MainActivity.name1;
import static com.example.amicus.MainActivity.pochta;
import static com.example.amicus.MainActivity.truba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ProfileSetting extends AppCompatActivity {

    CircleImageView profile_image;
    private static final int PIC_IMAGE =1;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        TextView number_textview = findViewById(R.id.number_textview);
        TextView profile_name =findViewById(R.id.profile_name);
        profile_image = findViewById(R.id.profile_image);

        number_textview.setText(truba);
        profile_name.setText(name1);
        TextView facebook_edit = findViewById(R.id.facebook_edit);
        TextView mail_edit = findViewById(R.id.mail_edit);

        number_textview.setText(truba);
        profile_name.setText(name1);
        mail_edit.setText(pochta);
        facebook_edit.setText(facebook);
        Glide.with(ProfileSetting.this).load(logo).diskCacheStrategy(DiskCacheStrategy.NONE).into(profile_image);

        if (pochta.equals("")) {
            mail_edit.setText("Добавить почту");
        }
        if(facebook.equals("")){
            facebook_edit.setText("Добавить Facebook");
        }


        Button back_bt = findViewById(R.id.back_bt);
        back_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button delete_bt = findViewById(R.id.delete_bt);

        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileSetting.this, "Сервис временно не доступен, пожалуйста попробуйте позже", Toast.LENGTH_LONG).show();
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== PIC_IMAGE &&resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                profile_image.setImageBitmap(bitmap);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}