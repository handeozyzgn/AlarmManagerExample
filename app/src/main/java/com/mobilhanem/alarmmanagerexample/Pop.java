package com.mobilhanem.alarmmanagerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * Created by Bengu on 15.3.2017.
 */

public class Pop extends Activity {
    boolean isClicked = true;
    PopupWindow popUpWindow;
    LayoutParams layoutParams;
    LinearLayout mainLayout;
    Button btnClickHere;
    LinearLayout containerLayout;
    TextView tvMsg;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* containerLayout = new LinearLayout(this);
        mainLayout = new LinearLayout(this);
        popUpWindow = new PopupWindow(this);


        btnClickHere = new Button(this);
        btnClickHere.setText("Click Here For Pop Up Window !!!");
        btnClickHere.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (isClicked) {
                    isClicked = false;
                    popUpWindow.showAtLocation(mainLayout, Gravity.BOTTOM, 10, 10);
                    popUpWindow.update(50, 50, 320, 90);
                } else {
                    isClicked = true;
                    popUpWindow.dismiss();
                }
            }

        });

        tvMsg = new TextView(this);
        tvMsg.setText("Hi this is pop up window...");

        layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        containerLayout.setOrientation(LinearLayout.VERTICAL);
        containerLayout.addView(tvMsg, layoutParams);
        popUpWindow.setContentView(containerLayout);
        mainLayout.addView(btnClickHere, layoutParams);
        setContentView(mainLayout); */

        AlertDialog.Builder builder = new AlertDialog.Builder(Pop.this);
        builder.setTitle("Mobilhanem");
        builder.setMessage("Uyarı mesajı yaz!!!");
        builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {

                //İptal butonuna basılınca yapılacaklar.Sadece kapanması isteniyorsa boş bırakılacak

            }
        });


        builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Tamam butonuna basılınca yapılacaklar

            }
        });


        builder.show();

    }
  /*  @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*8),(int)(height*6));


    }*/
}
