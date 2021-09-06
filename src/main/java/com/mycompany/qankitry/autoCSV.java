/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.qankitry;

import com.opencsv.CSVWriter;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Object;
import java.io.Closeable;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import org.apache.http.Header;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONString;


/**
 *
 * @author nigamehsan
 */
public class autoCSV {
    
    
    
    
    

   
    
    public static Object getAudioLink(int chap, int verse) throws java.net.MalformedURLException, java.io.IOException, URISyntaxException, InterruptedException{
        //int firstVerse = stuckVerse - 1; 
        
        
      
       
        String url = ("http://api.alquran.cloud/v1/ayah/"+ chap + ":" + verse + "/ar.alafasy");
        //System.out.println(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String body = response.body();
           JSONObject jsonObject = new JSONObject(body);
           
           
        JSONArray jsonArray =  jsonObject.getJSONObject("data").getJSONArray("audioSecondary");

        return jsonArray.get(0);
           
       
       
        

    } 
    
    
   
    public static ArrayList<Object>[] organize() throws IOException, MalformedURLException, URISyntaxException, InterruptedException{
                
        ArrayList<Object> front = new ArrayList<Object>();
        ArrayList<Object> back = new ArrayList<Object>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Which Surah #");
        int chapterNum = Integer.parseInt(sc.nextLine());
        
        System.out.println("Single: 1  or Range: 2");
        int singRang = Integer.parseInt(sc.nextLine());
        
        
        
                
                
        if(singRang == 1){
            System.out.println("What is the verse num you are stuck on");
            int stuckVint = Integer.parseInt(sc.nextLine());
            Object firstSingleV = getAudioLink(chapterNum, stuckVint);
            Object secondSingleV = getAudioLink(chapterNum, stuckVint-1);
            
            front.add(secondSingleV);
            back.add(firstSingleV);
                  
            
            
            
        }
        else{
            
               
               System.out.println("What is the first verse you are stuck on");
               int vRange1 = Integer.parseInt(sc.nextLine());
               
               
               System.out.println("What is the second verse you are stuck on");
               int vRange2 = Integer.parseInt(sc.nextLine());
               Object firstRangeStuck = getAudioLink(chapterNum,vRange1 - 1);
               
               front.add(firstRangeStuck);
               
               
               int x = 0;
               for(int i = vRange1; i<vRange2 +1; i++){
                  
                   Object rangeStuck = getAudioLink(chapterNum, i);
                   back.add(rangeStuck);
                   x++;
               }
        }
       ArrayList<Object>[] rVar = new ArrayList[2];
       rVar[0] = front;
       rVar[1] = back;
       return rVar; 
    }
    
    public static void download(String path) throws IOException{
        
        
       
            URLConnection conn;
    
          
            conn = new URL(path).openConnection();
              InputStream is = conn.getInputStream();
              
    File file = new File( "https:/\\/cdn.islamic.network\\/quran\\/audio\\/128\\/ar.alafasy\\/798.mp3"); 

     OutputStream outstream = new FileOutputStream(file);
     byte[] buffer = new byte[4096];
     int len;
     while ((len = is.read(buffer)) > 0) {
        outstream.write(buffer, 0, len);
    }
    outstream.close();
      
    }
    
           
                   
    
        
         
        
        
    
  
   
    public static void main(String[] args) throws IOException, MalformedURLException, URISyntaxException, InterruptedException {
        ArrayList<Object>[] result = organize();
        
        ArrayList<Object> frontc = result[0];
        ArrayList<Object> backc = result[1];
        
        for(int  i = 0; i < frontc.size(); i++){
            String strPath = frontc.get(i).toString();
            try{
            download(strPath);
            
            }catch(java.io.IOException ex){
                System.out.println(ex);
                
            }
        }
        
        
        
        

        
      
    }
   
    
    
}

