package sg.com.republicworkz.safety.restaurantreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName,etPhone,etSize;
    CheckBox checkBox;
    DatePicker dp;
    TimePicker tp;
    Button btConfirm, btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etSize = findViewById(R.id.etSize);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        checkBox = findViewById(R.id.checkBoxSmoking);
        btConfirm = findViewById(R.id.buttonConfirm);
        btReset = findViewById(R.id.buttonReset);

        dp.updateDate(2020, 5, 1);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String size = etSize.getText().toString();
                String telephone = etPhone.getText().toString();
                if(name.isEmpty() || size.isEmpty() || telephone.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the required information",Toast.LENGTH_SHORT).show();
                    return;
                }
                Calendar now = Calendar.getInstance();
                Calendar res = Calendar.getInstance();
                res.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(),
                        tp.getCurrentHour(), tp.getCurrentMinute());
                if(now.after(res)){
                    Toast.makeText(MainActivity.this, "Please select a date and time after today.",Toast.LENGTH_LONG).show();
                    return;
                }

                String isSmoke="";
                if(checkBox.isChecked()){
                    isSmoke = "moking";
                }else{
                    isSmoke = "non-smoking";
                }

                Toast.makeText(MainActivity.this,
                        "Hi, "+name+", you have booked a "
                                +size+" person(s)"
                                +isSmoke+" table on"
                        +dp.getYear()+"/"+(dp.getMonth()+1)+"/"+dp.getDayOfMonth()+" at "
                        +tp.getCurrentHour()+":"+tp.getCurrentMinute()+". Your phone number is "
                        +telephone+".",
                        Toast.LENGTH_LONG).show();

            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(null);
                etPhone.setText(null);
                etSize.setText(null);
                checkBox.setChecked(false);
                dp.updateDate(2020,5,1);
                tp.setCurrentMinute(30);
                tp.setCurrentHour(20);
            }
        });


        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay<8) {
                    Toast.makeText(MainActivity.this, "We open at 8AM", Toast.LENGTH_LONG).show();
                }else if(hourOfDay >= 21) {
                    Toast.makeText(MainActivity.this, "We close at 9P,", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
