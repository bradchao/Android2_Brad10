package tw.org.iii.brad.brad10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);

        } else {
            // Permission has already been granted
            Log.v("brad", "debug1");
            init();
        }

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.v("brad", "debug2");
                init();
            }else{
                Log.v("brad", "debug3");
                finish();
            }
        }
    }

    private void init(){
        String state = Environment.getExternalStorageState();
        Log.v("brad", state);   // mounted or removed
        File sdroot = Environment.getExternalStorageDirectory();
        Log.v("brad", sdroot.getAbsolutePath());

    }


}