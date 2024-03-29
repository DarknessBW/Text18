package com.example.fumingzhen.text18;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
private final static String MyFileName="myfile";
    //@Override


    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    //检查是否能够读取外部存储卡，返回true表示可读，返回false不可读
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Write =(Button)findViewById(R.id.Write);
        Button Read =(Button)findViewById(R.id.Read);

        Write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OutputStream out =null;
                try{
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File file =Environment.getExternalStorageDirectory();
                        File myfile = new File(file.getAbsolutePath()+"/"+MyFileName);

                        FileOutputStream fileOutputStream=new FileOutputStream(myfile);
                        out =new BufferedOutputStream(fileOutputStream);
                        String content ="hello world";
                        try{
                            out.write(content.getBytes(StandardCharsets.UTF_8));

                        }finally {
                            if(out!=null)
                                out.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream in=null;
                try{
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        File file=Environment.getExternalStorageDirectory();
                        File myfile=new File(file.getCanonicalPath()+"/"+MyFileName);
                        FileInputStream fileInputStream=new FileInputStream(myfile);
                        in=new BufferedInputStream(fileInputStream);

                        int c;
                        StringBuilder stringBuilder=new StringBuilder("");
                        try{
                            while((c=in.read())!= -1){
                                stringBuilder.append((char)c);

                            }
                            Toast.makeText(MainActivity.this,stringBuilder,Toast.LENGTH_LONG).show();

                        }finally {
                            if(in!=null)
                                in.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}
