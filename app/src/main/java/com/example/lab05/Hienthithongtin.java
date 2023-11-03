package com.example.lab05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Hienthithongtin extends AppCompatActivity {

    ListView lstview;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;
    DBHelper dbHelper;

    EditText editTen, editCMND, editBosung, editHoten;
    CheckBox chkdocbao, chkdoccode, chkdocsach;

    private ContentValues pendingUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthithongtin);

        lstview = findViewById(R.id.lstview);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lstview.setAdapter(myadapter);

        dbHelper = new DBHelper(this);
        mydatabase = dbHelper.getWritableDatabase();

        editTen = findViewById(R.id.editHoten);
        editCMND = findViewById(R.id.editCMND);
        editBosung = findViewById(R.id.editBosung);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkdoccode = findViewById(R.id.chkdoccoding);
        chkdocsach = findViewById(R.id.chkdocsach);
        Button btn = findViewById(R.id.btnguitt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doShowInformation();

                String hoten = editTen.getText().toString().trim();
                String cmnd = editCMND.getText().toString().trim();
                String bosung = editBosung.getText().toString().trim();

                String bang = "";
                RadioGroup group = findViewById(R.id.radioGroup1);
                int id = group.getCheckedRadioButtonId();
                RadioButton rad = findViewById(id);
                bang = rad.getText() + "";

                String sothich = "";
                if (chkdocbao.isChecked()) {
                    sothich += chkdocbao.getText() + "\n";
                }
                if (chkdocsach.isChecked()) {
                    sothich += chkdocsach.getText() + "\n";
                }
                if (chkdoccode.isChecked()) {
                    sothich += chkdoccode.getText() + "\n";
                }

                ContentValues myvalue = new ContentValues();
                myvalue.put(DBHelper.NAME, hoten);
                myvalue.put(DBHelper.CMND, cmnd);
                myvalue.put(DBHelper.BANGCAP, bang);
                myvalue.put(DBHelper.SOTHICH, sothich);
                myvalue.put(DBHelper.THONGTINBOSUNG, bosung);

                Cursor cursor = mydatabase.query(
                        DBHelper.TABLE_NAME,
                        null,
                        DBHelper.CMND + " = ?",
                        new String[]{cmnd},
                        null, null, null
                );

                if (cursor.getCount() > 0) {
                    showUpdateDialog(myvalue);
                } else {
                    insertData(myvalue);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }

    private void displayData() {
        Cursor c = mydatabase.query("IN4", null, null, null, null, null, null, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            String data = c.getString(0) + " " + c.getString(1) + "  " + c.getString(2) + "  " + c.getString(3) + "  " + c.getString(4);
            mylist.add(data);
            c.moveToNext();
        }
        c.close();
        myadapter.notifyDataSetChanged();
    }

    public void doShowInformation() {
        String ten = editTen.getText() + "";
        ten = ten.trim();
        if (ten.length() < 3) {
            editTen.requestFocus();
            editTen.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String cmnd = editCMND.getText() + "";
        cmnd = cmnd.trim();
        if (cmnd.length() != 9) {
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        String bang = "";
        RadioGroup group = findViewById(R.id.radioGroup1);
        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }

        RadioButton rad = findViewById(id);
        bang = rad.getText() + "";

        String sothich = "";
        int dem = 0;
        if (chkdocbao.isChecked()) {
            sothich += chkdocbao.getText() + "\n";
            dem += 1;
        }
        if (chkdocsach.isChecked()) {
            sothich += chkdocsach.getText() + "\n";
            dem += 1;
        }
        if (chkdoccode.isChecked()) {
            sothich += chkdoccode.getText() + "\n";
            dem += 1;
        }
        if (dem < 1) {
            Toast.makeText(this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_SHORT).show();
            return;
        }

        String bosung = editBosung.getText() + "";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        String msg = ten + "\n";
        msg += cmnd + "\n";
        msg += bang + "\n";
        msg += sothich;
        msg += "-----------------------------\n";
        msg += "Thông tin bổ sung:\n";
        msg += bosung + "\n";
        msg += "-----------------------------";
        builder.setMessage(msg);
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hienthithongtin, menu);
        return true;
    }

    private void showUpdateDialog(final ContentValues values) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân đã tồn tại");
        builder.setMessage("Có tiến hành cập nhật không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pendingUpdate = values;
                clearForm();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void insertData(ContentValues values) {
        long result = mydatabase.insert(DBHelper.TABLE_NAME, null, values);

        if (result == -1) {
            Toast.makeText(Hienthithongtin.this, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Hienthithongtin.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
            clearForm();
        }
    }

    private void clearForm() {
        editTen.setText("");
        editCMND.setText("");
        editBosung.setText("");
    }
}
