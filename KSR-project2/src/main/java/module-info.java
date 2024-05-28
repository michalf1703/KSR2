module ksr.ksrproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires org.apache.commons.io;
    requires java.desktop;
    requires org.jfree.jfreechart;
    requires javafx.graphics;
    requires lombok;

    opens ksr2.ksrproject2 to javafx.fxml;
    exports ksr2.ksrproject2;
    // opens ksr2.ksrproject2.logic to javafx.fxml;
    exports ksr2.ksrproject2.view;
    opens ksr2.ksrproject2.view to javafx.fxml;
}