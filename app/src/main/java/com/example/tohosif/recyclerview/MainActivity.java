package com.example.tohosif.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tohosif.db.DatabaseHelper;
import com.example.tohosif.fragment.MyAdapter;
import com.example.tohosif.fragment.RegisterAndUpdateActivity;
import com.example.tohosif.fragment.Tab1;
import com.example.tohosif.fragment.Tab2;
import com.example.tohosif.fragment.Tab3;
import com.example.tohosif.model.UserFromDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, MyAdapter.CallbackInterface {
    DatabaseHelper myDb;
    private DrawerLayout drawerLayout;
    private ListView listView;
    private String[] myList;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentRefreshListener fragmentRefreshListener;

    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);

        myDb = new DatabaseHelper(this);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp((DrawerLayout) findViewById(R.id.drawerLayout), toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        myList = getResources().getStringArray(R.array.my_list);
        listView = (ListView) drawerLayout.findViewById(R.id.drawerList);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList));
        listView.setOnItemClickListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(this, WebviewActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, myList[position] + " was selected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.navigate) {
            startActivity(new Intent(this, SubActivity.class));
        } else {
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onHandleSelection(UserFromDatabase user) {
        Intent intent = new Intent(this, RegisterAndUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {

//                MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
//                Fragment frg = myPagerAdapter.getItem(0);
//                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.detach(frg);
//                ft.attach(frg);
//                ft.commit();

            if (getFragmentRefreshListener() != null) {
                getFragmentRefreshListener().onRefresh();
            }

        }
    }

    public interface FragmentRefreshListener {
        void onRefresh();
    }

    public static class MyFragment extends Fragment {
        private TextView textView;

        public static MyFragment getInstance(int position) {
            MyFragment myFragment = new MyFragment();
            Bundle args = new Bundle();
            args.putInt("position", position + 1);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            View layout = inflater.inflate(R.layout.fragment_my, container, false);
            textView = (TextView) layout.findViewById(R.id.tv);
            Bundle bundle = getArguments();
            if (bundle != null) {
                textView.setText("Tab" + bundle.getInt("position"));
            }
            return layout;
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;
                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                case 2:
                    Tab3 tab3 = new Tab3();
                    return tab3;
                case 3:
                    MyFragment myFragment = MyFragment.getInstance(position);
                    return myFragment;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}
