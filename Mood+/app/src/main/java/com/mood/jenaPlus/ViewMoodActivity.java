package com.mood.jenaPlus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.gms.maps.model.LatLng;
import java.lang.reflect.Field;


/**
 * Created by carrotji on 2017-03-06.
 */
public class ViewMoodActivity extends AppCompatActivity implements MPView<MoodPlus> {

    /**
     * The A id.
     */
    protected String aId;
    /**
     * The A text.
     */
    protected String aText;
    /**
     * The A date.
     */
    protected String aDate;
    /**
     * The Add location.
     */
    protected Boolean addLocation;
    /**
     * The A Location.
     */
    protected String aLocation;
    /**
     * The A Latitude.
     */
    protected Double aLatitude;
    /**
     * The A Longitude.
     */
    protected Double aLongitude;
    /**
     * The A social.
     */
    protected String aSocial;
    /**
     * The A photo.
     */
    protected String aPhoto;
    /**
     * The A color.
     */
    protected String aColor;
    /**
     * The Icon.
     */
    protected ImageView icon;
    /**
     * The Situation.
     */
    protected TextView situation;
    /**
     * The Date.
     */
    protected TextView date;
    /**
     * The Message.
     */
    protected TextView message;

    /**
     * The Location.
     */
    protected ImageButton locationButton;

    protected ImageView cameraImage;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MoodPlus mp = MoodPlusApplication.getMoodPlus();
        mp.addView(this);

        Mood mood = (Mood)getIntent().getSerializableExtra("aMood");
        aId = mood.getId();
        aText = mood.getText();
        aDate = mood.getDate().toString();
        addLocation = mood.getAddLocation();
        aLatitude = mood.getLatitude();
        aLongitude = mood.getLongitude();
        aSocial = mood.getSocial();
        aPhoto = mood.getPhoto();
        aColor = mood.getColor();

        icon = (ImageView) findViewById(R.id.cur_mood);

        /**
         * This gets the resource id
         */
        //Taken from http://stackoverflow.com/questions/3276968/drawable-getresources-getidentifier-problem
        // 13 March 2017 12:01
        int recId = getResources().getIdentifier(aId, "drawable", getApplicationContext().getPackageName());
        icon.setImageResource(recId);

        situation = (TextView) findViewById(R.id.situation);
        situation.setText(aSocial);


        date = (TextView) findViewById(R.id.time);
        date.setText(aDate);

        message = (TextView) findViewById(R.id.message);
        message.setText(aText);

        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.parseColor(aColor));

        cameraImage = (ImageView) findViewById(R.id.camera_image);
        //Bitmap mBitmap = getIntent().getParcelableExtra("bmp_img");
        //cameraImage.setImageBitmap(mBitmap);

        locationButton = (ImageButton) findViewById(R.id.test_location);

        if(addLocation){
            locationButton.setVisibility(View.VISIBLE); //To set visible
        }
        else{
            System.out.println("DID NOT CLICK LOCATION");

        }

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addLocation){
                    //Taken from http://stackoverflow.com/questions/30106507/pass-longitude-and-latitude-with-intent-to-another-class
                    //2017-03-21
                    LatLng position = new LatLng(aLatitude,aLongitude);

                    Bundle args = new Bundle();
                    args.putParcelable("longLat_dataProvider",position);
                    Intent intent = new Intent(ViewMoodActivity.this, MapActivity.class);
                    intent.putExtras(args);
                    startActivity(intent);
                }
                else{
                    Log.i("Location","LOCATION NOT ADDED");
                }

            }
        });


    }

    /**
     * Gets id.
     *
     * @param resourceName the resource name
     * @param c            the c
     * @return the id
     */
    // Taken from http://stackoverflow.com/questions/4427608/android-getting-resource-id-from-string
    // 12 Mar 2017 12:42
    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    public void update(MoodPlus moodPlus){
        // TODO implements update method
    }

}
