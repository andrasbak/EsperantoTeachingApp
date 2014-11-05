package com.example.andras.esperantoapp;

/**
 * Created by Andras on 27-10-2014.
 */
public class LessonData {

    private String infoFromJson, lessonNumber;


    private int counter = 0;
    private static LessonData lessonData;

    private LessonData(){}

    public static LessonData getInstance(){

        if(lessonData==null){

            lessonData = new LessonData();

        }

        return lessonData;

    }

    public void setInfo(String info){
        infoFromJson = info;
    }
    public String getInfo(){
        return infoFromJson;
    }
    public void setCounter(int count){
        counter = counter + count;
    }
    public int getCounter(){
        return counter;
    }
    public void setLessonNumber(String lesson){
        lessonNumber = lesson;
    }
    public String getLessonNumber(){
        return lessonNumber;
    }







}
