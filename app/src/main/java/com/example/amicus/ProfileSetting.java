package com.example.amicus;

import static com.example.amicus.MainActivity.facebook;
import static com.example.amicus.MainActivity.id;
import static com.example.amicus.MainActivity.logo;
import static com.example.amicus.MainActivity.name1;
import static com.example.amicus.MainActivity.pochta;
import static com.example.amicus.MainActivity.truba;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.API.RetrofitAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSetting extends AppCompatActivity {

    CircleImageView profile_image;
    private static final int PIC_IMAGE =1;

    String imagePath;
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
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(photoPickerIntent, 0);

            }
        });

        delete_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    private void uploadImage() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        //формируем картинку
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",file.getName(),requestBody);
        RequestBody someDate = RequestBody.create(MediaType.parse("text/plan"),"new fotka");
        RetrofitAPI api = RetrofitAPI.getInstance();
        Call<ResponseBody> call = api.getJSONApi().uploadphoto(id,part,someDate);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            try {
                if (requestCode== 0 &&resultCode == RESULT_OK ) {
                    Uri selectedImage = data.getData();
                    String selectedFilePath = data.getData().getPath();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagePath = cursor.getString(columnIndex);
//                str1.setText(mediaPath);
                    // Set the Image in ImageView for Previewing the Media
                    profile_image.setImageBitmap(BitmapFactory.decodeFile(selectedFilePath));
                    cursor.close();
                    uploadImage();
                }
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
}