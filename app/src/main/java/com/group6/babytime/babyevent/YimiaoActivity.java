package com.group6.babytime.babyevent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.group6.babytime.R;
import com.group6.babytime.fragment.BabyEventFragment;
import com.group6.babytime.pojo.ListActivityBean;
import com.group6.babytime.titlebar.TitleBar;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class YimiaoActivity extends AppCompatActivity {
    private static final String TAG = "YimiaoActivity";
    private ListView lv_list;
    private BaseAdapter baseAdapter;
    final List<ListActivityBean.YimiaoInfo> dongtaiList = new ArrayList<ListActivityBean.YimiaoInfo>();
    private TitleBar titlebar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_yimiao);
        getSupportActionBar().hide();
        titlebar = ((TitleBar) findViewById(R.id.titlebar));
        titlebar.setTitle("疫苗接种");
        titlebar.setImgLeftRes(R.drawable.arro_left);
        titlebar.setImgLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        lv_list = ((ListView) findViewById(R.id.lv_list));
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                Intent intent=new Intent(getApplicationContext(),YimiaoInfoActivity.class);
                intent.putExtra("yimiaoInfo", dongtaiList.get(position));
                startActivity(intent);
            }
        });


        baseAdapter = new BaseAdapter() {
            private ImageView iv_touxiang;
            //private Button bt_status;
            private TextView title;
            //private TextView info;




            @Override
            public int getCount() {
                return dongtaiList.size();
            }

            @Override
            public Object getItem(int position) {
                return 0;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.i(TAG, "加载第 i个position" + position);
                View view = View.inflate(YimiaoActivity.this, R.layout.activity_yimiao, null);
                //iv_touxiang = ((ImageView) view.findViewById(R.id.iv_touxiang));
                TextView title = ((TextView) view.findViewById(R.id.title));
                TextView bt_status = ((TextView) view.findViewById(R.id.bu_status));
                TextView info = ((TextView) view.findViewById(R.id.info));


                ListActivityBean.YimiaoInfo dongtai = dongtaiList.get(position);

                bt_status.setText(dongtai.status);
                Bundle extras=getIntent().getExtras();
                if (extras != null) {
                    bt_status.setText(extras.getString("isInjected"));
                }
                title.setText(dongtai.name);
                info.setText("宝宝"+String.valueOf(dongtai.month)+"个月了");


                return view;
            }
        };
        lv_list.setAdapter(baseAdapter);

        getDongtaiList();

    }

    private List<ListActivityBean.YimiaoInfo> getDongtaiList() {
        RequestParams params = new RequestParams("http://10.40.5.43:8080/babytime/yimiao");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ListActivityBean bean = gson.fromJson(result, ListActivityBean.class);


                System.out.println(bean.status);
                System.out.println(bean);
                dongtaiList.addAll(bean.dongtailist);
                //更新页面
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                System.out.println(ex);
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return null;
    }
}





