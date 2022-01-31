package net.ivanvega.clientecontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor c =  getContentResolver().query(
                UsuarioProviderContract.CONTENT_URI,
                UsuarioProviderContract.COLUMNS,
                null,null,null
        );

        if(c!=null){
            while(c.moveToNext()){
                Log.d("providerusuario", "Usuario: "+ c.getInt(0)
                + " - " + c.getString(1));
            }
        }

    }
}