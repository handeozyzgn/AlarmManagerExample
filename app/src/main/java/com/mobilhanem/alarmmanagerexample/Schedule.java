package com.mobilhanem.alarmmanagerexample;

/**
 * Created by Bengu on 15.3.2017.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Schedule extends AppCompatActivity {

    String birthday = "";
    String rDate = "";
    private TextView eventTitle;
    private Button startAlarmBtn;
    private TimePickerDialog timePickerDialog;
    final static int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        final Calendar c = Calendar.getInstance();
        int year = c.get(c.YEAR);
        final int month = c.get(c.MONTH) + 1;
        int dayOfMonth = c.get(c.DAY_OF_MONTH);

        //Get the widgets reference from XML layout
        final TextView tv = (TextView) findViewById(R.id.tv);
        final Button dateButton = (Button) findViewById(R.id.dateButton);
        final DatePicker dp = (DatePicker) findViewById(R.id.dp);
        eventTitle = (TextView) findViewById(R.id.eventTitle);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rDate = df.format(c.getTime());
        //Display the DatePicker initial date
        tv.setText("Initial Date [mm/dd/yyyy]:\n" + month + "/" + dayOfMonth + "/" + year);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the DatePicker Selected Date
                tv.setText("Selected Date of Birth: [mm/dd/yyyy]\n");
                tv.setText(tv.getText() + "" + (dp.getMonth() + 1) + "/" + dp.getDayOfMonth() + "/" + dp.getYear());
                birthday = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth();
                dp.updateDate(c.get(c.YEAR) + 1, c.get(c.MONTH) + 2, c.get(c.DAY_OF_MONTH) + 10);

                openPickerDialog(false);
            }
        });


    }

    private void openPickerDialog(boolean is24hour) {

        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(
                Schedule.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24hour);
        timePickerDialog.setTitle("Alarm Ayarla");

        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener
            = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0){

                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }};

    private void setAlarm(Calendar alarmCalender){


        Toast.makeText(getApplicationContext(),"Alarm Ayarlandı!",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), REQUEST_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }

}

