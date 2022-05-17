package com.example.amicus;

import static com.example.amicus.MainActivity.facebook;
import static com.example.amicus.MainActivity.id;
import static com.example.amicus.MainActivity.logo;
import static com.example.amicus.MainActivity.name1;
import static com.example.amicus.MainActivity.parol1;
import static com.example.amicus.MainActivity.phone;
import static com.example.amicus.MainActivity.pochta;
import static com.example.amicus.MainActivity.truba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.amicus.API.AuthorizationBody;
import com.example.amicus.API.AuthorizationResponce;
import com.example.amicus.API.RetrofitAPI;
import com.example.amicus.API.UpdateAuto;

import java.io.File;

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
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Button delete_bt;
    Button save_bt;
    String imagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        EditText number_textview = findViewById(R.id.number_textview);
        TextView profile_name =findViewById(R.id.profile_name);
        EditText facebook_edit =findViewById(R.id.facebook_edit);
        EditText password_edit =findViewById(R.id.password_edit);

        profile_image = findViewById(R.id.profile_image);
        mSwipeRefreshLayout = findViewById(R.id.swipe_container);
        delete_bt = findViewById(R.id.delete_bt);
        save_bt = findViewById(R.id.save_bt);

        number_textview.setText(truba);
        profile_name.setText(name1);
        TextView mail_edit = findViewById(R.id.mail_edit);

        number_textview.setText(truba);
        profile_name.setText(name1);
        mail_edit.setText(pochta);
        facebook_edit.setText(facebook);
        password_edit.setText(parol1);
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
        save_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitAPI api = RetrofitAPI.getInstance();
                UpdateProfile addBody = new UpdateProfile();
                addBody.setUserid(id);
                addBody.setEmail(mail_edit.getText().toString());
                addBody.setFacebook(facebook_edit.getText().toString());
                addBody.setName(profile_name.getText().toString());
                addBody.setPassword(password_edit.getText().toString());
                addBody.setPhone(number_textview.getText().toString());
                Call<UpdateAuto> call = api.getJSONApi().upUser(addBody);
                call.enqueue(new Callback<UpdateAuto>() {
                    @Override
                    public void onResponse(Call<UpdateAuto> call, Response<UpdateAuto> response) {
                        Toast.makeText(ProfileSetting.this, "Данные профиля успешно изменены", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileSetting.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UpdateAuto> call, Throwable t) {

                    }
                });
            }
        });
        
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
                RetrofitAPI api = RetrofitAPI.getInstance();
                Call<UpdateAuto> call = api.getJSONApi().delUser(id);
                call.enqueue(new Callback<UpdateAuto>() {
                    @Override
                    public void onResponse(Call<UpdateAuto> call, Response<UpdateAuto> response) {
                        Intent intent = new Intent(ProfileSetting.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UpdateAuto> call, Throwable t) {
                        Toast.makeText(ProfileSetting.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RetrofitAPI api = RetrofitAPI.getInstance();

                AuthorizationBody body = new AuthorizationBody();
                body.phone = phone;
                body.password = parol1;
                api.getJSONApi().authUser(body);
                Call<AuthorizationResponce> call = api.getJSONApi().authUser(body);
                call.enqueue(new Callback<AuthorizationResponce>() {
                    @Override
                    public void onResponse(Call<AuthorizationResponce> call, Response<AuthorizationResponce> response) {
                        logo = response.body().getLogo();
                        Glide.with(ProfileSetting.this).load(logo).diskCacheStrategy(DiskCacheStrategy.NONE).into(profile_image);

                    }

                    @Override
                    public void onFailure(Call<AuthorizationResponce> call, Throwable t) {
                        Toast.makeText(ProfileSetting.this, "", Toast.LENGTH_SHORT).show();
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
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
    @Override
    protected void onStart() {
        super.onStart();
        RetrofitAPI api = RetrofitAPI.getInstance();

        AuthorizationBody body = new AuthorizationBody();
        body.phone = phone;
        body.password = parol1;
        api.getJSONApi().authUser(body);
        Call<AuthorizationResponce> call = api.getJSONApi().authUser(body);
        call.enqueue(new Callback<AuthorizationResponce>() {
            @Override
            public void onResponse(Call<AuthorizationResponce> call, Response<AuthorizationResponce> response) {
                logo = response.body().getLogo();
                Glide.with(ProfileSetting.this).load(logo).diskCacheStrategy(DiskCacheStrategy.NONE).into(profile_image);

            }

            @Override
            public void onFailure(Call<AuthorizationResponce> call, Throwable t) {
                Toast.makeText(ProfileSetting.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}