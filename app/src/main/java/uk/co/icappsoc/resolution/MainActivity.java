package uk.co.icappsoc.resolution;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The user will enter their resolution here
        final EditText resolution = (EditText) findViewById(R.id.resolution_text);

        Button commit = (Button) findViewById(R.id.set_resolution_button);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a random calendar appointment in January!
                Calendar startTime = Calendar.getInstance();
                Random random = new Random();
                startTime.set(2015, Calendar.JANUARY, 1 + random.nextInt(31));

                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, "Resolution ALERT")
                        .putExtra(CalendarContract.Events.DESCRIPTION, resolution.getText().toString())
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Share with any compatible application on the device!
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "My 2015 resolution is " +
                        resolution.getText().toString() + " #YOLO");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }
}
