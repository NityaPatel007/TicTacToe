package com.example.assignment2_nitya_patel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private HomeFunctionViewModel viewModel;
    private ImageView[] images = new ImageView[9];
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewModel = new HomeFunctionViewModel();

        textViewResult = findViewById(R.id.Result);

        images[0] = findViewById(R.id.Img1);
        images[1] = findViewById(R.id.Img2);
        images[2] = findViewById(R.id.Img3);
        images[3] = findViewById(R.id.Img4);
        images[4] = findViewById(R.id.Img5);
        images[5] = findViewById(R.id.Img6);
        images[6] = findViewById(R.id.Img7);
        images[7] = findViewById(R.id.Img8);
        images[8] = findViewById(R.id.Img9);

        for (ImageView image : images) {
            image.setOnClickListener(this);
        }

        Button PlayAgain = findViewById(R.id.btnPlay);
        PlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
            }
        });

        Button Quit = findViewById(R.id.btnQuit);
        Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int boxPosition = -1;
        for (int i = 0; i < images.length; i++) {
            if (v.getId() == images[i].getId()) {
                boxPosition = i;
                break;
            }
        }
        if (boxPosition != -1 && viewModel.isBoxSelectable(boxPosition)) {
            viewModel.getBoxPositions()[boxPosition] = viewModel.getCurrentPlayer();
            ((ImageView) v).setImageResource(viewModel.getCurrentPlayer() == 1 ? R.drawable.x_image : R.drawable.o_image);

            if (viewModel.checkWin()) {
                String winnerName = viewModel.getCurrentPlayer() == 1 ? getIntent().getStringExtra("PLAYER1_NAME") : getIntent().getStringExtra("PLAYER2_NAME");
                gameOver("Congratulations!", winnerName);
            } else if (viewModel.getTotalSelectedBoxes() == 9) {
                gameOver("Its A Draw !!", "");
            } else {
                viewModel.incrementTotalSelectedBoxes();
                viewModel.setCurrentPlayer(viewModel.getCurrentPlayer() == 1 ? 2 : 1);
            }


        }
    }

    private void gameOver(String message, String winnerName) {
        textViewResult.setText(message + " " + winnerName);
        disableBoxes();
    }


    private void playAgain() {
        textViewResult.setText("");
        enableBoxes();
        viewModel.resetGame();
        resetImages();
    }

    private void disableBoxes() {
        for (ImageView image : images) {
            image.setEnabled(false);
        }
    }

    private void enableBoxes() {
        for (ImageView image : images) {
            image.setEnabled(true);
        }
    }

    private void resetImages() {
        for (ImageView image : images) {
            image.setImageResource(0);
        }
    }
}
