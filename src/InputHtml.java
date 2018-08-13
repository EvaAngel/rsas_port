import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputHtml {


    public static String inputHtml(File file) throws IOException {
        String src;
        String row;
        StringBuffer sb = new StringBuffer();   //这个地方建成static变量，导致之前的没清空，忙活了好长时间，醉了
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while ((row = br.readLine()) != null) {
                sb.append(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        src = sb.toString();
        return src;
    }
}
