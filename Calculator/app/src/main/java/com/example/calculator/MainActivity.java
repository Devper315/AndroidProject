package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText num1, num2;
    private Button btnCalculate;
    private Spinner operatorSpinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numText1 = num1.getText().toString();
                String numText2 = num2.getText().toString();
                String operator = operatorSpinner.getSelectedItem().toString();
                try {
                    Double n1 = Double.parseDouble(numText1);
                    Double n2 = Double.parseDouble(numText2);
                    String resultText = calculate(n1, n2, operator);
                    result.setText(resultText);
                    Toast.makeText(getApplicationContext(), resultText, Toast.LENGTH_LONG).show();
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });
        operatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String number1 = num1.getText().toString();
                String number2 = num2.getText().toString();
                double n1, n2;
                try {
                    n1 = Double.parseDouble(number1);
                    n2 = Double.parseDouble(number2);
                    String operator = operatorSpinner.getSelectedItem().toString();
                    String res = calculate(n1, n2, operator);
                    result.setText(res);
                }
                catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Nhập 2 số", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initView(){
        result = findViewById(R.id.result);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operatorSpinner = findViewById(R.id.operator);
        btnCalculate = findViewById(R.id.btnCalculate);

    }

    private String calculate(double n1, double n2, String operator){
        String res = "";
        switch (operator){
            case "+": res = "Sum = " + (n1 + n2);
            break;
            case "-": res = "Sub = " + (n1 - n2);
            break;
            case "x": res = "Multi = " + (n1 * n2);
            break;
            case ":":
                if (n2 == 0) res = "Cannot be divided by 0";
                else res = "Div = " + (n1 / n2);
                break;
        }
        return res;
    }
}
