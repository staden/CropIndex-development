package org.mtri.navdraw;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by sam on 3/23/15.
 */

public class fragment1 extends android.support.v4.app.Fragment implements View.OnClickListener{



    private TextView displayDate;
    private Button changeDate;

    private int year;
    private int month;
    private int day;

    Button btnShowLocation;
    GPSTracker gps;

    View rootview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootview = inflater.inflate(R.layout.fragment1_layout, container, false);

        displayDate = (TextView) rootview.findViewById(R.id.displayDate);
        changeDate = (Button) rootview.findViewById(R.id.changeDate);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

//        TextView displayDate = (TextView) rootview.findViewById(R.id.displayDate);

        displayDate.setText(new StringBuilder()
                .append(month + 1).append(" - ")
                .append(day).append(" - ")
                .append(year).append(" "));

        btnShowLocation = (Button) rootview.findViewById(R.id.show_location);

        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gps = new GPSTracker(getActivity());

                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(
                            getActivity().getApplicationContext(),
                            "Your Location is -\nLat: " + latitude + "\nLong: "
                                    + longitude, Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        return rootview;
    }


    /*static final int DATE_PICKER_ID = 1111;*/

/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        displayDate = (TextView) rootview.findViewById(R.id.displayDate);
        changeDate = (Button) rootview.findViewById(R.id.changeDate);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

//        TextView displayDate = (TextView) rootview.findViewById(R.id.displayDate);

        displayDate.setText(new StringBuilder()
                .append(month + 1).append(" - ")
                .append(day).append(" - ")
                .append(year).append(" "));*/

//        Button btnChangeDate = (Button) rootview.findViewById(R.id.changeDate);
/*
        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog(DATE_PICKER_ID);

            }
        });
    }


  //  @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case DATE_PICKER_ID:
                // open datepicker dialog
                // set date picker for current date
                // add pickerListener listener to date picker
//                return new DatePickerDialog(this, pickerListener, year, month, day);
        }
        return null;
    }
*/

/*
    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // update displayDate
            displayDate.setText(new StringBuilder()
                    .append(month + 1).append(" - ")
                    .append(day).append(" - ")
                    .append(year).append(" "));
        }
    };

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
*/




    @Override
    public void onClick(View v) {

    }


}


