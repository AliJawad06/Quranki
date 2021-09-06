
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nigamehsan
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
   URLConnection conn;
    
          
            conn = new URL( "https://cdn.islamic.network/quran/audio/128/ar.alafasy/798.mp3").openConnection();
              InputStream is = conn.getInputStream();
              
    File file = new File( "798.mp3"); 

     OutputStream outstream = new FileOutputStream(file);
     byte[] buffer = new byte[4096];
     int len;
     while ((len = is.read(buffer)) > 0) {
        outstream.write(buffer, 0, len);
    }
    outstream.close();
      
    }
    
               }
    

