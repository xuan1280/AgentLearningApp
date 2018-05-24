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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.StubUserRepository;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRetrofitRepository;
import com.ood.clean.waterball.agentlearningapp.modles.viewmodels.SignUpModel;
import com.ood.clean.waterball.agentlearningapp.presenter.SignUpPresenter;
import com.ood.clean.waterball.agentlearningapp.views.base.SignUpView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ood.clean.waterball.agentlearningapp.R.array.cities;

public class SignUpActivity extends AppCompatActivity implements SignUpView, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener{
    private final static String TAG = "SignUpActivity";
    private final static String USER = "user";
    @BindView(R.id.signUpAccountEd) EditText accountEd;
    @BindView(R.id.signUpPasswordEd) EditText passwordEd;
    @BindView(R.id.signUpBtn) Button button;
    @BindView(R.id.nameEd) EditText nameEd;
    @BindView(R.id.ageEd) EditText ageEd;
    @BindView(R.id.genderRG) RadioGroup genderRG;
    @BindView(R.id.boyRadio) RadioButton boyRadio;
    @BindView(R.id.girlRadio) RadioButton girlRadio;
    @BindView(R.id.citySpn) Spinner citySpn;
    private int cityId;
    private String cityName;
    private boolean gender;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Log.d(TAG, "start to sign up");
        setUpSpinner();
        setUpRadioGroup();
        signUpPresenter = new SignUpPresenter(new UserRetrofitRepository(), this);
    }

    private void setUpSpinner() {
        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(this,cities , android.R.layout.simple_spinner_dropdown_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpn.setAdapter(adapterCity);
        citySpn.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String[] cities = getResources().getStringArray(R.array.cities);
        cityId = i;
        cityName = cities[i];
        Log.d(TAG, "user city : " +  cities[i]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void setUpRadioGroup() {
        genderRG.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        gender = ( i == R.id.boyRadio );
    }

    public void onSignUpBtnClick(View view) {
        String account = accountEd.getText().toString();
        String password = passwordEd.getText().toString();
        String name = nameEd.getText().toString();
        int age = ageEd.getText().toString().equals("") ? 0 : Integer.parseInt(ageEd.getText().toString());
        SignUpModel signUpModel = new SignUpModel(account, password, name, age, gender, cityId);
        signUpPresenter.signUp(signUpModel);
    }

    @Override
    public void onSignUpSuccessfully(User user) {
        Log.d(TAG, "onSignUpSuccessfully");
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra(USER, user);
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
