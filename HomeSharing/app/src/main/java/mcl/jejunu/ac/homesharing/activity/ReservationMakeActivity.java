package mcl.jejunu.ac.homesharing.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import mcl.jejunu.ac.homesharing.R;
import mcl.jejunu.ac.homesharing.formatter.DayFormatter;

public class ReservationMakeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout checkInLayout, checkOutLayout, peopleLayout;
    private TextView checkInText, checkOutText, peopleText, moneyText;
    private Date checkIn, checkOut;
    private int people, money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_make);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        checkInLayout = (LinearLayout) findViewById(R.id.check_in_layout);
        checkOutLayout = (LinearLayout) findViewById(R.id.check_out_layout);
        peopleLayout = (LinearLayout) findViewById(R.id.people_layout);
        checkInLayout.setOnClickListener(this);
        checkOutLayout.setOnClickListener(this);
        peopleLayout.setOnClickListener(this);

        checkInText = (TextView) findViewById(R.id.checkInText);
        checkOutText = (TextView) findViewById(R.id.checkOutText);
        moneyText = (TextView) findViewById(R.id.moneyText);
        peopleText = (TextView) findViewById(R.id.peopleText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_reservation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v == checkInLayout) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = layoutInflater.inflate(R.layout.dialog_pick_date, null);
            alert.setView(view);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            alert.setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    checkIn = new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth());
                    checkInText.setText(DayFormatter.format(checkIn));
                }
            });
            alert.show();
        } else if (v == checkOutLayout) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = layoutInflater.inflate(R.layout.dialog_pick_date, null);
            alert.setView(view);
            final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
            alert.setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    checkOut = new Date(datePicker.getYear() - 1900, datePicker.getMonth(), datePicker.getDayOfMonth());
                    checkOutText.setText(DayFormatter.format(checkOut));
                }
            });
            alert.show();
        } else if (v == peopleLayout) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View view = layoutInflater.inflate(R.layout.dialog_edit_text, null);
            alert.setView(view);
            alert.setTitle("인원");
            final EditText input = (EditText) view.findViewById(R.id.edit_text);
            input.setInputType(2);
            alert.setNegativeButton("취소",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    });
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    people = Integer.parseInt(input.getText().toString());
                    peopleText.setText(String.valueOf(people));
                }
            });
            alert.show();
        }
    }
}