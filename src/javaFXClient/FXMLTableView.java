/*
 * 文件名：FXMLTableView.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： FXMLTableView.java
 * 修改人：hongjian
 * 修改时间：2015年10月28日
 * 修改内容：新增
 */
package javaFXClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * 
 * @author hongjian
 */
public class FXMLTableView extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ServiceRouteHelper-hongjian");
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_tableview_Main.fxml"));
        Pane myPane = (Pane) loader.load();
        Scene myScene = new Scene(myPane);
        primaryStage.setScene(myScene);

        /*
         * 设置固定大小.
         */
        primaryStage.setMinWidth(800);
        primaryStage.setMaxWidth(700);
        primaryStage.setMaxHeight(800);
        primaryStage.setMinHeight(700);
        primaryStage.setMaximized(false);
        // primaryStage.getIcons().add(new
        // Image(FXMLTableView.class.getResourceAsStream("..\\image\\title.jpg")));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
