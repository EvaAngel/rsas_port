import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
* 说明：本系统功能仅针对端口扫描中的开放端口统计，不涉及且不能用于漏洞统计
* */
public class Client {
    public static void main(String[] args) {
        //得到所有的IP及对应的端口信息
        List<Ip> ipList = new ArrayList<>();
        //获取文件夹下的所有主机html文件
        List<String> fileList = new ArrayList<>();
        File file = new File("C:\\Users\\nsfocus-pc\\Desktop\\139_淄博端口扫描_2018_08_09_html\\host");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                //测试html导入功能
                try {
                    String filename = files[i].getName().substring(0, 14);
                    String src = InputHtml.inputHtml(files[i]);
                    Ip ip = new Ip();
                    ip.setIp(filename);
                    List<Port> portList = DataDeal.dealPort(src);
                    ip.setPortList(portList);
                    ipList.add(ip);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(ipList.get(2).getIp());
        OutputExcel.outputExcel(ipList);
    }
}
