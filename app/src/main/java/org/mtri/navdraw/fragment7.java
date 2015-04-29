package org.mtri.navdraw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Timestamp;

/**
 * Created by sam on 3/23/15.
 *
 *  This fragment displays all data to be submitted.
 *
 */


public class fragment7 extends android.support.v4.app.Fragment {

    View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootview = inflater.inflate(R.layout.fragment7_layout, container, false);

        // get activityData
        final MainActivity activityData = (MainActivity)getActivity();

        // format CSV output
        final String csv_output = activityData.owner + "," +
                activityData.year + "," +
                activityData.month + "," +
                activityData.day +  "," +
                activityData.latitude +  "," +
                activityData.longitude +  "," +
                activityData.temperature +  "," +
                activityData.rainfall +  "," +
                activityData.crop_1 +  "," +
                activityData.crop1_excellent + "," +
                activityData.crop1_good +  "," +
                activityData.crop1_fair +  "," +
                activityData.crop1_poor +  "," +
                activityData.crop1_vpoor +  "," +
                activityData.humus +  "," +
                activityData.soilmoisture_surplus + "," +
                activityData.soilmoisture_adequate +  "," +
                activityData.soilmoisture_short +  "," +
                activityData.soilmoisture_veryshort + "," +
                activityData.images + "," +
                activityData.notes;

        // set layout objects
        TextView year = (TextView) rootview.findViewById(R.id.submit_year);
        TextView month = (TextView) rootview.findViewById(R.id.submit_month);
        TextView day = (TextView) rootview.findViewById(R.id.submit_day);
        TextView latitude = (TextView) rootview.findViewById(R.id.submit_latitude);
        TextView longitude = (TextView) rootview.findViewById(R.id.submit_longitude);
        TextView IDFarm = (TextView) rootview.findViewById(R.id.submit_IDFarm);
        TextView temperature = (TextView) rootview.findViewById(R.id.submit_temperature);
        TextView rainfall = (TextView) rootview.findViewById(R.id.submit_rainfall);
        TextView crop1 = (TextView) rootview.findViewById(R.id.submit_crop1);
        TextView excellent = (TextView) rootview.findViewById(R.id.submit_c1_excellent);
        TextView good = (TextView) rootview.findViewById(R.id.submit_c1_good);
        TextView fair = (TextView) rootview.findViewById(R.id.submit_c1_fair);
        TextView poor = (TextView) rootview.findViewById(R.id.submit_c1_poor);
        TextView vpoor = (TextView) rootview.findViewById(R.id.submit_c1_vpoor);
        TextView humus = (TextView) rootview.findViewById(R.id.submit_humus);
        TextView surplus = (TextView) rootview.findViewById(R.id.submit_surplus);
        TextView adequate = (TextView) rootview.findViewById(R.id.submit_sm_adequate);
        TextView vshort = (TextView) rootview.findViewById(R.id.submit_sm_short);
        TextView vvshort = (TextView) rootview.findViewById(R.id.submit_sm_vshort);

        // display activityData
        year.setText(String.valueOf(activityData.year));
        month.setText(String.valueOf(activityData.month));
        day.setText(String.valueOf(activityData.day));
        latitude.setText(String.valueOf(activityData.latitude));
        longitude.setText(String.valueOf(activityData.longitude));
        IDFarm.setText(activityData.owner);
        temperature.setText(String.valueOf(activityData.temperature));
        rainfall.setText(String.valueOf(activityData.rainfall));
        crop1.setText(activityData.crop_1);
        excellent.setText(String.valueOf(activityData.crop1_excellent));
        good.setText(String.valueOf(activityData.crop1_good));
        fair.setText(String.valueOf(activityData.crop1_fair));
        poor.setText(String.valueOf(activityData.crop1_poor));
        vpoor.setText(String.valueOf(activityData.crop1_vpoor));
        humus.setText(activityData.humus);
        surplus.setText(String.valueOf(activityData.soilmoisture_surplus));
        adequate.setText(String.valueOf(activityData.soilmoisture_adequate));
        vshort.setText(String.valueOf(activityData.soilmoisture_short));
        vvshort.setText(String.valueOf(activityData.soilmoisture_veryshort));

        // configure button to save data as CSV/JSON?
        Button btnSubmitData = (Button) rootview.findViewById(R.id.submit_data_button);
        btnSubmitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onSelected();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            private void onSelected() throws FileNotFoundException {
                JSONObject form_record = new JSONObject();
                try {

                    // output formatting
                    form_record.put("id", activityData.owner);
                    form_record.put("latitude", activityData.latitude);
                    form_record.put("longitude", activityData.longitude);
                    form_record.put("everything", activityData);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String FILENAME = "test_output";

                // save data to EXTERNAL STORAGE
                File myXDir = Environment.getExternalStorageDirectory();
                File file = new File(myXDir + "/local_forms.csv");
                if (!file.exists()) try {

                        // create file with headers if local csv doesn't exist
                        file.createNewFile();
                        FileOutputStream os = new FileOutputStream(file,true);
                        OutputStreamWriter out = new OutputStreamWriter(os);
                        out.write(activityData.headers+"\n");
                        out.close();
                        /*FileWriter fout = new FileWriter(file.getName(),true);
                        fout.write(activityData.headers+"\n");*/
                    } catch (IOException e) {
                        e.printStackTrace();
                }
                if (file.exists()) try {

                    // write new line to csv
                    FileOutputStream fos = null;
                    fos = new FileOutputStream(file, true);
                    OutputStreamWriter out = new OutputStreamWriter(fos);
                    out.write(csv_output+"\n");
                    out.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }


                // save data to INTERNAL STORAGE
                File myDir = getActivity().getFilesDir();
                FileOutputStream fos = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                String jsonStr = form_record.toString();
                try {
                    fos.write(jsonStr.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // confirm save action
                String s="";
                try {
                    FileInputStream fileIn=getActivity().openFileInput("test_output");
                    InputStreamReader InputRead= new InputStreamReader(fileIn);

                    char[] inputBuffer= new char[16];

                    int charRead;

                    while ((charRead=InputRead.read(inputBuffer))>0) {
                        // char to string conversion
                        String readstring=String.copyValueOf(inputBuffer,0,charRead);
                        s +=readstring;
                    }
                    InputRead.close();
                    Toast.makeText(getActivity(), s + " saved to " + myDir.toString(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                // email activityData to mtri.fs.data@gmail.com
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mtri.fs.data@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, activityData.month+"_"
                        +activityData.day+"_"
                        +activityData.year+"_"
                        +activityData.owner); // format email subject
                i.putExtra(Intent.EXTRA_TEXT, s); // format email body

                // attach file
                Uri u1;
                File outgoing = new File("test_output");
                u1 = Uri.fromFile(outgoing);
                i.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:/"+myXDir+"/local_forms.csv"));
                //i.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.toString()+"test_output"));
                //i.putExtra(Intent.ACTION_GET_CONTENT, myDir.toString()+"/test_output");
                //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // TEST THIS!!
                //Uri uri = Uri.fromFile(new File(myDir.toString()+"/test_output"));
                //i.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(i);




            }
        });



        return rootview;
    }

}
