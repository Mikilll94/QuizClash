package com.example.samsung.quizclash;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsung.quizclash.api.Question;


public class QuestionActivity extends Activity {
    private TextView questionTextView;
    private Button btnSend;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        bindElements();
        createDummyQuestion();
        //TODO:: method getting question from server
        // getQuestion();
        populateActivity();
        addListenerOnSendButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_question, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void bindElements() {
        questionTextView = (TextView)findViewById(R.id.question_textView1);
        btnSend = (Button) findViewById(R.id.question_btn_send);
        radioGroup = (RadioGroup)findViewById(R.id.question_radioGroup);
        radioButton1 = (RadioButton)findViewById(R.id.question_radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.question_radioButton2);
        radioButton3 = (RadioButton)findViewById(R.id.question_radioButton3);
        radioButton4 = (RadioButton)findViewById(R.id.question_radioButton4);
    }


    private void createDummyQuestion() {
        question = new Question(1, "Which of the following is NOT a member of a scrum team?");
        question.addAnswer(0,"Scrum Master");
        question.addAnswer(1,"Development Team");
        question.addAnswer(2,"Team Leader");
        question.addAnswer(3,"Product Owner");
    }


    private void populateActivity() {
        questionTextView.setText(question.getText());
        radioButton1.setText(question.getAnswers().get(0).getText());
        radioButton2.setText(question.getAnswers().get(1).getText());
        radioButton3.setText(question.getAnswers().get(2).getText());
        radioButton4.setText(question.getAnswers().get(3).getText());
    }


    private void addListenerOnSendButton() {
        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendButtonHandle();
            }
        });
    }


    private void sendButtonHandle() {
        int rbId = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));

        final String toastMsg = rbId == 2 ? "Correct answer!" : "Wrong answer!";
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(QuestionActivity.this,
                        toastMsg,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
