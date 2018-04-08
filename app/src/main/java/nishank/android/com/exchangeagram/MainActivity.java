package nishank.android.com.exchangeagram;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView mRecyclerView;
    private RecyclerView mVerticalRecyclerView;
    private MyAdapter mAdapter1;
    private MyVerticalAdapter mAdapter2;

    ArrayList personNames = new ArrayList<>(Arrays.asList("Nishank", "Prasad", "Vinayak", "Sneha", "Shubham", "Rohan", "Sweety"));
    ArrayList personPost = new ArrayList<>(Arrays.asList("Welcome guys to instagram", "Good Morning #nustachai", "Had a blast at the trip", "Convocation Days #memories", "Vacation #holidays #nirvana",
            "On a Road Trip","First Day @ College"));


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.search_navigation:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.add_image:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.follow_navigation:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.profile_navigation:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_style);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        mRecyclerView=(RecyclerView)findViewById(R.id.stories_rv);
        mRecyclerView.setHasFixedSize(true);

        mVerticalRecyclerView=(RecyclerView)findViewById(R.id.posts_rv);
        mVerticalRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        mVerticalRecyclerView.setLayoutManager(linearLayoutManager2);

        mAdapter1=new MyAdapter(getApplicationContext(),personNames);
        mRecyclerView.setAdapter(mAdapter1);

        mAdapter2=new MyVerticalAdapter(getApplicationContext(),personNames,personPost);
        mVerticalRecyclerView.setAdapter(mAdapter2);

        mTextMessage = (TextView) findViewById(R.id.stories);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }
}
