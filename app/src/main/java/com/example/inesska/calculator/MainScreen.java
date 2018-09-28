package com.example.inesska.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity {

    //Переменные для каждой кнопки и поля с результатом
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
//    private Button decimal;
    private Button divide;
    private Button multiply;
    private Button minus;
    private Button plus;
    private Button equal;
//    private Button percent;
    private Button clear;
    private TextView res;
    private TextView textField;

    private final char PLUS = '+';
    private final char MINUS = '-';
    private final char MULTIPLY = 'x';
    private final char DIVIDE = '/';
    private final char EQUAL = 0;

    private double value1 = Double.NaN;
    private double value2;

    private char ACTION;


    //Первая функция, которая вызывается, когда создается активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //Вызываем функцию, которая сопоставит айди с переменными кнопок
        setButtonsWithIds();
        //Вызываем на клик на ноль
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Берет предыдущее значение если куда то уже кликали и добавляет к нему ноль (в строку с выводом чисел)
                textField.setText(textField.getText().toString() + "0");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textField.setText(textField.getText().toString() + "9");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //при клике на любую из операций вызывается функция compute
                compute();
                ACTION = PLUS;
                res.setText(String.valueOf(value1) + "+"); //значение 1 записываем в результат
                textField.setText(null); //очистка поля
            }
        });

        //то же самое для других операций

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = MINUS;
                res.setText(String.valueOf(value1) + "-");
                textField.setText(null);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = MULTIPLY;
                res.setText(String.valueOf(value1) + "X");
                textField.setText(null);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = DIVIDE;
                res.setText(String.valueOf(value1) + "/");
                textField.setText(null);
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                 ACTION = EQUAL;
                 res.setText(res.getText().toString() + String.valueOf(value2) + "=" + String.valueOf(value1));
                 textField.setText(null);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textField.getText().length() > 0) {
                    CharSequence name = textField.getText().toString();
                    textField.setText(name.subSequence(0, name.length() - 1));
                } else {
                    value1 = Double.NaN;
                    value2 = Double.NaN;
                    textField.setText(null);
                    res.setText(null);
                }
            }
        });
    }

//Сопоставить переменные с айдишниками кнопок, которые на кнопках и поолях с результатом
    private void setButtonsWithIds () {
        zero = (Button)findViewById(R.id.btn0);
        one = (Button)findViewById(R.id.btn1);
        two = (Button)findViewById(R.id.btn2);
        three = (Button)findViewById(R.id.btn3);
        four = (Button)findViewById(R.id.btn4);
        five = (Button)findViewById(R.id.btn5);
        six = (Button)findViewById(R.id.btn6);
        seven = (Button)findViewById(R.id.btn7);
        eight = (Button)findViewById(R.id.btn8);
        nine = (Button)findViewById(R.id.btn9);
//        decimal = (Button)findViewById(R.id.btnDecimal);
        divide = (Button)findViewById(R.id.btnDivide);
        multiply = (Button)findViewById(R.id.btnMultiply);
        minus = (Button)findViewById(R.id.btnMinus);
        plus = (Button)findViewById(R.id.btnPlus);
        equal = (Button)findViewById(R.id.btnEqual);
//        percent = (Button)findViewById(R.id.btnPercent);
        clear = (Button)findViewById(R.id.btnClear);
        res = (TextView)findViewById(R.id.tvResult);
        textField = (TextView)findViewById(R.id.tvControl);

    }

    private void compute() {
        if(!Double.isNaN(value1)){ //если value1 число
            //Double.parseDouble - делает строку double потому что value2 double
            value2 = Double.parseDouble(textField.getText().toString());

            switch (ACTION){
                case PLUS:
                    value1 += value2;
                    break;

                case MINUS:
                    value1 -= value2;
                    break;

                case MULTIPLY:
                    value1 = value1 * value2;
                    break;

                case DIVIDE:
                    value1 = value1 / value2;
                    break;

                case EQUAL:
                    break;
            }
        } else {
            //если value1 не число
            value1 = Double.parseDouble(textField.getText().toString());
        }
    }
}
