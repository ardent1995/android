package com.example.tohosif.layout;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tohosif.recyclerview.DatabaseHelper;
import com.example.tohosif.recyclerview.MainActivity;
import com.example.tohosif.recyclerview.R;

import java.util.Calendar;

public class registerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper myDB;
    String firstName, middleName, lastName, emailId, mobileNo, dob, gender, city;
    private Calendar calendar;
    private TextView tv_dob;
    private Button registerButton;
    private ImageButton setDateButton;
    private int year, month, day;
    private EditText et_firstName, et_middleName, et_lastName, et_emailId, et_moblileNo;
    private RadioButton radioGenButton;
    private Spinner spinner;
    private RadioGroup radioGrp;
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month + 1, dayOfMonth);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDB = new DatabaseHelper(this);

        tv_dob = (TextView) findViewById(R.id.tv_dob);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        setDateButton = (ImageButton) findViewById(R.id.setDateButton);
        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(999);
            }
        });


        et_firstName = (EditText) findViewById(R.id.fn);

        et_middleName = (EditText) findViewById(R.id.mn);

        et_lastName = (EditText) findViewById(R.id.ln);

        et_emailId = (EditText) findViewById(R.id.mail);

        et_moblileNo = (EditText) findViewById(R.id.mob);

        radioGrp = (RadioGroup) findViewById(R.id.radioGrp);

        radioGenButton = (RadioButton) findViewById(R.id.rbMale);

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int radioSetectedId = group.getCheckedRadioButtonId();
                radioGenButton = (RadioButton) findViewById(radioSetectedId);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.cityArray,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        registerButton = (Button) findViewById(R.id.btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialize();
                if (validate()) {
                    confirmation();
                }
            }
        });

    }

    private boolean validate() {
        boolean valid = true;
        if (firstName.isEmpty()) {
            et_firstName.setError("First Name can not be empty.");
            valid = false;
        }
        if (lastName.isEmpty()) {
            et_lastName.setError("Last Name can not be empty.");
            valid = false;
        }
        if (emailId.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailId).matches()) {
            et_emailId.setError("Enter valid email address.");
            valid = false;
        }
        if (mobileNo.isEmpty()) {
            et_moblileNo.setError("Mobile Number can not be empty.");
            valid = false;
        }
        return valid;
    }

    private void confirmation() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("First Name: " + firstName + "\n" + "Middle Name: " + middleName + "\n" + "Last Name: " + lastName + "\n" + "Email id: " + emailId + "\n" + "Mobile No:" + "+91" + mobileNo + "\n"
                + "DOB: " + dob + "\n" + "Gender: " + gender + "\n" + "City: " + city)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isInserted = myDB.insertData(firstName, middleName, lastName, gender, dob, city, emailId, "+91" + mobileNo);
                        showToast(isInserted);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setTitle("Confirmation")
                .create();
        myAlert.show();

    }

    private void showToast(boolean isInserted) {
        if (isInserted) {
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data insertion failed", Toast.LENGTH_LONG).show();
        }
    }

    private void initialize() {
        firstName = et_firstName.getText().toString().trim();
        middleName = et_middleName.getText().toString().trim();
        lastName = et_lastName.getText().toString().trim();
        emailId = et_emailId.getText().toString().trim();
        mobileNo = et_moblileNo.getText().toString().trim();
        dob = tv_dob.getText().toString();
        gender = radioGenButton.getText().toString();
        city = spinner.getSelectedItem().toString();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private void showDate(int year, int month, int day) {
        tv_dob.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
//        Log.d("Date",tv_dob.getText().toString());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        Toast.makeText(this, "You selected " + myText.getText(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment currentFragment = (new MainActivity()).getFragmentManager().findFragmentById(R.id.mainContent);
        FragmentTransaction fragTransaction = (new MainActivity()).getFragmentManager().beginTransaction();
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commit();
    }
}
