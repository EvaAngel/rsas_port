import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.List;

public class DataDeal {
    public static List<Port> dealPort(String str) {
        List<Port> portList = new ArrayList<>();
        Document document = Jsoup.parse(str);
        Elements tables = document.getElementsByClass("report_table");
        for (Element e : tables) {
            Elements ths = e.select("th");
            String value = null;
            String value2=null;
            if (ths.size()>=2) {
                value = ths.get(0).text();
                value2=ths.get(1).text();
                if (value.equals("端口")&&value2.equals("协议")) {
                    Elements trs = e.select("tr");
                    for (int i = 1; i < trs.size(); i++) {
                        Elements tds = trs.get(i).select("td");
                        Port port = new Port();
                        port.setPort(tds.get(0).text());
                        port.setCs_Xieyi(tds.get(1).text());
                        port.setYy_Xieyi(tds.get(2).text());
                        port.setIsOpen(tds.get(3).text());
                        portList.add(port);
                    }
                    return portList;
                }
            }
        }
        return null;
    }
}
