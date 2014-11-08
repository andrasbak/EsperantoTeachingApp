package com.example.andras.esperantoapp;

/**
 * Created by Andras on 27-10-2014.
 */
public class LessonData {

    private String infoFromJson, lessonNumber, lessonUrl, correctAnswer;


    private int counter = 0;
    private int dataCounter;

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
        counter = count;
    }
    public int getCounter(){
        return counter;
    }

    public void setDataCounter(int dataCount){
        dataCounter = dataCount;
    }
    public int getDataCounter(){
        return dataCounter;
    }

    public void setLessonNumber(String lesson){
        lessonNumber = lesson;
    }
    public String getLessonNumber(){
        return lessonNumber;
    }

    public void setLessonUrl(String url){
        lessonUrl = url;
    }
    public String getLessonUrl(){
        return lessonUrl;
    }

    public void setCorrect(String correct){
        correctAnswer = correct;
    }
    public String getCorrect(){
        return correctAnswer;
    }
}
