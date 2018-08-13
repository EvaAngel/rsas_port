import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputExcel {
    public static Boolean outputExcel(List<Ip> ipList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("0");
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("主机IP");
        row.createCell(1).setCellValue("开放端口");
        row.createCell(2).setCellValue("协议");
        int i = 1;
        int sum = 0;
        for (Ip ip : ipList) {
            if (ip.getPortList()==null) {
                sum++;
                Row roww = sheet.createRow(sum);
                Cell cell0 = roww.createCell(0);
                cell0.setCellValue(ip.getIp());
            } else {
                int j = -1;
                for (Port p : ip.getPortList()) {
                    j++;
                    Row rows = sheet.createRow(sum + i + j);
                    Cell cell0 = rows.createCell(0);
                    cell0.setCellValue(ip.getIp());
                    Cell cell1 = rows.createCell(1);
                    cell1.setCellValue(p.getPort());
                    Cell cell2 = rows.createCell(2);
                    cell2.setCellValue(p.getYy_Xieyi());
                }
                sum += i + j;  //这里用于计算行数递进关系
            }
        }

        workbook.setSheetName(0, "IP资产");
        try {
            File file = new File("E:\\test.xlsx");
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {

        Port port1 = new Port();
        port1.setPort("12");
        port1.setYy_Xieyi("http");
        Port port2 = new Port();
        port2.setPort("12");
        port2.setYy_Xieyi("http");
        Port port3 = new Port();
        port3.setPort("12");
        port3.setYy_Xieyi("http");
        Port port4 = new Port();
        port4.setPort("12");
        port4.setYy_Xieyi("http");
        List<Port> portList = new ArrayList<>();
        portList.add(port1);
        portList.add(port2);
        portList.add(port3);
        portList.add(port4);
        Ip ip = new Ip();
        ip.setIp("1.1.1.1");
        ip.setPortList(portList);
        Port port5 = new Port();
        port5.setPort("12");
        port5.setYy_Xieyi("http");
        Port port6 = new Port();
        port6.setPort("12");
        port6.setYy_Xieyi("http");
        Port port7 = new Port();
        port7.setPort("12");
        port7.setYy_Xieyi("http");
        List<Port> portList2 = new ArrayList<>();
        portList2.add(port5);
        portList2.add(port6);
        portList2.add(port7);
        Ip ip2 = new Ip();
        ip2.setIp("2.2.2.2");
        ip2.setPortList(portList2);
        List<Ip> ipList = new ArrayList<>();
        ipList.add(ip);
        ipList.add(ip2);
        OutputExcel.outputExcel(ipList);
    }
}
