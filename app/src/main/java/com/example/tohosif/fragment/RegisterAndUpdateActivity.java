package com.example.tohosif.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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

import com.example.tohosif.db.DatabaseHelper;
import com.example.tohosif.db.UserTable;
import com.example.tohosif.model.UserFromDatabase;
import com.example.tohosif.recyclerview.R;

import java.util.Calendar;

public class RegisterAndUpdateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper myDB;
    UserTable userTable;
    String firstName, middleName, lastName, emailId, mobileNo, dob, gender, city;
    boolean editable = false;
    private Calendar calendar;
    private TextView tv_dob;
    private Button registerButton, updateButton;
    private ImageButton setDateButton;
    private int year, month, day;
    private EditText et_firstName, et_middleName, et_lastName, et_emailId, et_moblileNo;
    private RadioButton radioGenButton, radioFemaleButton, radioMaleButton;
    private Spinner spinner;
    private RadioGroup radioGrp;

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            showDate(year, month + 1, dayOfMonth);
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_update);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myDB = new DatabaseHelper(this);
        userTable = new UserTable(myDB);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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

        radioFemaleButton = (RadioButton) findViewById(R.id.rbFemale);
        radioMaleButton = (RadioButton) findViewById(R.id.rbMale);

        radioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int radioSetectedId = group.getCheckedRadioButtonId();
                radioGenButton = (RadioButton) findViewById(radioSetectedId);
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.cityArray,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);


        registerButton = (Button) findViewById(R.id.registerbtn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
                if (validate()) {
                    confirmation();
                }
            }
        });

        updateButton = (Button) findViewById(R.id.updatebtn);
        updateButton.setVisibility(View.GONE);

        try {
            final UserFromDatabase userFromDatabase = (UserFromDatabase) getIntent().getExtras().getSerializable("user");

            et_firstName.setText(userFromDatabase.getFirstName());
            et_firstName.setEnabled(false);

            et_middleName.setText(userFromDatabase.getMiddleName());
            et_middleName.setEnabled(false);

            et_lastName.setText(userFromDatabase.getLastName());
            et_lastName.setEnabled(false);

            et_emailId.setText(userFromDatabase.getEmailId());
            et_emailId.setEnabled(false);

            et_moblileNo.setText(userFromDatabase.getPhoneNo());
            et_moblileNo.setEnabled(false);

            tv_dob.setText(userFromDatabase.getDob());

            String[] values = userFromDatabase.getDob().split("/");
            day = Integer.parseInt(values[0]);
            month = (Integer.parseInt(values[1])) - 1;
            year = Integer.parseInt(values[2]);
            calendar.set(year, month, day);

            setDateButton.setEnabled(false);

            if (userFromDatabase.getGender().equals(new String("Male"))) {
                radioMaleButton.setChecked(true);
                radioFemaleButton.setEnabled(false);
            } else {
                radioFemaleButton.setChecked(true);
                radioMaleButton.setEnabled(false);
            }

            int spinnerposition = spinnerAdapter.getPosition(userFromDatabase.getCity());
            spinner.setSelection(spinnerposition);
            spinner.setEnabled(false);

            registerButton.setVisibility(View.GONE);
            editable = true;

            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getData();
                    if (validate()) {
                        UserFromDatabase user = new UserFromDatabase();
                        user.setId(userFromDatabase.getId());
                        user.setFirstName(firstName);
                        user.setMiddleName(middleName);
                        user.setLastName(lastName);
                        user.setGender(gender);
                        user.setDob(dob);
                        user.setCity(city);
                        user.setEmailId(emailId);
                        user.setPhoneNo(mobileNo);
                        boolean isUpdated = userTable.updateData(user);
                        if (isUpdated) {
                            Toast.makeText(RegisterAndUpdateActivity.this, "Data updated successfully.", Toast.LENGTH_LONG).show();
                            setResult(2);
                            finish();
                        } else {
                            Toast.makeText(RegisterAndUpdateActivity.this, "Failed to update data.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });

        } catch (Exception e) {
        }
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
        if (mobileNo.isEmpty() || mobileNo.length() != 10) {
            et_moblileNo.setError("Enter valid mobile number.");
            valid = false;
        }
        return valid;
    }

    private void confirmation() {
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("First Name: " + firstName + "\n" + "Middle Name: " + middleName + "\n" + "Last Name: " + lastName + "\n" + "Email id: " + emailId + "\n" + "Mobile No:" + mobileNo + "\n"
                + "DOB: " + dob + "\n" + "Gender: " + gender + "\n" + "City: " + city)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserFromDatabase user = new UserFromDatabase();
                        user.setFirstName(firstName);
                        user.setMiddleName(middleName);
                        user.setLastName(lastName);
                        user.setGender(gender);
                        user.setDob(dob);
                        user.setCity(city);
                        user.setEmailId(emailId);
                        user.setPhoneNo(mobileNo);
                        boolean isInserted = userTable.insertData(user);
                        showToast(isInserted);
                        dialog.dismiss();
                        setResult(1);
                        finish();
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

    private void getData() {
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!editable) {
            menu.findItem(R.id.edit).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        } else if (id == R.id.edit) {
            et_firstName.setEnabled(true);
            et_middleName.setEnabled(true);
            et_lastName.setEnabled(true);
            et_emailId.setEnabled(true);
            et_moblileNo.setEnabled(true);
            setDateButton.setEnabled(true);
            radioMaleButton.setEnabled(true);
            radioFemaleButton.setEnabled(true);
            spinner.setEnabled(true);
            updateButton.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Do you want to exit?")
                .setTitle("Caution!!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alert.show();
    }
}
