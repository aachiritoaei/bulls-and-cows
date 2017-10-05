package com.ach.alex.bullsandcowsreloaded;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.spark.submitbutton.SubmitButton;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PlayScreen extends AppCompatActivity {

//    private EditText nameInput;
    private EditText numberInput;
    private TextView checkResult;

    private Button checkGuess;
    private Button showSolution;
    private Button backToMenu;

    private Dialog submitDialog;

    private Game game;

    private final String filename = "scores.data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        // start new game
        game = new Game();

        // create dialog
        createSubmitDialog();

        // name input
//        nameInput = (EditText) findViewById(R.id.play_name_input);
//        nameInput.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    unblockButtons();
//                    return true;
//                }
//                return false;
//            }
//        });

        // text areas
        numberInput = (EditText) findViewById(R.id.play_number_input);
        checkResult = (TextView) findViewById(R.id.play_check_result);

        // check guess button
        checkGuess = (Button) findViewById(R.id.play_check);
        checkGuess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String guess = numberInput.getText().toString();
                if (guess.length() == 4) {
                    String result = game.getHint(guess);
                    if (result.equals("4B0C")) { // correct result
                        checkResult.setText("Congratulations! You have guessed the nunmber! " +
                                "Your score is: " + game.getFinalScore());
                        registerScore();
                        blockButtons();
                    } else {
                        checkResult.setText(result);
                    }
                } else { // incorrect length
                    numberInput.setText("");
                }
            }
        });

        // show solution button
        showSolution = (Button) findViewById(R.id.play_show_solution);
        showSolution.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                blockButtons();
                showSolution.setText(game.getSecretNumber());
            }
        });

        // go back button
        backToMenu = (Button) findViewById(R.id.play_back);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        blockButtons();
    }

    private void blockButtons() {
        numberInput.setEnabled(false);
        checkGuess.setEnabled(false);
        showSolution.setEnabled(false);
    }

    private void unblockButtons() {
//        game.setPlayerName(nameInput.getText().toString());
//        nameInput.setEnabled(false);
        numberInput.setEnabled(true);
        checkGuess.setEnabled(true);
        showSolution.setEnabled(true);
    }


    private void registerScore() {
        TreeMap<Float, String> scores = new TreeMap<>(Collections.<Float>reverseOrder());

        FileInputStream input = null;
        FileOutputStream output = null;

        File file = new File(getFilesDir(), filename);

        try {
            if (!file.exists())
                file.createNewFile();

            // open input
            input = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(isr);

            // parse contents
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] components = line.split(" ");
                String name = components[0];
                Float score = Float.parseFloat(components[1]);
                scores.put(score, name);
            }

            scores.put(game.getFinalScore(), game.getPlayerName());

            // close input
            bufferedReader.close();
            isr.close();
            input.close();

            // open output
            output = openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(output);
            BufferedWriter bw = new BufferedWriter(osw);

            // parse output, maximum 10 values
            int count = 0;
            for (Map.Entry<Float, String> entry : scores.entrySet()) {
                if (count > 10) break;
                System.out.println(entry);
                bw.write(entry.getValue() + " " + entry.getKey() + "\n");
                count++;
            }

            // close ouput
            bw.close();
            osw.close();
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createSubmitDialog() {
        submitDialog = new Dialog(this);
        submitDialog.setContentView(R.layout.submit_dialog);
        submitDialog.setCanceledOnTouchOutside(false);

        final SubmitButton submitButton = (SubmitButton) submitDialog.findViewById(R.id.submit_button);
        final EditText insertName = (EditText) submitDialog.findViewById(R.id.insert_name);
        insertName.setHint("Insert name..");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(insertName.getText().toString().equals(""))) {
                    game.setPlayerName(insertName.getText().toString());
                    submitDialog.dismiss();
                }
            }
        });

        submitDialog.show();

    }
}
