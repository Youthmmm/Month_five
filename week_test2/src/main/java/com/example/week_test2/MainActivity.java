package com.example.week_test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.week_test2.adapter.LeftAdapter;
import com.example.week_test2.adapter.RightAdapter;
import com.example.week_test2.contract.ClsContract;
import com.example.week_test2.entity.ClsEntity;
import com.example.week_test2.entity.GreendaoEntity;
import com.example.week_test2.entity.RightEntity;
import com.example.week_test2.presenter.ClsPresenter;
import com.example.week_test2.utils.RetrofitUtils;
import com.google.gson.Gson;
import com.xiaoyehai.landsurvey.greendao.DaoMaster;
import com.xiaoyehai.landsurvey.greendao.DaoSession;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ClsContract.IView {

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
        setContentView(R.layout.activity_main);
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
            }
        }
    }

    @Override
    public void success(Object o) {
        if (o instanceof ClsEntity){
            ClsEntity clsEntity = (ClsEntity) o;
            //缓存数据
            GreendaoEntity greendaoEntity = new GreendaoEntity();
            String s = new Gson().toJson(clsEntity);
            greendaoEntity.setJson(s);
            greendaoEntity.setKey("分类");
            daoSession.getGreendaoEntityDao().insert(greendaoEntity);
        }else if (o instanceof RightEntity){
            RightEntity rightEntity = (RightEntity) o;
        }
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
