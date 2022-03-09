import java.io.*;
import java.util.*;

public class CharsetConverter {
    public static void main(String[] args) {
        String fi = args[0];
        File file = new File(fi);
        StringBuilder sb = new StringBuilder(fi);

        if(file.exists() && args.length == 3) {
            sb.delete(sb.indexOf(".txt"), sb.length());
            sb.append("_"+args[2]+".txt");
            System.out.println(sb.toString());
        }

        try(
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), args[1]);
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sb.toString()), args[2]);
        ){
            int len = 0;
            char[] s = new char[1024];
            while( (len = reader.read(s)) != -1) {
                writer.write(s ,0, len);
            }
        }  catch(FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
