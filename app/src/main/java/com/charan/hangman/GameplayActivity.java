package com.charan.hangman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class GameplayActivity extends AppCompatActivity {
    private Random randomGenerator = new Random();
    private static int id = 0;
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
    private String clue;
    Runnable runnable;
    ImageView clueimage;
    TextView cluebox;

    public static Integer[] mThumbIds = {

            R.drawable.hang9,R.drawable.hang10,R.drawable.hang11

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
        if(c != ' '){
            char C = Character.toUpperCase(c);
            String buttonID = "button" + C;
            int resID = getResources().getIdentifier(buttonID, "id", "com.charan.hangman");
            Button b = (Button) findViewById(resID);
            b.setEnabled(false);
        }
    }

    private String getCurAnser() {
        String result = new String();
        for (int i = 0; i < curAnswer.size(); ++i) {
            if (curAnswer.get(i)) {
                result += (key.charAt(i) + " ");
            } else {if(key.charAt(i)==' ') {
                result += "    ";
            }else
                result += "_ ";
            }
        }
        Log.d("test", result);

        return result;
    }

    private void selectKey() {

        switch (curlevel) {
            case 0:
                getQuestion("movies.xls",0);
                break;
            case 1:
                getQuestion("dictionary.xls",0);
                break;
            case 2:
                getQuestion("dictionary.xls",0);
                break;
            case 3:
                getQuestion("tvshows.xls",0);
                break;
            case 4:
                key = dictionaryWords.get(id=randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 5:
                getQuestionAdvanced("pokemon.xls",1);
                break;
            case 6:
                getQuestion("places.xls",1);
                break;
            case 7:
                key = dictionaryWords.get(id=randomGenerator.nextInt(dictionaryWords.size()));
                break;
            case 8:
                getQuestionAdvanced("vocabulary.xls",0);
                break;
            case 9:
                getQuestion("music.xls",0);
                break;
        }

        Log.d("test", key);
        Log.d("id", String.valueOf(id));

        curAnswer = new ArrayList<Boolean>();
        for (int i = 0; i < key.length(); i++) {
            Log.d("key.char", String.valueOf(key.charAt(i)));
            if(key.charAt(i)==' ')
                curAnswer.add(true);
            else
                curAnswer.add(false);
        }
        HashSet<Character> letterSet = new HashSet<Character>();
        for (int i = 0; i < key.length(); i++) {
//            if(key.charAt(i)!=' ')
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

        Log.d("test", "curMan" + curMan);

        Log.d("test", "numOfShow : " + numOfShow);

        for (int i = 0; i < numOfShow; ++i) {
            int itemIndex = randomGenerator.nextInt(letterSet.size());
            int j = 0;
            for (Character c : letterSet) {
                if (j == itemIndex) {
                    Log.d("c", String.valueOf(c));
                    if(c!=' '){
                        inputLetter(c);
                        letterSet.remove(c);
                        break;
                    }

                }
                ++j;
            }
        }
    }

    private void getQuestionAdvanced(String file, int includeTag) {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(file);
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int row = sheet.getRows();
//            int column = sheet.getColumns();
//            String gameRow="";
            String tag="";

            int i = (int)(Math.random()*row);
            Cell wordcell = sheet.getCell(0,i);
            key = wordcell.getContents();
            Cell primaryClueCell = sheet.getCell(1,i);
            Cell additionalClueCell = sheet.getCell(2,i);
            tag=additionalClueCell.getContents();
            if(tag.isEmpty()||tag==null)
            {
                tag="";
            }else tag="+"+tag;
            if(includeTag>0){
                clue=primaryClueCell.getContents()+tag;
            }else
                clue = primaryClueCell.getContents();
            Log.i("Clue",clue);
        }catch (Exception e){

        }
    }

    private void getQuestion(String file,int includeTag) {
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(file);
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int row = sheet.getRows();
//            int column = sheet.getColumns();
            String gameRow="";
            String tag="";

            int i = (int)(Math.random()*row);
            Cell wordcell = sheet.getCell(0,i);
            gameRow = wordcell.getContents();
            String[] gameWord = gameRow.split("\\|\\|");
            key = gameWord[0];
            Log.i("Word",key);
            if(includeTag>0){
                tag=gameWord[2];
                clue=gameWord[1]+"+"+tag;
            }else
            clue = gameWord[1];
        }catch (Exception e){

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
            imageHanging.setImageResource(R.drawable.hanggood1);
            for (int i = 0; i < 26; i++) {
                char c = (char) ('a' + i);
                disableLetter(c);
            }
            textFill.setText(getCurAnser());
            return;
        }

        clueimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clue==null){
                    cluebox.setText("Sorry,no clues available");
                } else
                cluebox.setText(clue);
            }
        });

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

        clueimage = (ImageView) findViewById(R.id.clueimage);
        cluebox = (TextView) findViewById(R.id.cluebox);

        selectKey();
        switch (curlevel) {
            case 0:
                textLevel.setText("MOVIES");
                break;
            case 1:
                textLevel.setText("DICTIONARY");
                break;
            case 2:
                textLevel.setText("SPORTS");
                break;
            case 3:
                textLevel.setText("TV SHOWS");
                break;
            case 4:
                textLevel.setText("PERSONALITIES");
                break;
            case 5:
                textLevel.setText("POKEMON");
                break;
            case 6:
                textLevel.setText("COUNTRIES");
                break;
            case 7:
                textLevel.setText("FASHION");
                break;
            case 8:
                textLevel.setText("VOCABULARY");
                break;
            case 9:
                textLevel.setText("MUSIC");
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
        Log.d("Curman", String.valueOf(curMan));
        switch (view.getId()) {
            case R.id.buttonA:
                inputLetter('A');
                break;
            case R.id.buttonB:
                inputLetter('B');
                break;
            case R.id.buttonC:
                inputLetter('C');
                break;
            case R.id.buttonD:
                inputLetter('D');
                break;
            case R.id.buttonE:
                inputLetter('E');
                break;
            case R.id.buttonF:
                inputLetter('F');
                break;
            case R.id.buttonG:
                inputLetter('G');
                break;
            case R.id.buttonH:
                inputLetter('H');
                break;
            case R.id.buttonI:
                inputLetter('I');
                break;
            case R.id.buttonJ:
                inputLetter('J');
                break;
            case R.id.buttonK:
                inputLetter('K');
                break;
            case R.id.buttonL:
                inputLetter('L');
                break;
            case R.id.buttonM:
                inputLetter('M');
                break;
            case R.id.buttonN:
                inputLetter('N');
                break;
            case R.id.buttonO:
                inputLetter('O');
                break;
            case R.id.buttonP:
                inputLetter('P');
                break;
            case R.id.buttonQ:
                inputLetter('Q');
                break;
            case R.id.buttonR:
                inputLetter('R');
                break;
            case R.id.buttonS:
                inputLetter('S');
                break;
            case R.id.buttonT:
                inputLetter('T');
                break;
            case R.id.buttonU:
                inputLetter('U');
                break;
            case R.id.buttonV:
                inputLetter('V');
                break;
            case R.id.buttonW:
                inputLetter('W');
                break;
            case R.id.buttonX:
                inputLetter('X');
                break;
            case R.id.buttonY:
                inputLetter('Y');
                break;
            case R.id.buttonZ:
                inputLetter('Z');
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
