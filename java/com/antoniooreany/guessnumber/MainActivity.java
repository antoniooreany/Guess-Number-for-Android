package com.antoniooreany.guessnumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static int MAX_NUMBER = 10;
    private Button button_enter;
    private Button button_reset;
    private EditText editText_enter_number;
    private TextView textView_result;
    private TextView textView_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_enter = (Button) findViewById(R.id.button_enter);
        button_reset = (Button) findViewById(R.id.button_reset);
        editText_enter_number = (EditText) findViewById(R.id.editText_enter_number);
        textView_result = (TextView) findViewById(R.id.textView_result);
        textView_hint = (TextView) findViewById(R.id.TextView_hint);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Random random = new Random();
        final int guessNumber = getGuessNumber(random);
        initTextViewsHintAndResult(guessNumber);

        button_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_enter_number_string = editText_enter_number.getText().toString();
                final Integer edit_enter_number_integer = Integer.valueOf(edit_enter_number_string);
                setTextInTextViewResult(edit_enter_number_integer, guessNumber);
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGuessNumber(random);
                initTextViewsHintAndResult(guessNumber);
            }
        });
    }

    private void setTextInTextViewResult(Integer edit_enter_number_integer, int guessNumber) {
        if (edit_enter_number_integer < guessNumber) {
            textView_result.setText(R.string.result_less_than_the_number);
        } else if (edit_enter_number_integer > guessNumber) {
            textView_result.setText(R.string.result_more_than_the_number);
        } else {
            textView_result.setText(R.string.You_are_right);
        }
    }

    private void initTextViewsHintAndResult(int guessNumber) {
        textView_hint.setText(String.valueOf(guessNumber));
        textView_result.setText("");
    }

    private int getGuessNumber(Random random) {
        final int guessNumber = random.nextInt(MAX_NUMBER);
        return guessNumber;
    }
}
