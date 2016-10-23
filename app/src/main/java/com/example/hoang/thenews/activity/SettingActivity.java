package com.example.hoang.thenews.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoang.myapplication.R;

import java.util.Calendar;


public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    Spinner spinner;
    Button btnSaveOption;
    CheckBox cbArt, cbFnS, cbSport;
    ImageButton imageButton;
    TextView tvDate;
    String date;
    static final int DIALOG_ID = 0;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        fintvie();
        Date();
        spinner();
    }

    private void spinner() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.sort, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }

    private void Date() {
        final DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = year + "" + (1 + monthOfYear) + "" + dayOfMonth;
                tvDate.setText(String.valueOf(date));

            }
        };
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SettingActivity.this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.YEAR), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void fintvie() {
        btnSaveOption = (Button) findViewById(R.id.btnSaveOption);
        cbArt = (CheckBox) findViewById(R.id.cbArt);
        cbFnS = (CheckBox) findViewById(R.id.cbFnS);
        cbSport = (CheckBox) findViewById(R.id.cbSport);
        imageButton = (ImageButton) findViewById(R.id.ibDate);
        tvDate = (TextView) findViewById(R.id.tvDate);
        spinner = (Spinner) findViewById(R.id.spSort);
        btnSaveOption.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String rs = "";
        if (cbArt.isChecked()) {
            rs += " \"Arts\"";
        }
        if (cbFnS.isChecked()) {
            rs += " \"Fashion & Style\"";
        }
        if (cbSport.isChecked()) {
            rs += " \"Sports\"";
        }

        Toast.makeText(getApplicationContext(), "(" + rs + ")", Toast.LENGTH_SHORT).show();

    }
}
