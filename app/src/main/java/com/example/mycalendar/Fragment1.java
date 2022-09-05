package com.example.mycalendar;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    String filename;
    Button btn_write;
    EditText editDiary;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        DatePicker dp = view.findViewById(R.id.datePicker);
        editDiary = view.findViewById(R.id.editDiary);
        btn_write = view.findViewById(R.id.btn_write);
        int Year = dp.getYear();
        int Month = dp.getMonth();
        int Day = dp.getDayOfMonth();

        Log.d("DATE", Year + Month + Day + "");

        filename = Year + "_" + (Month+1) + "_" + Day + ".txt";
        dp.init(Year, Month, Day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Log.d("CLiCK:",year + "_" + (monthOfYear + 1) + "_" + dayOfMonth);
                filename = year + "_" + (monthOfYear+1) + "_" + dayOfMonth + ".txt";
                String str = readDiary(filename);
                editDiary.setText(str);

            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outfS = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = editDiary.getText().toString();
                    outfS.write(str.getBytes());
                    outfS.close();
                    Toast.makeText(getContext(), filename + "파일을 저장 했습니다.",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        return view;
    }

    private String readDiary(String filename) {
        String diaryStr=null;

        try {
            FileInputStream infS = getContext().openFileInput(filename);
            byte[] txt = new byte[5];
            infS.read(txt);
            infS.close();

            diaryStr = new String(txt);
        } catch (IOException e) {
            editDiary.setText("일기 없음");
        }
        return diaryStr;

    }

    //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
}