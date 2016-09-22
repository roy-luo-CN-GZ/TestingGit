package com.example.administrator.mytest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private String[] itemName={"Apple","Banana","Cherry","Grape","Mango","Orange","Pear","Pineapple","Strawberry","Watermelon"};
    private int[] itemId={R.mipmap.apple,R.mipmap.banana,R.mipmap.cherry,R.mipmap.grape,R.mipmap.mango,R.mipmap.orange,R.mipmap.pear,
            R.mipmap.pineapple,R.mipmap.strawberry,R.mipmap.watermelon};
    private List<View> listView=new ArrayList<>();
    private MyPagerAdapter adapter;
    private ViewPager viewPager;
    private List<View> tagList=new ArrayList<>();
    private LinearLayout tagGroup;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initPagerView();
        initTagGroup();
        button= (Button) findViewById(R.id.button);

    }

    /**
     * 初始化pagerView内容
     */
    private void initPagerView(){
        for(int i=0;i<itemId.length;i++){
            ImageView image=new ImageView(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
            image.setLayoutParams(params);
            image.setImageResource(itemId[i]);
            listView.add(image);
        }
        adapter=new MyPagerAdapter(listView);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    private void initTagGroup(){
        tagGroup= (LinearLayout) findViewById(R.id.tag_group);
        for(int i=0;i<itemId.length;i++){
            ImageView image=new ImageView(this);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.setMargins(5,0,0,0);
            image.setLayoutParams(params);

            if(i==0){
                image.setImageResource(R.mipmap.tag_on);
            }else{
                image.setImageResource(R.mipmap.tag_off);
            }
            tagGroup.addView(image);
            tagList.add(image);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i=0;i<tagList.size();i++){
            if(i==position){
                ((ImageView)tagList.get(i)).setImageResource(R.mipmap.tag_on);
            }else{
                ((ImageView)tagList.get(i)).setImageResource(R.mipmap.tag_off);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyPagerAdapter extends PagerAdapter{
        List<View> list;
        MyPagerAdapter(List<View>list){
            this.list=list;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public int getCount() {
            if(list!=null){
                return list.size();
            }
            return 0;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager)container).removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager)container).addView(list.get(position),0);
            return list.get(position);
        }
    }
}
