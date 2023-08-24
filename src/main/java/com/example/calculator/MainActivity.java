package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView solution_tv, result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assignId(R.id.btnC).setOnClickListener(this);
        assignId(R.id.btnopen).setOnClickListener(this);
        assignId(R.id.btnclose).setOnClickListener(this);
        assignId(R.id.btndiv).setOnClickListener(this);
        assignId(R.id.btn_multiply).setOnClickListener(this);
        assignId(R.id.btnadd).setOnClickListener(this);
        assignId(R.id.btnminus).setOnClickListener(this);
        assignId(R.id.btnequal).setOnClickListener(this);
        assignId(R.id.btnallclear).setOnClickListener(this);
        assignId(R.id.btndot).setOnClickListener(this);
        assignId(R.id.btnzero).setOnClickListener(this);
        assignId(R.id.btn1).setOnClickListener(this);
        assignId(R.id.btn2).setOnClickListener(this);
        assignId(R.id.btn3).setOnClickListener(this);
        assignId(R.id.btn4).setOnClickListener(this);
        assignId(R.id.btn5).setOnClickListener(this);
        assignId(R.id.btn6).setOnClickListener(this);
        assignId(R.id.btn7).setOnClickListener(this);
        assignId(R.id.btn8).setOnClickListener(this);
        assignId(R.id.btn9).setOnClickListener(this);
    }

    MaterialButton assignId(int id) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if (buttonText.equals("AC")) {
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solution_tv.setText(result_tv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solution_tv.setText(dataToCalculate);

        String finalResult = getresult(dataToCalculate);
        if (!finalResult.equals("Err")) {
            result_tv.setText(finalResult);
        }
    }

    String getresult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}
