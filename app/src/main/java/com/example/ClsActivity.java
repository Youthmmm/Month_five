package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.adapter.LeftAdapter;
import com.example.adapter.RightAdapter;
import com.example.month_view.R;
import com.example.month_view.contract.ClsContract;
import com.example.month_view.entity.ClsEntity;
import com.example.month_view.entity.GreendaoEntity;
import com.example.month_view.entity.RightEntity;
import com.example.month_view.presenter.ClsPresenter;
import com.example.month_view.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.majianguo.greendao.DaoMaster;
import com.majianguo.greendao.DaoSession;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

public class ClsActivity extends AppCompatActivity implements ClsContract.IView {

    private ClsPresenter clsPresenter;
    private RecyclerView rv_left;
    private RecyclerView rv_right;
    private DaoMaster.DevOpenHelper helper1;
    private SQLiteDatabase sqlitedatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cls);
        clsPresenter = new ClsPresenter(this);

        //初始化recycleview
        rv_left = findViewById(R.id.rv_left);
        rv_right = findViewById(R.id.rv_right);
        rv_left.setLayoutManager(new LinearLayoutManager(this));
        rv_right.setLayoutManager(new GridLayoutManager(this,2));

        //注册eventbus
        EventBus.getDefault().register(this);

        helper1 = new DaoMaster.DevOpenHelper(this, "clsdb");
        sqlitedatabase = helper1.getWritableDatabase();
        daoMaster = new DaoMaster(sqlitedatabase);
        daoSession = daoMaster.newSession();
        if (RetrofitUtils.getInstance().isNet()){
            clsPresenter.getLeftData();
        }else{
            List<GreendaoEntity> greendaoEntities = daoSession.getGreendaoEntityDao().loadAll();
            if (greendaoEntities!=null&&greendaoEntities.size()>0){
                GreendaoEntity greendaoEntity = greendaoEntities.get(0);
                String json = greendaoEntity.getJson();
                ClsEntity clsEntity = new Gson().fromJson(json, ClsEntity.class);
                LeftAdapter leftAdapter = new LeftAdapter(this, clsEntity.getResult().get(0).getSecondCategoryVo());
                rv_left.setAdapter(leftAdapter);
                leftAdapter.setLeftClick(new LeftAdapter.LeftClick() {
                    @Override
                    public void leftItemClick(String id) {
                        EventBus.getDefault().post(id);
                    }
                });
            }
        }
    }

    @Override
    public void success(Object o) {
        if (o instanceof ClsEntity){
            ClsEntity clsEntity = (ClsEntity) o;
            LeftAdapter leftAdapter = new LeftAdapter(this, clsEntity.getResult().get(0).getSecondCategoryVo());
            rv_left.setAdapter(leftAdapter);

            leftAdapter.setLeftClick(new LeftAdapter.LeftClick() {
                @Override
                public void leftItemClick(String id) {
                    EventBus.getDefault().post(id);
                }
            });
            //缓存数据
            GreendaoEntity greendaoEntity = new GreendaoEntity();
            String s = new Gson().toJson(clsEntity);
            greendaoEntity.setJson(s);
            greendaoEntity.setKey("分类");
            daoSession.getGreendaoEntityDao().insert(greendaoEntity);
        }else if (o instanceof RightEntity){
            RightEntity rightEntity = (RightEntity) o;
            RightAdapter rightAdapter = new RightAdapter(this, rightEntity.getResult());
            rv_right.setAdapter(rightAdapter);
        }
    }
    //接收二级类目的id
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getId(String id){
        HashMap<String,String> params = new HashMap<>();
        params.put("categoryId",id);
        params.put("page","1");
        params.put("count","10");
        clsPresenter.getRightData(params);
    }
    @Override
    public void error(Throwable throwable) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
