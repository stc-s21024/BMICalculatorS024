package jp.suntech.s21024.bmicalculators024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelloListener listener = new HelloListener();

        Button btCalc = findViewById(R.id.btCalc);
        btCalc.setOnClickListener(listener);

        Button btClear = findViewById(R.id.btClear);
        btClear.setOnClickListener(listener);

    }
    private class HelloListener implements View.OnClickListener{

        boolean flag = false;
        @Override
        public void onClick(View view) {
//            EditText input = findViewById(R.id.etName);
//            EditText input2 = findViewById(R.id.etAddress);
//            TextView output = findViewById(R.id.tvOutput);
            EditText age_et = findViewById(R.id.etAge);
            EditText height_et = findViewById(R.id.etHeight);
            EditText weight_et = findViewById(R.id.etWeight);

            TextView R1 = findViewById(R.id.tvR1);
            TextView R2 = findViewById(R.id.tvR2);
            TextView Result1 = findViewById(R.id.tvResult1);
            TextView Result2 = findViewById(R.id.tvResult2);
            TextView W2 = findViewById(R.id.tvW2);

            int id = view.getId();
            switch (id){
                case R.id.btCalc:
//                    String inputStr = input.getText().toString();
//                    String inputStr2 = input2.getText().toString();
//                    output.setText(inputStr2+ "にお住まいの" + inputStr + "さん、こんにちは！");
                    if(age_et.getText().toString().isEmpty() || height_et.getText().toString().isEmpty() || weight_et.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,"空白の欄があります",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int age = Integer.parseInt(age_et.getText().toString());
                    float height = Float.parseFloat(height_et.getText().toString());
                    float weight = Float.parseFloat(weight_et.getText().toString());

                    float h100 = height / 100;
                    float bmi;

                    boolean loopflag = false;

                    String DoO;
                    
//                    if(age<16) {
//                        ConfirmDialogFragment dialogFragment = new ConfirmDialogFragment();
//                        dialogFragment.show(getSupportFragmentManager(),"ConfirmDialogFragment");
//                    }

                    //6歳未満
                    if(age<6){
                        bmi = weight / (h100 * h100);
                        Result2.setText(R.string.u16);
                        Toast.makeText(MainActivity.this,R.string.u16_toast,Toast.LENGTH_SHORT).show();

                        if(age==1){
                            if(bmi<15.5){
                                DoO = "やせぎみ";
                                Result1.setTextColor(Color.CYAN);
                            }else if(bmi>=17.5){
                                DoO = "ふとりぎみ";
                                Result1.setTextColor(Color.RED);
                            }else{
                                DoO = "正常";
                                Result1.setTextColor(Color.GREEN);
                            }
                        }else if(age<3){
                            if(bmi<15){
                                DoO = "やせぎみ";
                                Result1.setTextColor(Color.CYAN);
                            }else if(bmi>=17){
                                DoO = "ふとりぎみ";
                                Result1.setTextColor(Color.RED);
                            }else{
                                DoO = "正常";
                                Result1.setTextColor(Color.GREEN);
                            }

                        }else{
                            if(bmi<14.5){
                                DoO = "やせぎみ";
                                Result1.setTextColor(Color.CYAN);
                            }else if(bmi>=16.5){
                                DoO = "ふとりぎみ";
                                Result1.setTextColor(Color.RED);
                            }else{
                                DoO = "正常";
                                Result1.setTextColor(Color.GREEN);
                            }

                        }

                    //6歳~16歳未満
                    }else if(age<16){
                        bmi = weight/(h100*h100*h100)*10;
                        Result2.setText(R.string.u16);
                        Toast.makeText(MainActivity.this,R.string.u16_toast,Toast.LENGTH_SHORT).show();

                        if (bmi < 100) {
                            DoO = "やせすぎ";
                            Result1.setTextColor(Color.BLUE);
                        } else if (bmi < 115) {
                            DoO = "やせてる";
                            Result1.setTextColor(Color.CYAN);
                        } else if (bmi < 145) {
                            DoO = "ふつう";
                            Result1.setTextColor(Color.GREEN);
                        } else if (bmi < 160) {
                            DoO = "ふとっている";
                            Result1.setTextColor(Color.rgb(255, 180, 0));
                        } else {
                            DoO = "ふとりすぎ";
                            Result1.setTextColor(Color.RED);
                        }

                    //16歳以上
                    }else{
                        bmi = weight / (h100 * h100);
                        float AppropriateWeight = (h100*h100) * 22;
                        Result2.setText(String.format("%.1f",AppropriateWeight));
                        if (bmi < 18.5) {
                            DoO = "低体重";
                            Result1.setTextColor(Color.CYAN);
                        } else if (bmi < 25) {
                            DoO = "普通体重";
                            Result1.setTextColor(Color.GREEN);
                        } else if (bmi < 30) {
                            DoO = "肥満(1度)";
                            Result1.setTextColor(Color.rgb(255, 255, 0));
                        } else if (bmi < 35) {
                            DoO = "肥満(2度)";
                            Result1.setTextColor(Color.rgb(255, 180, 0));
                        } else if (bmi < 40) {
                            DoO = "肥満(3度)";
                            Result1.setTextColor(Color.rgb(255, 100, 0));
                        } else {
                            DoO = "肥満(4度)";
                            Result1.setTextColor(Color.RED);
                        }
                    }

                    Result1.setText(DoO);
                    R1.setAlpha(1);
                    R2.setAlpha(1);
                    Result1.setAlpha(1);
                    Result2.setAlpha(1);
                    W2.setAlpha(1);
                    break;
                case R.id.btClear:
//                    input.setText("");
                    age_et.setText("");
                    height_et.setText("");
                    weight_et.setText("");

                    R1.setAlpha(0);
                    R2.setAlpha(0);
                    Result1.setAlpha(0);
                    Result2.setAlpha(0);
                    W2.setAlpha(0);
                    loopflag = false;
                    break;
            }
        }
    }
}