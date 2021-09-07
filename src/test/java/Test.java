import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.FileWriter;
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
        
        CSVWriter writer = new CSVWriter(new FileWriter(new File("data.csv")));
        String[] header= new String[2];
        header[0] = "Hello";
        header[1] = "Hi";
        
          writer.writeNext(header);
        
    
    
    
    
               }
    
}
    

