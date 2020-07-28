package com.example.srishti.connect4game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //0 for player1(green)
    //1 for player2(yellow)
    //2 for empty cell
    //winPos represents winning positions
    int[][] winPos = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}, {0, 4, 8, 12}, {1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {0, 5, 10, 15}, {3, 6, 9, 12}};

    int turn = 0; //player1's(green) turn initially
    int[] cellState= {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean stopGame = false;
    public void dropIn(View view)
    {
        ImageView cell = (ImageView) view;
        int currentCell = Integer.parseInt(cell.getTag().toString());
        if(cellState[currentCell] == 2 && !stopGame) {
            cell.setTranslationY(-1500);
            if (turn == 0) {
                cell.setImageResource(R.drawable.green_counter);
                turn = 1;
            } else if (turn == 1) {
                cell.setImageResource(R.drawable.yellow_counter);
                turn = 0;
            }
            cellState[currentCell] = turn;
            cell.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] wp: winPos) {
                //winning condition
                if (cellState[wp[0]] == cellState[wp[1]] && cellState[wp[1]] == cellState[wp[2]] && cellState[wp[2]] == cellState[wp[3]]  && cellState[wp[0]] != 2) {
                    String winner = "";
                    stopGame = true;
                    Log.i("tag", "entered");
                    if (turn == 1)
                        winner = "Green has won";
                    else if (turn == 0)
                        winner = "Yellow has won";

                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    winnerTextView.setVisibility(View.VISIBLE);
                    winnerTextView.setText(winner);
                    break;
                }

            }
        }
    }
    public void resetGame(View view)
    {
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        for(int i = 0; i < tableLayout.getChildCount(); i++)
        {
            TableRow row = (TableRow)tableLayout.getChildAt(i);
            for(int j = 0; j < row.getChildCount(); j++)
            {
                ImageView img = (ImageView) row.getChildAt(j);
                img.setImageDrawable(null);
            }
        }
        for(int i = 0; i < 16; i++)
        {
            cellState[i] = 2;
        }

        turn = 0;
        stopGame = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
