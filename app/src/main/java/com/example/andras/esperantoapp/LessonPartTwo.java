package com.example.andras.esperantoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class LessonPartTwo extends Fragment implements View.OnClickListener {


    ImageView imageView;
    ImageButton imageButton;
    TextView textView;
    Button button;
    private String[] lessonInfo = {"L1P21", "L1P22", "L1P23", "L1P24", "L1P25"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l1p2 = inflater.inflate(R.layout.fragment_lesson_part2, container, false);




        return l1p2;

    }

    public void dataFromJson(){

        LessonData.getInstance().setInfo(lessonInfo[LessonData.getInstance().getCounter()]);

    }


    public void onClick(View v){

        if(v.equals(imageButton)){

            button.setVisibility(View.VISIBLE);

        }

        else if(v.equals(button)){

            if (LessonData.getInstance().getCounter() < 4) {

                LessonData.getInstance().setCounter(LessonData.getInstance().getCounter()+1);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartTwo());
                //ft.addToBackStack(null);
                ft.commit();
            }
            else{
                LessonData.getInstance().setCounter(0);
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, new LessonPartThree());
                //ft.addToBackStack(null);
                ft.commit();
            }

        }



    }

}
