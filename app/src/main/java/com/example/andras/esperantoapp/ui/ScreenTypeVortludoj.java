package com.example.andras.esperantoapp.ui;

import android.content.ClipData;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.andras.esperantoapp.R;

import org.json.JSONObject;
/**
 * Created by Andras on 24-11-2014.
 */
public class ScreenTypeVortludoj extends Fragment implements View.OnClickListener, SensorEventListener{

    private JSONObject jsondata;
    String answer;
    TextView droptext, pretext, word1, word2, word3, word4, word5, word6;
    Button continueButton;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVortludoj = inflater.inflate(R.layout.fragment_type_demando, container, false);

        droptext = (TextView) viewVortludoj.findViewById(R.id.textDrop);
        pretext = (TextView) viewVortludoj.findViewById(R.id.textPrewords);

        word1 = (TextView)viewVortludoj.findViewById(R.id.text1);
        word2 = (TextView)viewVortludoj.findViewById(R.id.text2);
        word3 = (TextView)viewVortludoj.findViewById(R.id.text3);
        word4 = (TextView)viewVortludoj.findViewById(R.id.text4);
        word5 = (TextView)viewVortludoj.findViewById(R.id.text5);
        word6 = (TextView)viewVortludoj.findViewById(R.id.text6);

        word1.setOnLongClickListener(longListener);
        word2.setOnLongClickListener(longListener);
        word3.setOnLongClickListener(longListener);
        word4.setOnLongClickListener(longListener);
        word5.setOnLongClickListener(longListener);
        word6.setOnLongClickListener(longListener);
        droptext.setOnDragListener(dragListener);

        continueButton = (Button)viewVortludoj.findViewById(R.id.continueButton4);
        continueButton.setOnClickListener(this);
        continueButton.setVisibility(View.INVISIBLE);

        try{
            jsondata = new JSONObject(getArguments().getString("jsondata"));
            answer = jsondata.optString("answer");
            word1.setText(jsondata.optString("word1"));
            word2.setText(jsondata.optString("word2"));
            word3.setText(jsondata.optString("word3"));
            word4.setText(jsondata.optString("word4"));
            word5.setText(jsondata.optString("word5"));
            word6.setText(jsondata.optString("word6"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return viewVortludoj;
    }

    @Override
    public void onSensorChanged(SensorEvent e) {

        int sensortype = e.sensor.getType();
        if (sensortype == Sensor.TYPE_ACCELEROMETER) {
            double sum = Math.abs(e.values[0]) + Math.abs(e.values[1]) + Math.abs(e.values[2]);
            if (sum > 3 * SensorManager.GRAVITY_EARTH) {

                Toast.makeText(getActivity(), "Correct! You Passed!", Toast.LENGTH_SHORT).show();


            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    View.OnLongClickListener longListener = new View.OnLongClickListener()
    {
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
    };
    View.OnDragListener dragListener = new View.OnDragListener()
    {
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
                    String firstchar = draggedText.getText().toString().substring(0,1);
                    if(firstchar.equals("-")){
                        dropText.append(draggedText.getText());
                        checkSentence();
                    }
                    else{
                        dropText.append(" " + draggedText.getText());
                        checkSentence();
                    }


                    break;
            }
            return true;
        }
    };

    public void checkSentence(){

        System.out.println(answer);
        System.out.println(droptext.getText().toString());
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
