package org.mtri.navdraw;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.Calendar;


/*class GlobalVarsInit extends Application {

    // Initialize date
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    private StringBuilder sub_date = new StringBuilder()
            .append(month + 1).append(" - ")
            .append(day).append(" - ")
            .append(year).append(" ");

    private String sub_location;
    private String sub_owner = "";
    private String sub_temp;
    private String sub_rainfall;
    private String sub_crop1;
    private String sub_crop1_excellent;
    private String sub_crop1_good;
    private String sub_crop1_fair;
    private String sub_crop1_poor;
    private String sub_crop1_vpoor;
    private String sub_sm_surplus;
    private String sub_sm_adequate;
    private String sub_sm_short;
    private String sub_sm_veryshort;



    private String myState;

    public String getState() {
        return myState;
    }
    public void setState(String s) {
        myState = s;
    }

    public StringBuilder getSub_date() {
        return sub_date;
    }
}*/

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

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



/*
    StringBuilder sub_date = new StringBuilder()
            .append(month + 1).append(" - ")
            .append(day).append(" - ")
            .append(year).append(" ");
*/

    /*private TextView displayDate;*/

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

/*
    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section0);
                break;
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
        }
    }
*/

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
