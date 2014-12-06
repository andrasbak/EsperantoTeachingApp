package com.example.andras.esperantoapp.ui;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andras.esperantoapp.R;

import org.json.JSONObject;

import java.sql.SQLOutput;

/**
 * Created by Andras on 24-11-2014.
 */
public class ScreenTypeVortludoj extends Fragment implements View.OnClickListener, SensorEventListener, View.OnLongClickListener, View.OnDragListener{

    private JSONObject jsondata;
    String answer;
    TextView droptext, pretext, word1, word2, word3, word4, word5, word6;
    ImageButton continueButton;
    SensorManager sensorManager;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVortludoj = inflater.inflate(R.layout.fragment_type_vortludoj, container, false);

        droptext = (TextView) viewVortludoj.findViewById(R.id.textDrop);
        pretext = (TextView) viewVortludoj.findViewById(R.id.textPrewords);

        word1 = (TextView)viewVortludoj.findViewById(R.id.text1);
        word2 = (TextView)viewVortludoj.findViewById(R.id.text2);
        word3 = (TextView)viewVortludoj.findViewById(R.id.text3);
        word4 = (TextView)viewVortludoj.findViewById(R.id.text4);
        word5 = (TextView)viewVortludoj.findViewById(R.id.text5);
        word6 = (TextView)viewVortludoj.findViewById(R.id.text6);

        word1.setOnLongClickListener(this);
        word2.setOnLongClickListener(this);
        word3.setOnLongClickListener(this);
        word4.setOnLongClickListener(this);
        word5.setOnLongClickListener(this);
        word6.setOnLongClickListener(this);
        droptext.setOnDragListener(this);

        continueButton = (ImageButton)viewVortludoj.findViewById(R.id.continueButton4);
        continueButton.setOnClickListener(this);
        continueButton.setVisibility(View.INVISIBLE);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        try{
            jsondata = new JSONObject(getArguments().getString("jsondata"));
            answer = jsondata.optString("answer");
            word1.setText(jsondata.optString("word1"));
            word2.setText(jsondata.optString("word2"));
            word3.setText(jsondata.optString("word3"));
            word4.setText(jsondata.optString("word4"));
            word5.setText(jsondata.optString("word5"));
            word6.setText(jsondata.optString("word6"));
            pretext.setText(jsondata.optString("phrase"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        textVisibility();
        return viewVortludoj;
    }

    @Override
    public void onSensorChanged(SensorEvent e) {

        int sensortype = e.sensor.getType();
        if (sensortype == Sensor.TYPE_ACCELEROMETER) {
            double sum = Math.abs(e.values[0]) + Math.abs(e.values[1]) + Math.abs(e.values[2]);
            if (sum > 4 * SensorManager.GRAVITY_EARTH) {

                droptext.setText("");

            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();

        int hyppighed = 250000; // 4 gange i sekundet

        for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
            System.out.println("sensor=" + sensor);
            sensorManager.registerListener(this, sensor, hyppighed);
        }

    }

    public void checkSentence(){

        System.out.println("HEJSA!!: " + answer);
        System.out.println(droptext.getText().toString());
        System.out.println("GUESS: " + pretext.getText().toString() + droptext.getText().toString() + "------------------------------------------------------------------------------");
        System.out.println("GUESS: " + answer + "--------------------------------------------------------------");
        if(answer.equals(pretext.getText().toString() + droptext.getText().toString())){

            continueButton.setVisibility(View.VISIBLE);
            Toast.makeText(getActivity(), "Correct! You Passed!", Toast.LENGTH_SHORT).show();

        }
    }

    public void onClick(View v){

        if(v.equals(continueButton)){

            ((LessonActivity) getActivity()).addFragment();
            ((LessonActivity) getActivity()).skiftBillede();

        }

    }

    @Override
    public boolean onLongClick(View v)
    {
        TextView word = (TextView) v;
        droptext.setBackgroundColor(Color.YELLOW);
        View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(v);
        ClipData data = ClipData.newPlainText("", "");
        v.startDrag(data, myShadowBuilder, word, 0);

        return true;
    }

    @Override
    public boolean onDrag(View v, DragEvent event)
    {
        int dragEvent = event.getAction();
        TextView dropText = (TextView) v;
        switch(dragEvent)
        {
            case DragEvent.ACTION_DRAG_ENTERED:
                dropText.setBackgroundColor(Color.GREEN);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                dropText.setBackgroundColor(Color.YELLOW);
                break;
            case DragEvent.ACTION_DROP:
                TextView draggedText = (TextView)event.getLocalState();
                String firstchar = draggedText.getText().toString().substring(0, 1);
                Boolean lastchar = draggedText.getText().toString().endsWith("-");
                System.out.println(lastchar);
                String guess = pretext.getText().toString() + droptext.getText().toString();

                if(firstchar.equals("-") && guess.endsWith("-")){

                    dropText.append(draggedText.getText().toString().substring(1, draggedText.length())+" ");
                    checkSentence();
                }
                else if(firstchar.equals("-")){
                    dropText.append(draggedText.getText().toString() + " ");
                    checkSentence();
                }
                else if(lastchar.equals(true)){
                    dropText.append(draggedText.getText().toString());
                    checkSentence();
                    System.out.println("GUESS 2: " + guess);
                }
                break;
        }
        return true;
    }

    public void textVisibility(){

        if ((jsondata.optString("word1")).equals("")){
            word1.setVisibility(View.INVISIBLE);
        }
        if ((jsondata.optString("word2")).equals("")){
            word2.setVisibility(View.INVISIBLE);
        }
        if ((jsondata.optString("word3")).equals("")){
            word3.setVisibility(View.INVISIBLE);
        }
        if ((jsondata.optString("word4")).equals("")){
            word4.setVisibility(View.INVISIBLE);
        }
        if ((jsondata.optString("word5")).equals("")){
            word5.setVisibility(View.INVISIBLE);
        }
        if ((jsondata.optString("word6")).equals("")){
            word6.setVisibility(View.INVISIBLE);
        }

    }

    private class MyShadowBuilder extends View.DragShadowBuilder
    {
        private Drawable shadow;
        public MyShadowBuilder(View v)
        {
            super(v);
            shadow = new ColorDrawable(Color.YELLOW);
        }
        @Override
        public void onDrawShadow(Canvas canvas)
        {
            shadow.draw(canvas);
        }
        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint)
        {
            int height, width;
            height = (int) getView().getHeight()*1;
            width = (int) getView().getHeight()*2;
            shadow.setBounds(0, 0, width, height);
            shadowSize.set(width, height);
            shadowTouchPoint.set(width/2, height/2);
        }
    }
}
