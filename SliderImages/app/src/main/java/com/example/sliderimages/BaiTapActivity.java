package com.example.sliderimages;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.sliderimages.API.ServiceAPI;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiTapActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<ImagesSlider> imagesList;
    private ServiceAPI serviceAPI;
    private BaiTapAdapter adapter;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager.getCurrentItem() == imagesList.size() -1){
                viewPager.setCurrentItem(0);
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }

        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_tap);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.viewpage);
        circleIndicator = findViewById(R.id.circle_indicator);

        imagesList = getListImages();


// Lắng nghe ViewPager chuyển trang
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });



    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); // Xóa Runnable khi Activity bị tạm dừng
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000); // Đặt lại Runnable khi Activity tiếp tục chạy
    }


    private List<ImagesSlider> getListImages() {

        List<ImagesSlider> list = new ArrayList<>();  ;
        serviceAPI =RetrofitClient.getClient().create(ServiceAPI.class);
        serviceAPI.LoadImageSlider(1).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if(response.isSuccessful()){
                    MessageModel messageModel =response.body();
                    list.addAll(messageModel.getResult());
                    adapter = new BaiTapAdapter(imagesList);
                    viewPager.setAdapter(adapter);
                    circleIndicator.setViewPager(viewPager);
                    // Gọi runnable
                    handler.postDelayed(runnable, 3000);
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.d("API ERROR", "Không thể lấy danh mục" + t.getMessage());
            }
        });
        return list;


    }
}