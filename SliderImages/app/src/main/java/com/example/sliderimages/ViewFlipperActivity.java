package com.example.sliderimages;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {

    ViewFlipper viewFlipperMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        setupViewFlipper();
    }

    private void setupViewFlipper() {
        List<String> imageUrls = Arrays.asList(
                "http://app.iotstar.vn:8081/appfoods/flipper/quangcao.png",
                "http://app.iotstar.vn:8081/appfoods/flipper/coffee.jpg",
                "http://app.iotstar.vn:8081/appfoods/flipper/companypizza.jpeg",
                "http://app.iotstar.vn:8081/appfoods/flipper/themoingon.jpeg"
        );

        imageUrls.forEach(url -> {
            ImageView imageView = new ImageView(this);
            Glide.with(this)
                    .load(url)
                    .into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        });

        viewFlipperMain.setFlipInterval(3000);
        viewFlipperMain.setAutoStart(true);

        // Thiết lập animation
        Animation slide_in = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        viewFlipperMain.setInAnimation(slide_in);
        viewFlipperMain.setOutAnimation(slide_out);
    }
}
