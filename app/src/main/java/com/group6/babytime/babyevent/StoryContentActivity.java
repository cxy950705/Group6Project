package com.group6.babytime.babyevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.group6.babytime.R;
import com.group6.babytime.pojo.Story;
import com.group6.babytime.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StoryContentActivity extends AppCompatActivity {


    boolean isChanged = false;//imageview 更换图片
    List<Story> storyList = new ArrayList<Story>();
    Story story;//童话故事信息
    @InjectView(R.id.titlebar)
    TitleBar titlebar;
    @InjectView(R.id.image_story)
    ImageView imageStory;
    @InjectView(R.id.tv_introduction)
    TextView tvIntroduction;
    @InjectView(R.id.iv_likes)
    ImageView ivLikes;
    @InjectView(R.id.rl_top)
    RelativeLayout rlTop;
    @InjectView(R.id.tv_story_content)
    TextView tvStoryContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_content);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);//只在activity中实用，继承AppCompatActivity不能用
        getSupportActionBar().hide();
        ButterKnife.inject(this);
        titlebar = ((TitleBar) findViewById(R.id.titlebar));

        titlebar.setTitle("童话故事");
        //titlebar.setTvRight("保存");
        titlebar.setImgLeftRes(R.drawable.arro_left);
        titlebar.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent intent = new Intent(getApplicationContext(), TongHuaFragment.class);
//                startActivity(intent);
                //Toast.makeText(YimiaoInfoActivity.this, "你点击了疫苗详情", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent1 = getIntent();
        story = intent1.getParcelableExtra("storycontent");
        Log.i("333", "onCreate: =====" + story);

        tvIntroduction.setText(story.getStory_introduction());
        tvStoryContent.setText(story.getStory_content());
        xUtilsImageUtils.display(imageStory,"http://10.40.5.43:8080/babytime/upload/"+story.getStory_cover()+"",false);

        ivLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == ivLikes)
                {
                    if(isChanged){
                        ivLikes.setImageDrawable(getResources().getDrawable(R.drawable.like_before));
                    }else
                    {
                        ivLikes.setImageDrawable(getResources().getDrawable(R.drawable.like_after));

                    }
                    isChanged = !isChanged;

                }

            }
        });
            }
}