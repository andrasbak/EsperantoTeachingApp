package com.example.andras.esperantoapp.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andras.esperantoapp.R;
import com.example.andras.esperantoapp.skrald.LessonData;


public class ScreenTypeBildoDemando extends Fragment implements View.OnClickListener {


    ImageView imageView;
    TextView titleView;
    Button button1, button2, button3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonPart2 = inflater.inflate(R.layout.fragment_type_bildodemando, container, false);

        button1 = (Button)lessonPart2.findViewById(R.id.buttonPart2_1);
        button1.setOnClickListener(this);
        button2 = (Button)lessonPart2.findViewById(R.id.buttonPart2_2);
        button2.setOnClickListener(this);
        button3 = (Button)lessonPart2.findViewById(R.id.buttonPart2_3);
        button3.setOnClickListener(this);
        imageView = (ImageView)lessonPart2.findViewById(R.id.imageViewPart2);
        titleView = (TextView)lessonPart2.findViewById(R.id.titlePart2);

        return lessonPart2;
    }

    public void onClick(View v) {

        if (v.equals(button1)) {

            if (button1.getText().equals(LessonData.getInstance().getCorrect())) {
            } else {
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();
            }
        } else if (v.equals(button2)) {
            if (button2.getText().equals(LessonData.getInstance().getCorrect())) {
            } else {
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();
            }
        } else if (v.equals(button3)) {
            if (button3.getText().equals(LessonData.getInstance().getCorrect())) {
            } else {
                Toast.makeText(getActivity(), "Incrorrect! Try Again.", Toast.LENGTH_LONG).show();
            }
        }
    }
}

