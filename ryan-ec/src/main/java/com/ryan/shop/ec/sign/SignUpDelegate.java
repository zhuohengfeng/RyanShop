package com.ryan.shop.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.ryan.shop.core.delegate.RyanDelegate;
import com.ryan.shop.core.net.RestClient;
import com.ryan.shop.core.net.callback.ISuccess;
import com.ryan.shop.core.util.log.RyanLogger;
import com.ryan.shop.ec.R;

/**
 * Created by zhuohf1 on 2017/8/17.
 */
// 注册的界面
public class SignUpDelegate extends RyanDelegate {

    TextInputEditText mName = null;
    TextInputEditText mEmail = null;
    TextInputEditText mPhone = null;
    TextInputEditText mPassword = null;
    TextInputEditText mRePassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    //@OnClick(R2.id.btn_sign_up)
    public void onClickSignUp(View v) {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.56.1:8080/RestDataServer/api/user_profile.php")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            RyanLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .post();
        }
    }

    //@OnClick(R2.id.tv_link_sign_in)
    public void onClickLink(View v) {
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        mName = rootView.findViewById(R.id.edit_sign_up_name);
        mEmail = rootView.findViewById(R.id.edit_sign_up_email);
        mPhone = rootView.findViewById(R.id.edit_sign_up_phone);
        mPassword = rootView.findViewById(R.id.edit_sign_up_password);
        mRePassword = rootView.findViewById(R.id.edit_sign_up_re_password);
    }
}
