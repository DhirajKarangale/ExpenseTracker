package com.learntodroid.piechartandroid;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delete extends AppCompatActivity {
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        Intent intent= getIntent();
        id= intent.getStringExtra("rowId");
        Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();

        Button btnDelete=  findViewById(R.id.btnDel);
        Button btnCancel = findViewById(R.id.cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String IdToBeDeleted= id;

                FeedReaderDbHelper dbHelper = new FeedReaderDbHelper(delete.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // Define 'where' part of query.
                String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
                String selectionArgs[] = {IdToBeDeleted};
// Issue SQL statement.
                int deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
                if(deletedRows==0){
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "No Entry Exist with the ID" + IdToBeDeleted, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                else if(deletedRows>0){
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "Entry with  ID " + IdToBeDeleted + " is Deleted Successfully!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });
    }
}
