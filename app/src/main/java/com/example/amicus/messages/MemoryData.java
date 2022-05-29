package com.example.amicus.messages;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MemoryData {


    public static void saveName(String data, Context context){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("name.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePhone(String data, Context context){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("phone.txt",Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void saveLastMsgTS(String data,String chatId, Context context){
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(chatId+".txt",Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getName(Context context){
        String data ="";
        try {
            FileInputStream fis = context.openFileInput("name.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!=null){
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static String getPhone(Context context){
        String data ="";
        try {
            FileInputStream fis = context.openFileInput("phone.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!=null){
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getLastMsgTS(Context context,String chatId){
        String data ="0";
        try {
            FileInputStream fis = context.openFileInput(chatId+".txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = bufferedReader.readLine())!=null){
                sb.append(line);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
