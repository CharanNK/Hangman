package com.charan.hangman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class GameplayActivity extends AppCompatActivity {
    private Random randomGenerator = new Random();
    private final String[] easylist = {"cat", "sun", "cup", "ghost", "pie", "cow", "banana",
            "bug", "book", "jar",
            "snake", "light", "tree",
            "lips", "apple", "slide",
            "socks", "smile", "swing",
            "coat", "shoe", "water",
            "heart", "hat", "ocean",
            "kite", "dog", "mouth",
            "milk", "duck", "eyes", "bird", "boy",
            "apple", "person", "girl",
            "mouse", "ball", "house",
            "star", "nose", "bed",
            "whale", "jacket", "shirt",
            "beach", "egg",
            "face", "cookie", "cheese",
            "dance", "skip", "jumping", "jack",
            "shark", "chicken", "alligator",
            "chair", "robot", "head",
            "smile", "baseball", "bird",
            "happy", "scissors", "cheek",
            "back", "jump", "drink",
            "ice", "cream", "cone", "car", "airplane",
            "clap", "circle", "pillow",
            "pinch", "kick", "dog",
            "basketball", "sleep", "camera",
            "prayer", "elephant", "blink",
            "doll", "spider", "point",
            "kite", "homework", "ladybug",
            "bed", "bird", "gum",
            "book", "dress", "queen",
            "puppy", "happy", "doctor"
    };


    private final String[] dictionayList = {
            "autocade", "apprehensible", "apicad"," aeroscope", "authorizable", "alimony" ,"asahigawa" ,"astigmia" ,
            "ascham" ,"acousmas", "adactylous", "aloneness" ,"american", "anadarko", "asthmatical", "arcuated", "ambagious", "antimoniate",
            "ashlaring", "achab", "acting", "abridge", "armary" ,"antimatter",
            "arborous", "antihero" ,"ambidextrously", "aboiteau", "automatograph" ,"absinth", "arcadianly",  "ambrosia",
            "ataxite",  "alternation", "ambulacra" ,"acetic", "adlerian", "armorist", "algorithmic", "allmouth", "aurelia", "asterodia",
            "annelida",
            "antique",
            "antonio",
            "aboveground",
            "agatho"
    };

    private final String[] animalList = {
            "alligator","ant","bear", "bee", "bird", "camel", "cat","cheetah", "chicken"
            ,"chimpanzee","cow","crocodile","deer","dog","dolphin","duck"
            ,"eagle","elephant","fish","fly","fox","frog","giraffe"
            ,"goat","goldfish","hamster",  "hippopotamus",  "horse",  "kangaroo",  "kitten",  "leopard",  "lion",  "lizard",  "lobster"
          ,  "monkey",  "octopus",  "ostrich",  "otter",  "owl",  "oyster",  "panda",  "parrot",  "pelican",  "pig",  "pigeon",  "porcupine"
            ,  "puppy",  "rabbit",  "rat", "reindeer",  "rhinoceros",  "rooster",  "scorpion",  "seal",  "shark",  "sheep",  "shrimp",  "snail",  "snake",  "sparrow",  "spider",  "squid",  "squirrel"
          ,  "swallow",  "swan",  "tiger",  "toad",  "tortoise",  "turtle",  "vulture",  "walrus",  "weasel",  "whale",  "wolf",  "zebra"
    };

    private final ArrayList<String> easyWords = new ArrayList<String>(Arrays.asList(easylist));
    private final ArrayList<String> dictionaryWords = new ArrayList<String>(Arrays.asList(dictionayList));
    private final ArrayList<String> animalWords = new ArrayList<String>(Arrays.asList(animalList));
    private int curlevel = 0;
    private int curMan = 0;
    private ArrayList<Boolean> curAnswer;
    private String key;
    Runnable runnable;

    public static Integer[] mThumbIds = {

            R.drawable.hang8,R.drawable.hang9,R.drawable.hang10,R.drawable.hang11

    };

    private void inputLetter(char c) {
        boolean isContain = false;
        for (int i = 0; i < key.length(); ++i) {
            char ans = key.charAt(i);
            if (c == ans) {
                isContain = true;
                curAnswer.set(i, true);
            }
        }
        if (curMan > 0 && isContain) {
            curMan--;
        }
        disableLetter(c);
    }

    private void disableLetter(char c) {
        char C = Character.toUpperCase(c);
        String buttonID = "button" + C;
        int resID = getResources().getIdentifier(buttonID, "id", "com.charan.hangman");
        Button b = (Button) findViewById(resID);
        b.setEnabled(false);
    }

    private String getCurAnser() {
        String result = new String();
        for (int i = 0; i < curAnswer.size(); ++i) {
            if (curAnswer.get(i)) {
                result += (key.charAt(i) + " ");
            } else {
                result += "_ ";
            }
        }
        Log.d("test", result);

        return result;
    }

    private void selectKey() {

        switch (curlevel) {
            case 0:
                key = easyWords.get(randomGenerator.nextInt(easyWords.size()));
                break;
            case 1:
                key = easyWords.get(randomGenerator.nextInt(easyWords.size()));
                break;
            case 2:
                key = dictionaryWords.get(randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 3:
                key = easyWords.get(randomGenerator.nextInt(easyWords.size()));
                break;
            case 4:
                key = dictionaryWords.get(randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 5:
                key = animalWords.get(randomGenerator.nextInt(animalWords.size()));
                break;
            case 6:
                key = dictionaryWords.get(randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 7:
                key = dictionaryWords.get(randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 8:
                key = dictionaryWords.get(randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 9:
                key = dictionaryWords.get(randomGenerator.nextInt(dictionaryWords.size()));
                break;
        }

        Log.d("test", key);
        Log.d("keylen = ", String.valueOf(key.length()));

        curAnswer = new ArrayList<Boolean>();
        for (int i = 0; i < key.length(); i++) {
            curAnswer.add(false);
        }
        HashSet<Character> letterSet = new HashSet<Character>();
        for (int i = 0; i < key.length(); i++) {
            letterSet.add(key.charAt(i));
        }

        int numOfBlanks = 3;
//        Log.d("blanks = ", String.valueOf(numOfBlanks));

        Log.d("numletters : ",String.valueOf(letterSet.size()));
        int numOfLetters = letterSet.size();
        int numOfShow = 3;
        if(numOfLetters==3){
            curMan=0;
            numOfShow=1;
        }
        if(numOfLetters==4){
            curMan =0;
            numOfShow=1;
        }
        else if(numOfLetters>4&&numOfLetters<7){
            curMan = 0;
            numOfShow= 2;
        }
//        else if(numOfLetters>6&&numOfLetters<9){
//            curMan=0;
//            numOfShow=4;
//        }
//        else if(numOfLetters>9){
//            curMan=0;
//            numOfShow=5;
//        }
//         if (numOfLetters > numOfBlanks) {
//            curMan = 0;
//            numOfShow = (numOfLetters - numOfBlanks);
//        }
//    else if (numOfLetters < numOfBlanks) {
//            curMan = numOfBlanks - numOfLetters;
//            numOfShow = 0;
//        } else if(numOfLetters==numOfBlanks){
//            curMan = 0;
//            numOfShow = 2;
//        }


        Log.d("test", "curMan" + curMan);

        Log.d("test", "numOfShow : " + numOfShow);

        for (int i = 0; i < numOfShow; ++i) {
            int itemIndex = randomGenerator.nextInt(letterSet.size());
            int j = 0;
            for (Character c : letterSet) {
                if (j == itemIndex) {
                    inputLetter(c);
                    letterSet.remove(c);
                    break;
                }
                ++j;
            }
        }
    }

    private void checkResult() {
        boolean isComplete = true;
        for (boolean b : curAnswer) {
            if (!b) {
                isComplete = false;
                break;
            }
        }

        final ImageView imageHanging = (ImageView) findViewById(R.id.imageHanging);
        TextView textFill = (TextView) findViewById(R.id.textFill);

        if (isComplete) {
           // imageHanging.setImageResource(R.drawable.hanggood);
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                disableLetter(c);
            }
            textFill.setText(getCurAnser());
            return;
        }

        //not complete
        if (curMan < 8) {
            textFill.setText(getCurAnser());
        }
        switch (curMan) {
            case 0:
                imageHanging.setImageResource(R.drawable.hang0);
                break;
            case 1:
                imageHanging.setImageResource(R.drawable.hang1);
                break;
            case 2:
                imageHanging.setImageResource(R.drawable.hang2);
                break;
            case 3:
                imageHanging.setImageResource(R.drawable.hang3);
                break;
            case 4:
                imageHanging.setImageResource(R.drawable.hang4);
                break;
            case 5:
                imageHanging.setImageResource(R.drawable.hang5);
                break;
            case 6:
                imageHanging.setImageResource(R.drawable.hang6);
                break;
            case 7:
                imageHanging.setImageResource(R.drawable.hang8);
                break;
            case 8:
                final Handler handler = new Handler();
                runnable = new Runnable() {
                    int i=0;
                    public void run() {
                        if(i<mThumbIds.length)
                        {
                            imageHanging.setImageResource(mThumbIds[i]);
                            i++;

                        }
                        else {
                            //stop the handler
                            handler.removeCallbacks(runnable);
                        }
                        handler.postDelayed(this, 400);
                    }
                };
                handler.postDelayed(runnable, 1000); //for initial delay..

                for (int i = 0; i < 26; i++) {
                    char c = (char) ('a' + i);
                    disableLetter(c);
                } //game over
                String rightAnswer = new String("");
                for (int i = 0; i < curAnswer.size(); ++i) {
                    rightAnswer += key.charAt(i) + " ";
                }
                SpannableString text = new SpannableString(rightAnswer);

                for (int i = 0; i < curAnswer.size(); ++i) {
                    if (!curAnswer.get(i)) {
                        text.setSpan(new ForegroundColorSpan(Color.GRAY), 2 * i, 2 * i + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    }
                }
                textFill.setText(text, TextView.BufferType.SPANNABLE);
                break;
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        curlevel = extras.getInt("level");
        Log.d("curlevel :", String.valueOf(curlevel));
        setContentView(R.layout.activity_gameplay);

        TextView textLevel = (TextView) findViewById(R.id.textLevel);
        TextView textFill = (TextView) findViewById(R.id.textFill);

        selectKey();
        switch (curlevel) {
            case 0:
                textLevel.setText("MOVIES");
                break;
            case 1:
                textLevel.setText("BOOKS");
                break;
            case 2:
                textLevel.setText("SPORTS");
                break;
        }
        textFill.setText(getCurAnser());

        checkResult();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickLetter(View view) {
        curMan++;
        switch (view.getId()) {
            case R.id.buttonA:
                inputLetter('a');
                break;
            case R.id.buttonB:
                inputLetter('b');
                break;
            case R.id.buttonC:
                inputLetter('c');
                break;
            case R.id.buttonD:
                inputLetter('d');
                break;
            case R.id.buttonE:
                inputLetter('e');
                break;
            case R.id.buttonF:
                inputLetter('f');
                break;
            case R.id.buttonG:
                inputLetter('g');
                break;
            case R.id.buttonH:
                inputLetter('h');
                break;
            case R.id.buttonI:
                inputLetter('i');
                break;
            case R.id.buttonJ:
                inputLetter('j');
                break;
            case R.id.buttonK:
                inputLetter('k');
                break;
            case R.id.buttonL:
                inputLetter('l');
                break;
            case R.id.buttonM:
                inputLetter('m');
                break;
            case R.id.buttonN:
                inputLetter('n');
                break;
            case R.id.buttonO:
                inputLetter('o');
                break;
            case R.id.buttonP:
                inputLetter('p');
                break;
            case R.id.buttonQ:
                inputLetter('q');
                break;
            case R.id.buttonR:
                inputLetter('r');
                break;
            case R.id.buttonS:
                inputLetter('s');
                break;
            case R.id.buttonT:
                inputLetter('t');
                break;
            case R.id.buttonU:
                inputLetter('u');
                break;
            case R.id.buttonV:
                inputLetter('v');
                break;
            case R.id.buttonW:
                inputLetter('w');
                break;
            case R.id.buttonX:
                inputLetter('x');
                break;
            case R.id.buttonY:
                inputLetter('y');
                break;
            case R.id.buttonZ:
                inputLetter('z');
                break;
        }

        checkResult();
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void nextOne(View view) {
        Intent intent = new Intent(this, GameplayActivity.class);
        intent.putExtra("level", curlevel);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
