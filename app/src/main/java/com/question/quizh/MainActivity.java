package com.question.quizh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private ImageView questionImage;
    private EditText editText;
    private AdView mAdView;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
           
            {"aichi", "愛知県"},
            {"akita", "秋田県"},
            {"aomori", "青森県"},
            {"chiba", "千葉県"},
            {"ehime", "愛媛県"},
            {"fukui", "福井県"},
            {"fukuoka", "福岡県"},
            {"fukushima", "福島県"},
            {"gifu", "岐阜県"},
            {"gunma", "群馬県"},
            {"hiroshima", "広島県"},
            {"hokkaido", "北海道"},
            {"hyougo", "兵庫県"},
            {"ibaraki", "茨城県"},
            {"ishikawa", "石川県"},
            {"iwate", "岩手県"},
            {"kagawa", "香川県"},
            {"kagoshima", "鹿児島県"},
            {"kanagawa", "神奈川県"},
            {"kouchi", "高知県"},
            {"kumamoto", "熊本県"},
            {"kyoto", "京都府"},
            {"mie", "三重県"},
            {"miyagi", "宮城県"},
            {"miyazaki", "宮崎県"},
            {"nagano", "長野県"},
            {"nagasaki", "長崎県"},
            {"nara", "奈良県"},
            {"niigata", "新潟県"},
            {"oita", "大分県"},
            {"okayama", "岡山県"},
            {"okinawa", "沖縄県"},
            {"osaka", "大阪府"},
            {"saga", "佐賀県"},
            {"saitama", "埼玉県"},
            {"shiga", "滋賀県"},
            {"shimane", "島根県"},
            {"shizuoka", "静岡県"},
            {"tochigi", "栃木県"},
            {"tokushima", "徳島県"},
            {"tokyo", "東京都"},
            {"tottori", "鳥取県"},
            {"toyama", "富山県"},
            {"wakayama", "和歌山県"},
            {"yamagata", "山形県"},
            {"yamaguchi", "山口県"},
            {"yamanashi", "山梨県"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countLabel = findViewById(R.id.countLabel);
        questionImage = findViewById(R.id.questionImage);
        editText = findViewById(R.id.editText);
       
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        checkAnswer();
                    }
                }
                return false;
            }
        });
       
        for (int i = 0; i < quizData.length; i++) {
            
            ArrayList<String> tmpArray = new ArrayList<>();

           
            tmpArray.add(quizData[i][0]); 
            tmpArray.add(quizData[i][1]); 

          
            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }
    public void showNextQuiz() {
       
        countLabel.setText("Q" + quizCount);

       
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

       
        ArrayList<String> quiz = quizArray.get(randomNum);

       
        questionImage.setImageResource(
                getResources().getIdentifier(quiz.get(0), "drawable", getPackageName())
        );

    
        rightAnswer = quiz.get(1);

       
        quizArray.remove(randomNum);
    }
    public void checkAnswer() {

       
        String answer = editText.getText().toString();

        String alertTitle;
        if (answer.equals(rightAnswer)) {
            
            alertTitle = "正解！";
            rightAnswerCount++;

        } else {
            
            alertTitle = "不正解...";
        }

        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え：" + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                editText.setText("");

                if (quizCount == QUIZ_COUNT) {
              

                    showResult();

                } else {
                    
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
    public void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("結果");
        builder.setMessage(rightAnswerCount + " / " + QUIZ_COUNT);
        builder.setPositiveButton("もう一度", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
            }
        });
        builder.setNegativeButton("終了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.show();
    }

}
