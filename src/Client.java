import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
* 说明：本系统功能仅针对端口扫描中的开放端口统计，不涉及且不能用于漏洞统计
* */
public class Client {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Gui.rsasGui();
            }
        });
    }
}
