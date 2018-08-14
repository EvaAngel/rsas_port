import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gui {
    public static void rsasGui() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame jFrame = new JFrame("rsas整理工具");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setBounds(200, 200, 550, 300);
        JLabel jLabel = new JLabel("请选择所要抽取的html");
        jLabel.setBounds(20, 20, 150, 30);
        JTextField jTextField = new JTextField();
        jTextField.setBounds(170, 26, 200, 20);
        JButton jButton = new JButton("选择文件");
        jButton.setBounds(400, 26, 100, 20);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                jTextField.setText(file.getAbsolutePath());
                /*if (file.isDirectory()) {
                    System.out.println("文件夹:" + file.getAbsolutePath());
                } else if (file.isFile()) {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());*/
            }
        });
        JLabel jLabel2 = new JLabel("请输入保存的路径地址");
        jLabel2.setBounds(20, 100, 150, 20);
        JTextField jTextField2 = new JTextField();
        jTextField2.setBounds(170, 100, 200, 20);
        JButton jButton2 = new JButton("数据抽取");
        jButton2.setBounds(400, 100, 100, 20);
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String outPath = jTextField2.getText();
                String inPath = jTextField.getText();
                //得到所有的IP及对应的端口信息
                List<Ip> ipList = new ArrayList<>();
                //获取文件夹下的所有主机html文件
                List<String> fileList = new ArrayList<>();
                File file = new File(inPath);
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
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                System.out.println(ipList.get(2).getIp());
                OutputExcel.outputExcel(ipList,outPath);
            }
        });
        JLabel jLabel3 = new JLabel("说明：输入格式到host目录；输出地址格式如下：**：//**。例如：D://text.xlsx");
        jLabel3.setBounds(20, 200, 500, 20);
        jFrame.add(jLabel);
        jFrame.add(jTextField);
        jFrame.add(jButton);
        jFrame.add(jLabel2);
        jFrame.add(jTextField2);
        jFrame.add(jButton2);
        jFrame.add(jLabel3);
        jFrame.setVisible(true);
    }
}
