package com.example.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnInsert;
    Button btnDelete;
    Button btnSelect;
    Button btnSearch;
    EditText editName;
    EditText editAge;
    EditText editDelete;
    EditText editSearch;
    ListView lvItems;


    // This is our DataManager instance
    DataManager dm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataManager(this);
// get a reference to the UI item
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        editName = (EditText) findViewById(R.id.editName);
        editAge = (EditText) findViewById(R.id.editAge);
        editDelete = (EditText) findViewById(R.id.editDelete);
        editSearch = (EditText) findViewById(R.id.editSearch);
         lvItems = (ListView) findViewById(R.id.LvItems);
// Register MainActivity as a listener
        btnSelect.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }
    public void showData(Cursor c){
        LayoutInflater inflater = getLayoutInflater();

        View toastLayout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.toast_root_view));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastLayout);
        while (c.moveToNext()){
            TextView header = (TextView) toastLayout.findViewById(R.id.toast_header);
            header.setText(c.getString(1));
            TextView body = (TextView) toastLayout.findViewById(R.id.toast_body);
            body.setText(c.getString(2));
            Log.i(c.getString(1) ,c.getString(2));
            toast.show();

        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnInsert:
                dm.insert(editName.getText().toString(),
                        editAge.getText().toString());
                break;
            case R.id.btnSelect:
                //showData(dm.selectAll());
                Intent act2 = new Intent(this, MainActivity2.class);
                startActivity(act2);

                break;
            case R.id.btnSearch:
                showData(dm.searchName(editSearch.getText().toString()));
                break;
            case R.id.btnDelete:
                dm.delete(editDelete.getText().toString());
                break;
        }
    }
}