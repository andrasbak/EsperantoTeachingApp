package com.example.andras.esperantoapp.ui;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.MediaPlayer;

import com.example.andras.esperantoapp.R;
import com.example.andras.esperantoapp.skrald.LessonData;

import org.json.JSONException;
import org.json.JSONObject;

public class ScreenTypeFrazo extends Fragment implements View.OnClickListener {

    ImageView imageView;
    ImageButton imageButton;
    TextView textView, titleView;
    MediaPlayer mp = new MediaPlayer();

  private JSONObject jsondata;

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lessonpart1 = inflater.inflate(R.layout.fragment_lesson_part1, container, false);
        imageView = (ImageView)lessonpart1.findViewById(R.id.imageViewPart1);
        imageButton = (ImageButton)lessonpart1.findViewById(R.id.imageButtonPart1);
        imageButton.setOnClickListener(this);
        textView = (TextView)lessonpart1.findViewById(R.id.phrasePart1);
        titleView = (TextView)lessonpart1.findViewById(R.id.titlePart1);

    try {
      jsondata = new JSONObject(getArguments().getString("jsondata"));
      textView.setText(jsondata.optString("phrase"));
    } catch (JSONException e) {
      e.printStackTrace();
    }


        return lessonpart1;
    }
    public void onClick(View v){

        if(v.equals(imageButton)){

            mp.start();
            while(mp.isPlaying()){}
            dialog();

        }
    }

    public void dialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle("Correct!");
        dialog.setPositiveButton("Continue", new AlertDialog.OnClickListener() {

            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        dialog.show();
    }


/*
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        ImageView bmImage;
        InputStream is = null;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap mIcon11 = null;
            try {
                String lokalHentetFil = FilCache.hentFil(LessonData.getInstance().getPictureUrl(), true);
                is = new FileInputStream(lokalHentetFil);
                mIcon11 = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    } */
}
