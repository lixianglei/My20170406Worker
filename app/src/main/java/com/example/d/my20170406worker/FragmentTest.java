package com.example.d.my20170406worker;

/**
 * Created by d on 2017/4/6.
 */
import android.app.Fragment;
import com.example.d.my20170406worker.bean.FragmentAdapter;
import com.example.d.my20170406worker.fragment.EverydayActivity;
import com.example.d.my20170406worker.fragment.OpenSourceActivity;
import com.example.d.my20170406worker.fragment.RecommendActivity;
import com.example.d.my20170406worker.fragment.TechnologyActivity;

import java.util.ArrayList;
public class FragmentTest extends Fragment {
    private ArrayList<String> mListName = new ArrayList<>();
    private ArrayList<Fragment> mList = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private EverydayActivity everydayActivity;
    private OpenSourceActivity openSourceActivity;
    private RecommendActivity recommendActivity;
    private TechnologyActivity technologyActivity;
//    private TabLayout mTab;
//    private ViewPager mViewPager;
//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.activity_fragment);
//        mTab= (TabLayout) findViewById(R.id.Two_TabLayout);
//        mViewPager= (ViewPager) findViewById(R.id.Two_ViewPager);
//        init();
//    }
//    public void init(){
//        mList.add(everydayActivity=new EverydayActivity());
////      mList.add(openSourceActivity=new OpenSourceActivity());
//        mList.add(recommendActivity=new RecommendActivity());
//        mList.add(technologyActivity=new TechnologyActivity());
//        mListName.add("开源资讯");
//        mListName.add("推荐博客");
//        mListName.add("技术问答");
//        mListName.add("每日一博");
//        mTab.setTabMode(TabLayout.MODE_FIXED);
//        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mList, mListName);
//        mViewPager.setAdapter(fragmentAdapter);
//        //给每个ViewPager设置点击事件，点击得到每个的标题
//
//        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab) {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
////                mText.setText(mListName.get(position));
//            }
//        });
//
//        //添加下面的菜单
//        mTab.addTab(mTab.newTab().setText(mListName.get(0)));
//        mTab.addTab(mTab.newTab().setText(mListName.get(1)));
//        mTab.addTab(mTab.newTab().setText(mListName.get(2)));
//        mTab.addTab(mTab.newTab().setText(mListName.get(3)));
//        mTab.setupWithViewPager(mViewPager);
//    }
}
