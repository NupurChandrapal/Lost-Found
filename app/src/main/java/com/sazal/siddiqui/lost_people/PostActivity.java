package com.sazal.siddiqui.lost_people;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.sazal.siddiqui.lost_people.Model.LostFoundData;
import com.sazal.siddiqui.lost_people.Model.Upload;
import com.sazal.siddiqui.lost_people.Puller.TheDoor;
import com.sazal.siddiqui.lost_people.Puller.TheRoad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RuntimePermissions
public class PostActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    @BindView(R.id.uploadImage)
    AppCompatImageView ms_UploadImage;
    @BindView(R.id.fab)
    FloatingActionButton ms_Fab;
    @BindView(R.id.nameEditText)
    TextInputEditText ms_NameEditText;
    @BindView(R.id.placeEditText)
    TextInputEditText ms_PlaceEditText;
    @BindView(R.id.dressEditText)
    TextInputEditText ms_DressEditText;
    @BindView(R.id.colorEditText)
    TextInputEditText ms_ColorEditText;
    @BindView(R.id.markEditText)
    TextInputEditText ms_MarkEditText;
    @BindView(R.id.heightEditText)
    TextInputEditText ms_HeightEditText;
    @BindView(R.id.prizeEditText)
    TextInputEditText ms_PrizeEditText;
    @BindView(R.id.dateEditText)
    TextInputEditText ms_DateEditText;
    @BindView(R.id.contactEditText)
    TextInputEditText ms_ContactEditText;
    @BindView(R.id.addressEditText)
    TextInputEditText ms_AddressEditText;
    @BindView(R.id.submitButton)
    AppCompatButton ms_SubmitButton;
    private String name, place, dress, color, mark, prize, contact, address, height, date, url;
    private LostFoundData lostFoundData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        lostFoundData = new LostFoundData();
        Intent intent = getIntent();
        boolean isLost = intent.getBooleanExtra("isLost", false);
        if (isLost) lostFoundData.setIsLost((short) 1);
        else lostFoundData.setIsLost((short) 0);

        ms_SubmitButton.setClickable(false);
    }


    @OnClick({R.id.fab, R.id.dateEditText, R.id.submitButton})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.fab:
                PostActivityPermissionsDispatcher.selectImageFromGalleryWithCheck(PostActivity.this);
                //selectImageFromGallery();
                break;
            case R.id.dateEditText:
                picDate();
                break;
            case R.id.submitButton:
                takeData();
                break;
        }
    }

    private void takeData() {
        name = ms_NameEditText.getText().toString().trim();
        place = ms_PlaceEditText.getText().toString().trim();
        color = ms_ColorEditText.getText().toString().trim();
        dress = ms_DressEditText.getText().toString().trim();
        mark = ms_MarkEditText.getText().toString().trim();
        height = ms_HeightEditText.getText().toString().trim();
        prize = ms_PrizeEditText.getText().toString().trim();
        date = ms_DateEditText.getText().toString().trim();
        contact = ms_ContactEditText.getText().toString().trim();
        address = ms_AddressEditText.getText().toString().trim();

        loadModel();
    }

    private void loadModel() {
        if (!name.isEmpty()) lostFoundData.setName(name);
        else lostFoundData.setName("Not Given");
        if (!place.isEmpty()) lostFoundData.setLostPlace(place);
        else lostFoundData.setLostPlace("Not Given");
        if (!color.isEmpty()) lostFoundData.setBodyColor(color);
        else lostFoundData.setBodyColor("Not Given");
        if (!dress.isEmpty()) lostFoundData.setDress(dress);
        else lostFoundData.setDress("Not Given");
        if (!mark.isEmpty()) lostFoundData.setBodyMark(mark);
        else lostFoundData.setBodyMark("Not Given");
        if (!height.isEmpty()) lostFoundData.setHeight(height);
        else lostFoundData.setHeight("0");
        if (!prize.isEmpty()) lostFoundData.setPrize(prize);
        else lostFoundData.setPrize("Not Given");
        if (!date.isEmpty()) lostFoundData.setDate(date);
        if (!contact.isEmpty()) lostFoundData.setFoundContact(contact);
        else lostFoundData.setFoundContact("Not Given");
        if (!address.isEmpty()) lostFoundData.setFoundAddress(address);
        else lostFoundData.setFoundAddress("Not Given");
        TheRoad theRoad = TheDoor.driveOnRoad();
        theRoad.insertInfo(name, height, color, mark, dress, date, contact, address, null, place, prize, lostFoundData.getIsLost(), "http://alliancemichenery.com/Dragon/lostFound/api/pic/" + url).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) finish();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private void picDate() {
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(PostActivity.this, (datepicker, selectedyear, selectedmonth, selectedday) -> {
            // TODO Auto-generated method stub
                /*      Your code   to get date and time    */
            selectedmonth = selectedmonth + 1;
            ms_DateEditText.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Select Date");
        mDatePicker.show();

    }


    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void selectImageFromGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }

    @OnShowRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("To upload image, need permission to access your storage.")
                .setPositiveButton("Allow", (dialog, button) -> request.proceed())
                .setNegativeButton("Deny", (dialog, button) -> request.cancel())
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PostActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void showDenied() {
        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                ms_UploadImage.setImageBitmap(selectedImage);

                String filePath = getRealPathFromURIPath(imageUri, PostActivity.this);
                File file = new File(filePath);

                RequestBody uFile = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), uFile);
                RequestBody fileName = RequestBody.create(MediaType.parse("text/plain"), file.getName());
                TheRoad theRoad = TheDoor.driveOnRoad();
                url = file.getName();
                theRoad.uploadFile(fileToUpload, fileName).enqueue(new Callback<Upload>() {
                    @Override
                    public void onResponse(Call<Upload> call, Response<Upload> response) {
                        if (response.isSuccessful()) {
                            ms_SubmitButton.setClickable(true);
                        }
                    }

                    @Override
                    public void onFailure(Call<Upload> call, Throwable t) {
                        Toast.makeText(PostActivity.this, "Something went wrong, please try after sometime", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(PostActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
