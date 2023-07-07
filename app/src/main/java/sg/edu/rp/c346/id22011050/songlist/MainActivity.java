package sg.edu.rp.c346.id22011050.songlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShow;
    ListView lv;
    EditText etTitle, etSinger,etYear;
    RadioGroup stars;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);
        lv = findViewById(R.id.lv);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        stars = findViewById(R.id.stars);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                //db.insertTask("Submit RJ", "25 Apr 2021");
                String insTitle = etTitle.getText().toString();
                String insSinger = etSinger.getText().toString();
                String insYear = etYear.getText().toString();
                int finalYear = Integer.parseInt(insYear);
                int insStars = 0 ;
                int checkedStars = stars.getCheckedRadioButtonId();
                if (checkedStars == R.id.star1){
                    insStars += 1;
                }else if (checkedStars == R.id.star2){
                    insStars += 2;
                }else if (checkedStars == R.id.star3){
                    insStars += 3;
                }else if (checkedStars == R.id.star4){
                    insStars += 4;
                }else if (checkedStars == R.id.star5){
                    insStars += 5;
                }
                //insert into data
                db.insertSong(insTitle,insSinger,finalYear,insStars);
                Toast toast = Toast.makeText(btnInsert.getContext(), "Song added successfully", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> dTitle = db.getSongsTitle();
                ArrayList<String> dSinger = db.getSongsSinger();
                ArrayList<String> dYear = db.getSongsYear();
                ArrayList<String> dStar = db.getSongsStar();
                ArrayList<Songs> alTask = db.getSongs();
                db.close();

                String txt = "";
                for (int i = 0; i < dTitle.size(); i++) {
                    Log.d("Database Content", i+". " + dTitle.get(i)  + dSinger.get(i) + dYear.get(i)
                          + dStar.get(i));
                    txt +=i+". " +"\nSong Title: " +dTitle.get(i)  + dSinger.get(i) + dYear.get(i)
                            + dStar.get(i);
                }

                ArrayAdapter<Songs> aaTasks =new ArrayAdapter<Songs>(MainActivity.this, android.R.layout.simple_list_item_1,alTask);
                lv.setAdapter(aaTasks);
            }
        });
    }
}