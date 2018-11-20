package com.example.user.kakao;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context ctx = Login.this;
        final EditText etlD = findViewById(R.id.etID);
        final EditText etPass = findViewById(R.id.etPass);
        findViewById(R.id.btLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validation : 유효성체크
                if(etlD.getText().length()!=0 && etPass.getText().length()!=0){
                    String id = etlD.getText().toString();
                    String pass = etPass.getText().toString();
                    final ItemExist query = new ItemExist(ctx);
                    query.id = id;
                    query.pw = pass;
                    new Main.ExecuteService() {
                        @Override
                        public void perform() {
                            if(query.execute()){
                                startActivity(new Intent(ctx, MemberList.class));
                            }else{
                                startActivity(new Intent(ctx,Login.class));
                            }
                        }
                    }.perform();
                }else{
                    startActivity(new Intent(ctx,Login.class));
                }
            }
        });
        findViewById(R.id.btCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ctx,Login.class));
            }
        });
    } // end of onCreate
    private class LoginQuery extends Main.QueryFactory{
        SQLiteOpenHelper helper;
        public LoginQuery(Context ctx) {
            super(ctx);
            helper = new Main.SqliteHelper(ctx);
        }

        @Override
        public SQLiteDatabase getDatabase() {
            return helper.getReadableDatabase();
        }
    } // LoginQuery End
    private class ItemExist extends LoginQuery{
        String id, pw;
        public ItemExist(Context ctx) {
            super(ctx);
        }
        public boolean execute(){
            /*
            String s = String.format(
                            " SELECT * FROM %s + " +
                            " WHERE %s LIKE '%s' AND %s LIKE '%s'",
                            DBInfo.MBR_TABLE, DBInfo.MBR_SEQ, id,
                            DBInfo.MBR_PASS, pw
                        );
            */
            return super
                    .getDatabase()
                    .rawQuery(String.format(
                                    " SELECT * FROM %s + " +
                                    " WHERE %s LIKE '%s' AND %s LIKE '%s'",
                                    DBInfo.MBR_TABLE, DBInfo.MBR_SEQ, id,
                                    DBInfo.MBR_PASS, pw
                    ),null)
                    .moveToNext()
                    ;
        }
    }
}
