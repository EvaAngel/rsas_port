import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputExcel {
    public static Boolean outputExcel(List<Ip> ipList,String outPath) {
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
            File file = new File(outPath);
            FileOutputStream fileoutputStream = new FileOutputStream(file);
            workbook.write(fileoutputStream);
            fileoutputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
