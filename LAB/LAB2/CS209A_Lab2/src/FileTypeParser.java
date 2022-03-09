import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTypeParser {
//    public enum FileType{
//        PNG("89504e47", "png"),
//        JARorZip("504b0304", "jarORzip"),
//        CLASS("cafebabe", "class");
//
//    }
    public static void main(String[] args) {
        String fi = args[0];
        System.out.println("Filename:"+fi);
        try(
                FileInputStream inputStream = new FileInputStream(fi);
                ) {
            byte head[] = new byte[4];
            inputStream.read(head, 0, head.length);
            StringBuilder hstr = new StringBuilder();
            System.out.print("File Header(Hex):[");
            for(int i = 0; i < head.length; i++) {
                String tmp = Integer.toHexString(head[i] & 0xFF);
                if(tmp.length() < 2) {
                    hstr.append(0);
                }
                hstr.append(tmp);
            }
            for(int i = 0; i < head.length-1; i++) {
                System.out.print(hstr.substring(i*2,(i+1)*2).toString()+",");
            }

            System.out.println(hstr.substring((head.length-1)*2,head.length*2).toString()+"]");

            String filetype=null;
            if(hstr.toString().equals("89504e47")) {
                filetype = "png";
            } else if (hstr.toString().equals("504b0304")) {
                filetype = "jarORzip";
            } else if (hstr.toString().equals("cafebabe")) {
                filetype = "class";
            }
            System.out.println("File Type:"+filetype);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
