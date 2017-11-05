package com.example.nikhil.krigerapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nikhil.krigerapplication.R;
import com.example.nikhil.krigerapplication.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static String TAG , gender;
    private AutoCompleteTextView city;
   private RadioButton male, female;
    private RadioGroup radioGroup;
    EditText email , ph ;
    EditText password ;

    Button btnSubmit ;
DatabaseReference mDatabase  ;
    FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       initviews();
        TAG = getString(R.string.tag);
        getGender();

    }
   void initviews()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference() ;
        mAuth = FirebaseAuth.getInstance() ;
        ph = (EditText) findViewById(R.id.ph);

//        radio buttons
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        male = (RadioButton) findViewById(R.id.male_radio_btn);
        female = (RadioButton) findViewById(R.id.female_radio_btn);

//        city
        city = (AutoCompleteTextView) findViewById(R.id.ac_city);
        city.setThreshold(1);
        String[] cities = getResources().getStringArray(R.array.india_cities);

        ArrayAdapter<String> city_adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        city.setAdapter(city_adapter);

        email = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);
        btnSubmit = (Button)findViewById(R.id.buttonSubmit);
    }

    public void btnRegister(View v)
    {
        mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(SignUpActivity.this , "Authentication Successfull" , Toast.LENGTH_SHORT).show();
                            dbRegister(task.getResult().getUser());
                            move();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          
                        }


                    }
                });
//        startActivity(new Intent(this, HomeActivity.class));
    }
    public void move()
    {
        startActivity(new Intent(this, HomeActivity.class));
    }


    public String getGender(){

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.male_radio_btn:
                        gender = "male";
                        break;
                    case R.id.female_radio_btn:
                        gender = "female";
                        break;
                }

            }
        });
        return gender;
    }
    void dbRegister(FirebaseUser fuser)
    {
        User user = new User(email.getText().toString().trim(),
                password.getText().toString().trim(),
                ph.getText().toString().trim(),
                gender,
                city.getText().toString().trim()
        );
        Log.d(TAG, ""+user);
        mDatabase.child("User")
                .child(fuser.getUid())
                .setValue(user);

    }

}


