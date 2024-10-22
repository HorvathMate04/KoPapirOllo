package com.example.kopapirollo;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Dictionary;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewMyChoice;
    private ImageView imageViewPcChoice;
    private TextView textViewYourPoints;
    private TextView textViewPcPoints;
    private int pcPoints;
    private int yourPoints;
    private Button buttonRock;
    private Button buttonPaper;
    private Button buttonScissors;
    private Random random;
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        buttonRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compareChoices(0);
            }
        });
        buttonPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compareChoices(1);
            }
        });
        buttonScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compareChoices(2);
            }
        });
    }

    public void compareChoices(int choice){
        randomNumber = random.nextInt(3);
        imageViewPcChoice.setImageResource(imageToLoad(randomNumber));
        imageViewMyChoice.setImageResource(imageToLoad(choice));

        if(choice + 1 == randomNumber || (choice == 2 && randomNumber == 0)){
            pcPoints++;
            if(pcPoints == 3){
                displayResult(0);
            }else{
                Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();
            }
            textViewPcPoints.setText(String.valueOf(pcPoints));
        } else if (randomNumber + 1 == choice || (randomNumber == 2 && choice == 0)) {
            yourPoints++;
            if(yourPoints == 3){
                displayResult(1);
            }else{
                Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
            }
            textViewYourPoints.setText(String.valueOf(yourPoints));
        }

    }

    public void displayResult(int player){
        String finalText;
        if(player == 1){
            finalText = "Győztél";
        }else{
            finalText = "Vereség";
        }

        AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
        alertBox.setTitle(finalText);
        alertBox.setMessage("Szeretne új játékot játszani?");
        alertBox.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        alertBox.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alertBox.create();
        alertBox.show();

    }

    public int imageToLoad(int choice){
        int imgSrc;
        switch (choice){
            case 0:
                imgSrc = R.drawable.rock;
                break;
            case 1:
                imgSrc = R.drawable.paper;
                break;
            case 2:
                imgSrc = R.drawable.scissors;
                break;
            default:
                imgSrc = R.drawable.rock;
                break;
        }
        return imgSrc;
    }

    public void init(){
        imageViewMyChoice = findViewById(R.id.imageViewMyChoice);
        imageViewPcChoice = findViewById(R.id.imageViewPcChoice);
        textViewYourPoints = findViewById(R.id.textViewYourPoints);
        textViewYourPoints.setText("0");
        pcPoints = 0;
        yourPoints = 0;
        textViewPcPoints = findViewById(R.id.textViewPcPoints);
        textViewPcPoints.setText("0");
        buttonRock = findViewById(R.id.buttonRock);
        buttonPaper = findViewById(R.id.buttonPaper);
        buttonScissors = findViewById(R.id.buttonScissors);
        random = new Random();;
    }
}