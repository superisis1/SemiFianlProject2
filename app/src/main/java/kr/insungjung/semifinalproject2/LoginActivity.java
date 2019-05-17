package kr.insungjung.semifinalproject2;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import kr.insungjung.semifinalproject2.databinding.ActivityLoginBinding;
import kr.insungjung.semifinalproject2.utils.ConnectServer;
import kr.insungjung.semifinalproject2.utils.ContextUtil;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        // 자동 로그인
        if (ContextUtil.getAutoLogin(mContext) == true) {
            act.autoLoginCheckBox.setChecked(true);
            act.loginIdEdt.setText(ContextUtil.getUserInputId(mContext));
            act.loginPwEdt.setText(ContextUtil.getUserInputPw(mContext));
        } else {
            act.autoLoginCheckBox.setChecked(false);
        }

        // 로그인 버튼 클릭
        act.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputId = act.loginIdEdt.getText().toString();
                String inputPw = act.loginPwEdt.getText().toString();

                ContextUtil.setUserInputId(mContext, inputId);
                ContextUtil.setUserInputPw(mContext, inputPw);

                ConnectServer.postRequestLogIn(mContext, inputId, inputPw, new ConnectServer.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    int code = json.getInt("code");
                                    Log.d("로그인 코드", Integer.toString(code));

                                    if(code == 200) {
                                        JSONObject data = json.getJSONObject("data");
                                        String token = data.getString("token");
                                        ContextUtil.setUserToken(mContext, token);

                                        if(act.autoLoginCheckBox.isChecked()) {
                                            ContextUtil.setAutoLogin(mContext, true);
                                        } else if (act.autoLoginCheckBox.isChecked() == false){
                                            ContextUtil.setAutoLogin(mContext, false);
                                        }

                                        Intent intent = new Intent(mContext, MainActivity.class);
                                        intent.putExtra("사용자토", token);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                                        alert.setTitle("로그인 실패");
                                        alert.setMessage(json.getString("message"));
                                        alert.setPositiveButton("확인", null);
                                        alert.show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        act = DataBindingUtil.setContentView(this, R.layout.activity_login);
    }
}





