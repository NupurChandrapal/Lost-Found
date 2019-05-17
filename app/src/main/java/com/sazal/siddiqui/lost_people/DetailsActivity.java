package com.sazal.siddiqui.lost_people;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.sazal.siddiqui.lost_people.Model.LostFoundData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.image)
    AppCompatImageView ms_Image;
    @BindView(R.id.name)
    AppCompatTextView ms_Name;
    @BindView(R.id.date)
    AppCompatTextView ms_Date;
    @BindView(R.id.place)
    AppCompatTextView ms_Place;
    @BindView(R.id.bodyColor)
    AppCompatTextView ms_BodyColor;
    @BindView(R.id.bodyMark)
    AppCompatTextView ms_BodyMark;
    @BindView(R.id.dress)
    AppCompatTextView ms_Dress;
    @BindView(R.id.height)
    AppCompatTextView ms_Height;
    @BindView(R.id.prize)
    AppCompatTextView ms_Prize;
    @BindView(R.id.contact)
    AppCompatTextView ms_Contact;
    @BindView(R.id.address)
    AppCompatTextView ms_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deails);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        LostFoundData lostFoundData = (LostFoundData) intent.getSerializableExtra("data");

        ms_Name.setText("Name: " + lostFoundData.getName());
        ms_Date.setText("Lost Date: " + lostFoundData.getDate());
        ms_Place.setText("Lost Place: " + lostFoundData.getLostPlace());
        ms_Dress.setText(lostFoundData.getDress());
        ms_BodyColor.setText("Body Color: " + lostFoundData.getBodyColor());
        ms_BodyMark.setText("Body Mark: " + lostFoundData.getBodyMark());
        ms_Height.setText("Height: " + lostFoundData.getHeight());
        ms_Prize.setText("Prize: " + lostFoundData.getPrize());
        ms_Contact.setText("If found call" + lostFoundData.getFoundContact());
        ms_Address.setText("Or " + lostFoundData.getFoundAddress());
        Glide.with(this).load(lostFoundData.getPictureUrl()).into(ms_Image);

    }
}
