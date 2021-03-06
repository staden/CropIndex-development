package org.mtri.navdraw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
/**
 * Created by sam on 3/23/15.
 *
 *  This fragment displays all data to be submitted,
 *  and provides options for how to submit.
 *
 */

public class fragment7 extends android.support.v4.app.Fragment implements service_reciever.Receiver {

    View rootview;
    private service_reciever mReceiver;
    private TextView email;
    private View view;
    ViewGroup parent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReceiver = new service_reciever(new Handler());
        mReceiver.setReceiver(this);
        view = LayoutInflater.from(getActivity()).inflate(R.layout.popup, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
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
        final TextView poor = (TextView) rootview.findViewById(R.id.submit_c1_poor);
        TextView vpoor = (TextView) rootview.findViewById(R.id.submit_c1_vpoor);
        TextView humus = (TextView) rootview.findViewById(R.id.submit_humus);
        TextView surplus = (TextView) rootview.findViewById(R.id.submit_surplus);
        TextView adequate = (TextView) rootview.findViewById(R.id.submit_sm_adequate);
        TextView vshort = (TextView) rootview.findViewById(R.id.submit_sm_short);
        TextView vvshort = (TextView) rootview.findViewById(R.id.submit_sm_vshort);
        TextView images = (TextView) rootview.findViewById(R.id.submit_images);
        TextView notes = (TextView) rootview.findViewById(R.id.submit_notes);

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
        images.setText(String.valueOf(activityData.images));
        notes.setText(String.valueOf(activityData.notes));

        boolean network;

        /*
        ConnectivityManager connMgr = (ConnectivityManager)
                this.getActivity().getSystemService(this.getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //GPS Enabled
            Toast.makeText(this.getActivity(), "Network Enabled, test 3.",
                    Toast.LENGTH_LONG).show();
            network = true;
        } else {
            //GPS Enabled
            Toast.makeText(this.getActivity(), "Network Disabled, test 2",
                    Toast.LENGTH_LONG).show();
            network = false;
        }
        */

        email = (TextView) rootview.findViewById(R.id.email);

        // configure button to save data as CSV to SD card
        Button btnLocalData = (Button) rootview.findViewById(R.id.local_storage_button);
        btnLocalData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //onCreateDialog();
                //popup();
                onSelected();
            }

            public void onSelected() {

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

                    Toast.makeText(getActivity(), "Record added to local_forms.csv at " + myXDir.toString(), Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getActivity(), submit.class);
                intent.putExtra("reciever", mReceiver);
                getActivity().startService(intent);
            }
        });

        // configure button to email csv to mtri.fs.data@gmail.com
        Button btnEmailData = (Button) rootview.findViewById(R.id.email_data_button);
        btnEmailData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    onSelected();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            private void onSelected() throws FileNotFoundException {

                // save data to EXTERNAL STORAGE
                File myDir = Environment.getExternalStorageDirectory();
                String FILENAME = myDir+"/single_record.csv";
                //FileOutputStream fos = getActivity().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                FileOutputStream fos = new FileOutputStream(FILENAME);
                String csvStr = activityData.headers+"\n"+csv_output+"\n";
                try {
                    fos.write(csvStr.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // email activityData to mtri.fs.data@gmail.com
                Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"mtri.fs.data@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, activityData.month+"_"
                        +activityData.day+"_"
                        +activityData.year+"_"
                        +activityData.owner); // format email subject
                i.putExtra(Intent.EXTRA_TEXT, csvStr); // format email body

                // add single_record.csv and image
                String[] filePaths = new String[] {FILENAME,
                        Environment.getExternalStorageDirectory()+"/"+activityData.images};
                ArrayList<Uri> uris = new ArrayList<Uri>();
                for (String file : filePaths)
                {
                    File fileIn = new File(file);
                    Uri u = Uri.fromFile(fileIn);
                    uris.add(u);
                }
                i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                startActivity(i);
            }
        });
        return rootview;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        if (resultCode == 1){
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getActivity())
                            .setSmallIcon(R.drawable.cropindex_logo_small)
                            .setContentTitle("Data connection avaiable")
                            .setContentText("Click on the notification to email your data!");
            NotificationManager mNotificationManager =
                    (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = mBuilder.build();
            notification.defaults|= Notification.DEFAULT_SOUND;
            notification.defaults|= Notification.DEFAULT_LIGHTS;
            notification.defaults|= Notification.DEFAULT_VIBRATE;
            mNotificationManager.notify(1, notification);

        }
    }
}
