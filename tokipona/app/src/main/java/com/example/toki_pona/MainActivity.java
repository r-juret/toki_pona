package com.example.toki_pona;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pixplicity.sharp.Sharp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import modele.Exception.TokiPonaException;
import modele.PhraseDetection;

public class MainActivity extends AppCompatActivity {

    private Button bout;
    private AutoCompleteTextView texteToki;
    private ImageView imgV;

    private ArrayAdapter<String> adapt;

    private String[] exemple;
    BufferedReader buf;

    public static String pathExtra;
    public static String base_url;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgV = findViewById(R.id.imageView);
        bout = findViewById(R.id.button);
        texteToki = findViewById(R.id.autoCompleteTextView2);

        exemple = new String[]{"sina e moku", "ken la jan lili li wile moku e telo",
                "tenpo ali la o kama sona", "sina sona e toki ni la sina sona e toki pona",
                "moku li pona", "jan li wile jo e ma", "jan lawa li moku e telo jaki"
        };


        adapt = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, exemple);
        texteToki.setAdapter(adapt);
        texteToki.setThreshold(1);

        pathExtra = getResources().getString(R.string.exemple);
        base_url = getFilesDir().toString(); // the directory where results and svg images will be stored

        texteToki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texteToki.showDropDown(); // to show the dropdown even if 0 character is entered
            }
        });

        if (PhraseDetection.getPathBD() == null){
            Toast.makeText(MainActivity.this,"debut de chargement des données", Toast.LENGTH_SHORT).show();

            // The svg images are stored in the directory /ressources
            // We set base_url, the results and temp directory will be created there
            PhraseDetection.setPaths(base_url+"/ressources", base_url);
            Log.e("PATHS",getFilesDir().toString());
            try {
                /* we copy all the files from assets to /resources
                   because our toki pona library need string paths to use the resources and we couldn't
                   have access to the assets directory with a string path (we only have access with getAssets())
                   If you have another way to have access to Assets with a String path feel free to report us your idea
                 */
                buf = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("ressources/fichiers.csv")));
                BufferedReader copie;
                String path = buf.readLine();


                while(path != null){
                    File file = new File(getFilesDir()+"/"+path);
                    if (path.endsWith("/")){
                        file.mkdirs(); // we use mkdirs instead of mkdir because we want to create several folders
                    }
                    else{
                        copie = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open(path)));

                        file.createNewFile();
                        FileOutputStream writer = new FileOutputStream(file);
                        String ligne = copie.readLine();
                        while (ligne != null){
                            writer.write((ligne+"\n").getBytes());
                            ligne = copie.readLine();
                        }
                    }
                    path = buf.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // When you click on the button you start the tanslation
        bout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // we stored the texte to translate
                    pathExtra = texteToki.getText().toString().toLowerCase().trim();

                    // the man method of our library
                    String path = PhraseDetection.draw(pathExtra);
                    Sharp mSvg = Sharp.loadFile(new File(path));
                    imgV.setImageDrawable(mSvg.getDrawable());
                } catch (TokiPonaException e) {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        // When you lick on the image it open a new activity where you can zoom on the image
        imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Zoom.class);
                it.putExtra("pathTxt", pathExtra);
                startActivity(it);
            }
        });
    }
}
