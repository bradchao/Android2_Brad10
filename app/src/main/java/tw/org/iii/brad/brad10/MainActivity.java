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
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private File sdroot, approot;

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
        sdroot = Environment.getExternalStorageDirectory();
        Log.v("brad", sdroot.getAbsolutePath());
        approot = new File(sdroot,
                "Android/data/"+getPackageName());
        Log.v("brad", approot.getAbsolutePath());

        if (!approot.exists()){
            if (approot.mkdirs()){
                Log.v("brad", "create OK");
            }else{
                Log.v("brad", "create XX");
            }
        }else{
            Log.v("brad", "exist");
        }
    }


    public void test1(View view) {
        try {
            FileOutputStream fout =
                    new FileOutputStream(sdroot.getAbsolutePath()+"/001.txt");
            fout.write("Hello, World".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("brad", e.toString());
        }

    }
    public void test2(View view) {
        try {
            FileOutputStream fout =
                    new FileOutputStream(approot.getAbsolutePath()+"/001.txt");
            fout.write("Hello, World".getBytes());
            fout.flush();
            fout.close();
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }

    public void test3(View view) {
        try{
            FileInputStream fin =
                    new FileInputStream(sdroot.getAbsolutePath()+"/001.txt");
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(fin));
            String line = reader.readLine();
            fin.close();
            Log.v("brad", line);
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }
    public void test4(View view) {
        try{
            FileInputStream fin =
                    new FileInputStream(approot.getAbsolutePath()+"/001.txt");
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(fin));
            String line = reader.readLine();
            fin.close();
            Log.v("brad", line);
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }

    public void test5(View view) {
        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        if (musicDir.exists() && musicDir.isDirectory()){
            File[] musics = musicDir.listFiles();
            for (File music : musics){
                Log.v("brad", music.getAbsolutePath());
            }
        }


    }
}
