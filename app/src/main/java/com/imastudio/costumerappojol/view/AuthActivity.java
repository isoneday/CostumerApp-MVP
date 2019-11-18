package com.imastudio.costumerappojol.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.imastudio.costumerappojol.R;
import com.imastudio.costumerappojol.auth.AuthContract;
import com.imastudio.costumerappojol.auth.AuthPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthActivity extends AppCompatActivity implements AuthContract.View {
    @BindView(R.id.txt_rider_app)
    TextView txtRiderApp;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.rootlayout)
    RelativeLayout rootlayout;

    AuthPresenter presenter;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        loading = new ProgressDialog(this);
        presenter = new AuthPresenter(this);
    }

    @OnClick({R.id.btnSignIn, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                login();
                break;
            case R.id.btnRegister:
                register();
                break;
        }
    }

    private void register() {
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_register, null, false);
        final ViewHolderRegister holderRegister = new ViewHolderRegister(v);

        AlertDialog dialogRegis = new AlertDialog.Builder(this)
                .setView(v)
                .setTitle(getString(R.string.titleregis))
                .setMessage(getString(R.string.messageregister))
                .setPositiveButton("Register", null)
                .setNegativeButton("cancel", null)
                .create();
        dialogRegis.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button buttonPositive = ((AlertDialog) dialogInterface).getButton(DialogInterface.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = holderRegister.edtEmail.getText().toString();
                        String password = holderRegister.edtPassword.getText().toString();
                        String nama = holderRegister.edtName.getText().toString();
                        String phone = holderRegister.edtPhone.getText().toString();

                        if (TextUtils.isEmpty(email)) {
                            holderRegister.edtEmail.setError(getString(R.string.requireemail));
                        } else if (TextUtils.isEmpty(password)) {
                            holderRegister.edtPassword.setError(getString(R.string.requirepassword));
                        } else if (TextUtils.isEmpty(nama)) {
                            holderRegister.edtName.setError(getString(R.string.requirename));
                        } else if (TextUtils.isEmpty(phone)) {
                            holderRegister.edtPhone.setError(getString(R.string.requirephone));
                        } else {
                            presenter.prosesRegister(email, password, nama, phone,dialogInterface);
                        }
                    }
                });
            }
        });

        dialogRegis.show();


    }

    private void login() {

    }

    @Override
    public void showLoading(String msg) {
        loading.setTitle("Proses" + msg + "user");
        loading.setMessage("loading . .. . ");
        loading.show();

    }

    @Override
    public void hideLoading() {
        loading.dismiss();

    }

    @Override
    public void showError(String localizedMessage) {
        Toast.makeText(this, localizedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void pindahHalaman() {

    }

    @Override
    public void onAttachView() {
        presenter.onAttach(AuthActivity.this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onAttachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDetachView();
    }

    static
    class ViewHolderRegister {
        @BindView(R.id.edtEmail)
        MaterialEditText edtEmail;
        @BindView(R.id.edtPassword)
        MaterialEditText edtPassword;
        @BindView(R.id.edtName)
        MaterialEditText edtName;
        @BindView(R.id.edtPhone)
        MaterialEditText edtPhone;

        ViewHolderRegister(View view) {
            ButterKnife.bind(this, view);
        }
    }
}