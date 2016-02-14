/*
 * 文件名：FXMLTableViewControllerMain.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： FXMLTableViewControllerMain.java
 * 修改人：hongjian
 * 修改时间：2015年10月28日
 * 修改内容：新增
 */
package javaFXClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import main.java.org.thehecklers.monologfx.MonologFX;
import main.java.org.thehecklers.monologfx.MonologFXBuilder;
import main.java.org.thehecklers.monologfx.MonologFXButton;
import main.java.org.thehecklers.monologfx.MonologFXButtonBuilder;

/**
 * 
 * @author hongjian
 */
public class FXMLTableViewControllerImport {
    @FXML
    TextField ip;
    @FXML
    TextField databaseName;
    @FXML
    TextField tblName;
    @FXML
    TextField fldName;
    @FXML
    TextField value;

    @FXML
    TextField sql;
    @FXML
    ToggleButton databaseType;
    @FXML
    RadioButton databaseTypeYanfa;
    @FXML
    RadioButton databaseTypeCeshi;
    @FXML
    ToggleGroup myToggleGroup;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    public void importValue() {
        // 获取值
        List<String> valueList = FXMLTableViewControllerMain.valueList;
        List<String> sqlList = new ArrayList<String>();
        // 拼接sql
        StringBuffer sb = new StringBuffer();
        sb.append("INSERT INTO " + databaseName.getText() + "." + tblName.getText() + "(" + fldName.getText() + ") VALUES(");
        StringBuffer sbnew = null;
        for (String string : valueList) {
            sbnew = new StringBuffer(sb);
            sbnew.append(string + ");");
            sqlList.add(sbnew.toString());
        }

        // 获取数据库连接

        Connection con = null;
        Statement sts = null;
        int result = 0;
        StringBuffer sbMsg = new StringBuffer();
        try {
            con = DBUtils.getConnection(ip.getText(), databaseName.getText(), password.getText(), username.getText());
            if (con != null) {
                sts = con.createStatement();
                // 遍历sql，批量插入
                for (String string : sqlList) {
                    try {
                        result += sts.executeUpdate(string);
                    } catch (Exception e) {
                        e.printStackTrace();
                        sbMsg.append(e.getMessage() + System.lineSeparator());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sbMsg.append(e.getMessage() + System.lineSeparator());
        } finally {
            DBUtils.reaseConnection(con, sts, null, null);
        }

        // 返回结果，弹出对话框

        MonologFXButton mlb = MonologFXButtonBuilder.create().defaultButton(true)
                // .icon("dialog_apply.png")
                .type(MonologFXButton.Type.OK).build();

        MonologFXButton mlb2 = MonologFXButtonBuilder.create().cancelButton(true)
                // .icon("dialog_cancel.png")
                .type(MonologFXButton.Type.CANCEL).build();
        String msg = "成功导入 " + result + " 条数据!";
        MonologFX mono = MonologFXBuilder.create().modal(true).message(msg + System.lineSeparator() + sbMsg.toString()).titleText("导入结果").button(mlb).button(mlb2)
                .buttonAlignment(MonologFX.ButtonAlignment.CENTER).build();

        MonologFXButton.Type retval = mono.show();

        mlb = MonologFXButtonBuilder.create().defaultButton(true).cancelButton(true).type(MonologFXButton.Type.OK).build();
        System.out.println("Return value=" + retval);

        ip.getScene().getWindow().hide();
    }

    @FXML
    protected void cancelImport(ActionEvent event) {
        ip.getScene().getWindow().hide();
    }

    @FXML
    public void selectYanfa(MouseEvent event) {
        ip.setText("192.168.1.61");
        username.setText("betterdev");
        password.setText("better2008");
    }

    @FXML
    public void selectCeshi(MouseEvent event) {
        ip.setText("192.168.1.");
        username.setText("writeuser");
        password.setText("writeuser");

    }

}
