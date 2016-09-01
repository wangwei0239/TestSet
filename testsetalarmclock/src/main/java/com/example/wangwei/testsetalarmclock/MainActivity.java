package com.example.wangwei.testsetalarmclock;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends Activity {
    Button btnSetAlarm;
    EditText etHour, etMinute;
    int minute, hour, day;
    Calendar cal;

    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        queryClock();
////        setAlarm();
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CALENDAR)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_CALENDAR,Manifest.permission.SET_ALARM}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
//        }
//        addReminder(2016, 9, 2, 10, 10, "起床");
        setCalendar();
//        queryCalendaar();
//        queryReminder();
//        queryCalendar();
    }


    public void queryClock() {
        final String tag_alarm = "tag_alarm";
        Uri uri = Uri.parse("content://com.android.alarmclock/alarm");
        Cursor c = getContentResolver().query(uri, null, null, null, null);
        Log.i(tag_alarm, "no of records are " + c.getCount());
        Log.i(tag_alarm, "no of columns are " + c.getColumnCount());
        if (c != null) {
            String names[] = c.getColumnNames();
            for (String temp : names) {
                System.out.println(temp);
            }
            if (c.moveToFirst()) {
                do {
                    for (int j = 0; j < c.getColumnCount(); j++) {
                        Log.i(tag_alarm, c.getColumnName(j)
                                + " which has value " + c.getString(j));
                    }
                } while (c.moveToNext());
            }
        }
    }

    public static final String[] EVENT_PROJECTION1 = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };

    private static final int PROJECTION_ID_INDEX1 = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;


    public void queryCalendar() {
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"sampleuser@gmail.com", "com.google",
                "sampleuser@gmail.com"};
// Submit the query and get a Cursor object back.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            showLog("query calendar dont have permission");
            return;
        }
        showLog("query calendar have permission");
        cur = cr.query(uri, EVENT_PROJECTION1, null, null, null);

        while (cur.moveToNext()) {
            long calID = 0;
            String displayName = null;
            String accountName = null;
            String ownerName = null;

            // Get the field values
            calID = cur.getLong(PROJECTION_ID_INDEX);
            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

            // Do something with the values...
            showLog("calId:" + calID + " displayname:" + displayName + " accountName:" + accountName + " ownerName:" + ownerName);
        }

    }


    /**
     * worked method
     */
    public void setCalendar() {
        long calID = 1;
        long startMillis = 0;
        long endMillis = 0;
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2016, 8, 15, 7, 30);
        startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2016, 8, 15, 8, 45);
        endMillis = endTime.getTimeInMillis();

        ContentResolver cr = getContentResolver();

        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, startMillis);
        values.put(CalendarContract.Events.DTEND, endMillis);
        values.put(CalendarContract.Events.TITLE, "Jazzercisehaha");
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            showLog("Dont have permission");
            return;
        }
        showLog("has permission");
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
//        Uri uri = cr.insert(Uri.parse("content://com.android.freeme.calendar.statistic"), values);
        long eventID = Long.parseLong(uri.getLastPathSegment());
        showLog(eventID + "");

        ContentResolver cr1 = getContentResolver();
        ContentValues values1 = new ContentValues();
        values1.put(CalendarContract.Reminders.MINUTES, 15);
        values1.put(CalendarContract.Reminders.EVENT_ID, eventID);
        values1.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri1 = cr1.insert(CalendarContract.Reminders.CONTENT_URI, values1);
        showLog("Insert finished");
    }

    public void setCal(int year, int month, int day, int hour, int minute, String title) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute);
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false);
        intent.putExtra("endTime", cal.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", title);
        startActivity(intent);
    }


    private void setAlarm(int hour, int minute) {
        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_HOUR, 18);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 0 + 0);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(i);
    }

    private void addReminder(int statrYear, int startMonth, int startDay, int startHour, int startMinut, String title) {
        // Convert start of begin time of reminder in milliseconds.
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(statrYear, startMonth, startDay, startHour, startMinut);
        long startMillis = beginTime.getTimeInMillis();

        // String to access default google calendar of device for Event setting.
        String eventUriString = "content://com.android.calendar/events";

        // Creation of Event.
        ContentValues eventValues = new ContentValues();
        // Set calendar as 1 for default calendar.
        eventValues.put(CalendarContract.Events.CALENDAR_ID, 1);
        // Set title as user define.
        eventValues.put(CalendarContract.Events.TITLE, title);
        // Set description as user define.
        eventValues.put(CalendarContract.Events.DESCRIPTION, "MYApp");
        // Set location as user define.
        eventValues.put(CalendarContract.Events.EVENT_TIMEZONE, "India");
        // Set start time as system time or time converted in milliseconds.
        eventValues.put(CalendarContract.Events.DTSTART, startMillis);
        eventValues.put(CalendarContract.Events.DURATION, 300000);
        // Set status of event as 1.
        eventValues.put("eventStatus", 1);
        // Set visibility of event as 3 (public).
        eventValues.put("visibility", 3);
        // Set transparency as 0. No other app seen through reminder.
        eventValues.put("transparency", 0);
        // Set alarm as 1. Ringing.
        eventValues.put(CalendarContract.Events.HAS_ALARM, 1);

        // Set Event in calendar.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            showLog("add reminder dont have permission");
            return;
        }
        showLog("add reminder have permission");
        Uri eventUri = getContentResolver().insert(CalendarContract.Events.CONTENT_URI, eventValues);
        // Getting ID of event in Long.
        long eventID = Long.parseLong(eventUri.getLastPathSegment());

        /***************** Event: Reminder(with alert) Adding reminder to event *******************/
        // String to access default google calendar of device for reminder setting.
        String reminderUriString = "content://com.android.calendar/reminders";
        ContentValues reminderValues = new ContentValues();

        // Set reminder on Event ID.
        reminderValues.put("event_id", eventID);
        // Set reminder minute before.
        reminderValues.put("minutes", 1);
        // Set method of reminder
        reminderValues.put("method", 1);


        @SuppressWarnings("unused")
        //Setting reminder in calendar on Event.
                Uri reminderUri = getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
//                addReminder(2016,9,1,10,10,"起床");
                queryClock();
            } else {
                // Permission Denied
                System.out.println("Permission denied");
            }
        }
    }

    private void showLog(String log) {
        Log.i("Log", log);
    }


    // Projection array. Creating indices for this array instead of doing
// dynamic lookups improves performance.
    public static final String[] EVENT_PROJECTION = new String[]{
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT,                 // 3
            CalendarContract.Calendars.SYNC_EVENTS
    };

    // The indices for the projection array above.
//    private static final int PROJECTION_ID_INDEX = 0;
//    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
//    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
//    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

    private void queryCalendaar() {

        // Run query
        Cursor cur = null;
        ContentResolver cr = getContentResolver();
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{"sampleuser@gmail.com", "com.google",
                "sampleuser@gmail.com"};
// Submit the query and get a Cursor object back.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            showLog("query dont have permission");
            return;
        }
        showLog("query have permission");
        cur = cr.query(uri, null, null, null, null);

        showLog(""+cur.getCount());


        while (cur.moveToNext()) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < cur.getColumnCount(); i++){
                sb.append(cur.getColumnName(i));
                sb.append(":");
                sb.append(cur.getString(i));
                sb.append(";");
                sb.append("\n");
            }
            showLog(sb.toString());
        }
//            for()

//            long calID = 3;
//            String displayName = null;
//            String accountName = null;
//            String ownerName = null;



//            // Get the field values
//            calID = cur.getLong(PROJECTION_ID_INDEX);
//            displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
//            accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
//            ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);
//
//
//            // Do something with the values...
//
//            showLog("CalId:"+calID+" account:"+accountName+" displayName:"+displayName+" ownerName:"+ownerName);

//        }

    }


    public static final String[] INSTANCE_PROJECTION = new String[] {
            CalendarContract.Instances.EVENT_ID,      // 0
            CalendarContract.Instances.BEGIN,         // 1
            CalendarContract.Instances.TITLE          // 2
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_BEGIN_INDEX = 1;
    private static final int PROJECTION_TITLE_INDEX = 2;

    public void queryReminder(){
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2015, 9, 23, 8, 0);
        long startMillis = beginTime.getTimeInMillis();
        Calendar endTime = Calendar.getInstance();
        endTime.set(2017, 10, 24, 8, 0);
        long endMillis = endTime.getTimeInMillis();

        Cursor cur = null;
        ContentResolver cr = getContentResolver();

// The ID of the recurring event whose instances you are searching
// for in the Instances table
        String selection = CalendarContract.Instances.EVENT_ID + " = ?";
        String[] selectionArgs = new String[] {"207"};

// Construct the query with the desired date range.
        Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon();
        ContentUris.appendId(builder, startMillis);
        ContentUris.appendId(builder, endMillis);

// Submit the query
        cur =  cr.query(builder.build(),
                null,
                null,
                null,
                null);

        while (cur.moveToNext()) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < cur.getColumnCount(); i++){
                sb.append(cur.getColumnName(i));
                sb.append(":");
                sb.append(cur.getString(i));
                sb.append(";");
                sb.append("\n");
            }
            showLog(sb.toString());
        }

//        while (cur.moveToNext()) {
//            String title = null;
//            long eventID = 0;
//            long beginVal = 0;
//
//            // Get the field values
//            eventID = cur.getLong(PROJECTION_ID_INDEX);
//            beginVal = cur.getLong(PROJECTION_BEGIN_INDEX);
//            title = cur.getString(PROJECTION_TITLE_INDEX);
//
//            // Do something with the values.
//            Log.i("Log", "Event:  " + title);
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(beginVal);
//            DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//            Log.i("Log", "Date: " + formatter.format(calendar.getTime()));
//        }
    }

}

