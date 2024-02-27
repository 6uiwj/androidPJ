package com.choongang.exam06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText[] inputBoxes;
    private CheckBox joinAgree;
    private Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //객체화 - 이 다음에 코드를 입력해야 함

        //요소 선택하기
        inputBoxes = new EditText[4];
        inputBoxes[0] = findViewById(R.id.userId); //객체화 된 요소를 가져옴
        inputBoxes[1] = findViewById(R.id.password);
        inputBoxes[2] = findViewById(R.id.confirmPassword);
        inputBoxes[3] = findViewById(R.id.userNm);

        joinAgree = findViewById(R.id.joinAgree);
        joinBtn = findViewById(R.id.joinBtn);

        //검증하기 (필수항목)
        //이벤트리스너 클릭
        joinBtn.setOnClickListener(v -> {
            try {
                String[] errorMessages = {
                        "아이디를 입력하세요",
                        "비밀번호를 입력하세요.",
                        "비밀번호를 확인하세요.",
                        "회원명을 입력하세요."
                };
                //순서대로 버튼쪽에서 값을 가져오자.
                for ( int i = 0; i < errorMessages.length; i++ ) {
                    String value = inputBoxes[i].getText().toString(); //입력된 값을 문자열로 반환(null이나 blank면 안됨)
                    if(value == null || value.trim().isEmpty()) {
                        throw new RuntimeException(errorMessages[i]); //toast메시지로 유입
                    }
                }

                if (!joinAgree.isChecked()) {
                    throw new RuntimeException("가입약관에 동의하세요.");
                }
                //추가 검증

            } catch(RuntimeException e) { //getApplicatonContext: 현재 액티비티의 this
               //toast: 안드로이드 팝업 메시지
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show(); //예외를 던지면 예외메시지를 가져오고 toast형태로 알려줌
                //== Toast.makeText(getApplicationContext())
            }
        });
    }
}