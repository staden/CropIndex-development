package org.mtri.navdraw;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import java.util.Calendar;

/**
 *
 *  This class holds the "global" variables that the app is designed to collect, and controls
 *  navigation between views.
 *
 */
public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    String headers = "FarmID," +
            "year," +
            "month," +
            "day," +
            "latitude," +
            "longitude," +
            "temperature," +
            "rainfall," +
            "primarycrop," +
            "c1_excellent," +
            "c1_good," +
            "c1_fair," +
            "c1_poor," +
            "c1_vpoor," +
            "humus," +
            "sm_surplus," +
            "sm_adequate," +
            "sm_short," +
            "sm_vshort," +
            "images," +
            "notes";



    // Initialize data
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    double latitude = 0;
    double longitude = 0;

    String owner = "no owner identified";

    String temperature = "null";
    String rainfall = "0";

    String crop_1 = "null";
    Integer crop1_excellent = 0;
    Integer crop1_good = 0;
    Integer crop1_fair = 0;
    Integer crop1_poor = 0;
    Integer crop1_vpoor = 0;

    String humus = "null";
    Integer soilmoisture_surplus = 0;
    Integer soilmoisture_adequate = 0;
    Integer soilmoisture_short = 0;
    Integer soilmoisture_veryshort = 0;

    String notes = null;
    String images = null;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment objFragment = null;

        switch (position){
            case 0:
                objFragment = new fragment0();
                break;
            case 1:
                objFragment = new fragment1();
                break;
            case 2:
                objFragment = new fragment2();
                break;
            case 3:
                objFragment = new fragment3();
                break;
            case 4:
                objFragment = new fragment4();
                break;
            case 5:
                objFragment = new fragment5();
                break;
            case 6:
                objFragment = new fragment6();
                break;
        }
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, objFragment)
                .addToBackStack(null)
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Fragment subFragment = null;
        switch (item.getItemId()) {
            case R.id.action_submit:
                PreviewAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void PreviewAction() {
        // update the main content by replacing fragments
        Fragment subFragment = new fragment7();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, subFragment)
                .addToBackStack(null)
                .commit();
    }

}
