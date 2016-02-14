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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * 
 * @author hongjian
 */
public class FXMLTableViewControllerPage {
    @FXML
    TextField applicationName;
    @FXML
    TextField databaseName;
    @FXML
    TextField tblName;
    @FXML
    TextField fldName;
    @FXML
    TextField condition;
    @FXML
    TextField fldSort;
    @FXML
    TextField pageSize;

    @FXML
    TextField sql;
    @FXML
    TextField bizTime;
    @FXML
    RadioButton databaseType;
    @FXML
    TextField dataBaseAddress;

    @FXML
    public void fillValue() {
        String sqlStr = this.sql.getText();
        StringBuffer sb = new StringBuffer(sqlStr);
        sb.append(";");
        sqlStr = sb.toString();

        this.applicationName.setText("分页服务");
        sqlStr = sqlStr.replace("SELECT", "SELECT ").replace("FROM", " FROM ").replace("WHERE", " WHERE ").replace("ORDER BY", " ORDER BY ").replace("LIMIT", " LIMIT ").replace("where", " WHERE ")
                .replace("limit", " LIMIT ").replace("Limit", " LIMIT ").replace("Where", " WHERE ").replace("select", "SELECT ").replace("from", " FROM ").replace("order by", " ORDER BY ")
                .replaceAll("AND ", " AND ").replaceAll("and ", " AND ");
        // System.out.println(s);

        Pattern p = Pattern.compile("SELECT(?<item>.*?)FROM(?<dataBaseName>.*?)\\.(?<tableName>.*?)WHERE(?<condition>.*?)ORDER BY(?<orderBy>.*?)LIMIT(?<limit>.*?);");
        Matcher m = p.matcher(sqlStr);
        boolean flag = false;
        if (m.find()) {
            tblName.setText(m.group("tableName").trim());
            databaseName.setText(m.group("dataBaseName").trim());
            fldName.setText(m.group("item").trim());
            pageSize.setText(m.group("limit").trim());
            fldSort.setText(m.group("orderBy").trim());
            condition.setText(m.group("condition").trim());
            flag = true;
        } else {
            p = Pattern.compile("SELECT(?<item>.*?)FROM(?<dataBaseName>.*?)\\.(?<tableName>.*?)WHERE(?<condition>.*?)LIMIT(?<limit>.*?);");
            m = p.matcher(sqlStr);
            if (m.find()) {
                tblName.setText(m.group("tableName").trim());
                databaseName.setText(m.group("dataBaseName").trim());
                fldName.setText(m.group("item").trim());
                pageSize.setText(m.group("limit").trim());
                condition.setText(m.group("condition").trim());
                flag = true;
            }
        }
        if (flag && fldName.getText().trim().equals("*")) {
            // 查询字段
            Connection con = null;
            Statement sts = null;
            StringBuffer sbMsg = new StringBuffer();

            try {
                con = DBUtils.getConnection(dataBaseAddress.getText(), databaseName.getText(), "writeuser", "writeuser");
                if (con != null) {
                    sts = con.createStatement();
                    // 遍历sql，批量插入
                    try {
                        String sql2 = "show columns from " + tblName.getText();
                        ResultSet rst = sts.executeQuery(sql2);
                        while (rst.next()) {
                            sbMsg.append(rst.getString(1) + ",");
                        }
                        if (sbMsg.toString().endsWith(",")) {
                            sbMsg.deleteCharAt(sbMsg.length() - 1);

                        }
                        fldName.setText(sbMsg.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        sbMsg.append(e.getMessage() + System.lineSeparator());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                sbMsg.append(e.getMessage() + System.lineSeparator());
            } finally {
                DBUtils.reaseConnection(con, sts, null, null);
            }
        }
    }

    @FXML
    protected void setValue(ActionEvent event) {
        try {

            MPageParamVo param = new MPageParamVo();
            param.setApplicationName(applicationName.getText());
            param.setTblName(tblName.getText());
            param.setDatabaseName(databaseName.getText());
            param.setFldName(fldName.getText());
            if (bizTime.getText().trim().length() == 0) {
                param.setBeginBizTime(new SimpleDateFormat("yyyy").format(new Date()) + "-01-01");
            } else {
                param.setBeginBizTime(bizTime.getText() + "-01-01");
            }

            if (pageSize.getText() != null && pageSize.getText().trim().length() > 0) {
                param.setPageSize(Integer.valueOf(pageSize.getText().trim()));
            }
            param.setCondition(condition.getText());
            param.setFldSort(fldSort.getText());
            FXMLTableViewControllerMain.s = new Gson().toJson(param);
            applicationName.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
