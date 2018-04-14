package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.views.base.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ood.clean.waterball.agentlearningapp.R.array.cities;

public class SignUpActivity extends AppCompatActivity implements SignUpView, AdapterView.OnItemSelectedListener{
    private final static String TAG = "SignUpActivity";
    @BindView(R.id.signUpAccountEd) EditText accountEd;
    @BindView(R.id.signUpPasswordEd) EditText passwordEd;
    @BindView(R.id.signUpBtn) Button button;
    @BindView(R.id.nameEd) EditText nameEd;
    @BindView(R.id.ageEd) EditText ageEd;
    @BindView(R.id.genderRG) RadioGroup genderRG;
    @BindView(R.id.citySpn) Spinner citySpn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Log.d(TAG, "start to sign up");
        //setUpSpinner();
    }

    private void setUpSpinner() {
        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(this,cities , android.R.layout.simple_spinner_dropdown_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpn.setAdapter(adapterCity);
        citySpn.setOnItemClickListener(this::onItemSelected);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void onSignUpBtnClick(View view) {

    }

    @Override
    public void onSignUpSuccessfully(User user) {
        Log.d(TAG, "onSignUpSuccessfully");
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("use", user);
        startActivity(intent);
    }

    @Override
    public void onAccountDuplicated() {
        Log.d(TAG, "onAccountDuplicated");
        new AlertDialog.Builder(this)
                .setMessage(R.string.accountDuplicatedWarning)
                .setPositiveButton(R.string.okay, null)
                .show();
    }

    @Override
    public void onParameterInvalid() {
        Log.d(TAG, "onParameterInvalid");
        new AlertDialog.Builder(this)
                .setMessage(R.string.pleaseEnterCorrectParameter)
                .setPositiveButton(R.string.okay, null)
                .show();
    }


}
