package com.borovoi.dmitrii.crocodileassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper = new DBHelper(this);
    Word currentWord = null;
    Level currentLevel = Level.LOW;
    boolean useSingleWord = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView wordView = (TextView) this.findViewById(R.id.textView);
        currentWord = dbHelper.getRandom();
        wordView.setText(currentWord.getWord());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.low).setChecked(true);
        menu.findItem(R.id.single).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.low:
                currentLevel = Level.LOW;
                return isChecked(item);
            case R.id.middle:
                currentLevel = Level.MIDDLE;
                return isChecked(item);
            case R.id.high:
                currentLevel = Level.HIGH;
                return isChecked(item);
            case R.id.single:
                if (item.isChecked()) {
                    item.setChecked(false);
                    useSingleWord = false;
                } else {
                    item.setChecked(true);
                    useSingleWord = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isChecked(MenuItem item) {
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        return true;
    }

    public void onClickBtnNext(View view) {
        TextView wordView = (TextView) this.findViewById(R.id.textView);
        currentWord = dbHelper.getRandom(useSingleWord, currentLevel);
        if (currentWord == null) {
            Toast.makeText(this, "Failed to generate new word", Toast.LENGTH_SHORT).show();
        }
        wordView.setText(currentWord.getWord());
    }

}
