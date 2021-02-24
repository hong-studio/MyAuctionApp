package com.hong_studio.myauctionapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.loader.content.CursorLoader;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.hong_studio.myauctionapp.G;
import com.hong_studio.myauctionapp.R;
import com.hong_studio.myauctionapp.RetrofitHelper;
import com.hong_studio.myauctionapp.RetrofitService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UploadActivity extends AppCompatActivity {

    NestedScrollView scrollView;
    ImageView ivUpload;
    String imgPath;
    LinearLayout layoutCategory;
    String[] categories= new String[]{"디지털/가전", "가구/인테리어", "유아동/유아도서",
                                      "생활/가공식품", "스포츠/레저", "여성잡화",
                                      "여성의류", "남성패션/잡화", "게임/취미", "뷰티/미용",
                                      "반려동물용품", "도서/티켓/음반", "식물", "기타 상품"};
    TextView tvCategory;
    EditText etProductName, etPrice, etMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        setToolbar();
        scrollView= findViewById(R.id.scrollView);
        ivUpload= findViewById(R.id.iv_upload);

        etProductName= findViewById(R.id.et_productName);
        etPrice= findViewById(R.id.et_price);

        layoutCategory= findViewById(R.id.layout_category);
        tvCategory= findViewById(R.id.tv_category);
        onClickCategory();

        etMsg= findViewById(R.id.et_msg);
        etMsgTouch();
    }

    public void clickIvCamera(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            Uri uri= data.getData();
            if(uri != null){
                Glide.with(this).load(uri).into(ivUpload);

                //이미지 uri를 절대주소로 변경해야 파일업로드가 가능함
                //uri --> 절대경로
                imgPath= getRealPathFromUri(uri);
                //경로가 잘 되었는지 확인
                //new AlertDialog.Builder(this).setMessage(imgPath).show();
            }
        }
    }

    //Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_upload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.upload_menu:

                String productName= etProductName.getText().toString();
                String category= tvCategory.getText().toString();
                String price= etPrice.getText().toString();
                String msg= etMsg.getText().toString();

                Retrofit retrofit= RetrofitHelper.getRetrofitInstanceScalars();
                RetrofitService retrofitService= retrofit.create(RetrofitService.class);

                MultipartBody.Part filePart= null;
                if(imgPath!=null){
                    File file= new File(imgPath);
                    RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"), file);
                    filePart= MultipartBody.Part.createFormData("img", file.getName(), requestBody);
                }

//                MultipartBody.Part filePart2= null;
//                if(imgPath!=null){
//                    File file= new File(imgPath);
//                    RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"), file);
//                    filePart2= MultipartBody.Part.createFormData("profileImg", file.getName(), requestBody);
//                }

                Map<String, String> dataPart= new HashMap<>();
                dataPart.put("productName", productName);
                dataPart.put("category", category);
                dataPart.put("price", price);
                dataPart.put("msg", msg);
                dataPart.put("memberName", G.memberName);
                dataPart.put("profileImg", G.profileImgUrl);

                Call<String> call= retrofitService.postDataToServer(dataPart, filePart);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s= response.body();
                        Toast.makeText(UploadActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UploadActivity.this, "error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        MaterialToolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
    }

    private void onClickCategory() {
        layoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(UploadActivity.this).setItems(categories, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCategory.setText(categories[which]);
                    }
                }).create().show();
            }
        });
    }

    private void etMsgTouch() {
        etMsg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    scrollView.smoothScrollTo(0, scrollView.getScrollY() + etMsg.getHeight());
                }
                return false;
            }
        });
    }
}