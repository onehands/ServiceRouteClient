/*
 * 文件名：FXMLTableViewControllerMain.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： FXMLTableViewControllerMain.java
 * 修改人：hongjian
 * 修改时间：2015年10月28日
 * 修改内容：新增
 */
package javaFXClient;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author hongjian
 */
public class FXMLTableViewControllerMain {
    @FXML
    private TextArea text;
    @FXML
    private TextField svc;
    @FXML
    private TextField method;
    @FXML
    private TextField serviceAddress;
    @FXML
    private TextField params;
    @FXML
    private TextField filePath;
    @FXML
    private AnchorPane layoutPane;
    @FXML
    private RadioButton rb;

    static String s = "";

    static String result = "";

    static List<String> valueList = null;

    @FXML
    protected void clearParam(ActionEvent event) {
        try {
            svc.setText("");
            method.setText("");
            serviceAddress.setText("");
            params.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            text.appendText(e.getCause().getMessage() + System.lineSeparator());
        }
    }

    @FXML
    protected void clearText(ActionEvent event) {
        try {
            text.setText("");
        } catch (Exception e) {
            e.printStackTrace();
            text.appendText(e.getCause().getMessage() + System.lineSeparator());
        }
    }

    @FXML
    protected void chooseFile(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            File result = fileChooser.showOpenDialog(layoutPane.getScene().getWindow());
            if (result != null) {
                filePath.setText(result.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            text.appendText(e.getCause().getMessage() + System.lineSeparator());
        }
    }

    @FXML
    protected void pageService(ActionEvent event) {
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        // FXMLLoader f = new FXMLLoader();
                        Object c = FXMLLoader.load(getClass().getResource("FXML_tableview_Page.fxml"));
                        root = (Parent) c;
                        // FXMLTableViewControllerMain c2 =
                        // (FXMLTableViewControllerMain) f.getController();
                        // c2.pageSize.setText("20");
                        s = params.getText().trim();
                        if (s != null && s.length() > 0) {
                            MPageParamVo param = new Gson().fromJson(s, MPageParamVo.class);
                            TextField t = (TextField) root.lookup("#pageSize");
                            t.setText(String.valueOf(param.getPageSize()));
                            TextField applicationName2 = (TextField) root.lookup("#applicationName");
                            TextField databaseName2 = (TextField) root.lookup("#databaseName");
                            TextField tblName2 = (TextField) root.lookup("#tblName");
                            TextField fldName2 = (TextField) root.lookup("#fldName");
                            TextField condition2 = (TextField) root.lookup("#condition");
                            TextField fldSort2 = (TextField) root.lookup("#fldSort");
                            TextField bizTime = (TextField) root.lookup("#bizTime");
                            applicationName2.setText(String.valueOf(param.getApplicationName()));
                            databaseName2.setText(String.valueOf(param.getDatabaseName()));
                            tblName2.setText(String.valueOf(param.getTblName()));
                            fldName2.setText(String.valueOf(param.getFldName()));
                            condition2.setText(String.valueOf(param.getCondition()));
                            fldSort2.setText(String.valueOf(param.getFldSort()));
                            if (param.getBeginBizTime().length() == 0) {
                                bizTime.setText(new SimpleDateFormat("yyyy").format(new Date()));
                            } else {
                                bizTime.setText(param.getBeginBizTime().split("-")[0]);
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        text.appendText(e.getCause().getMessage() + System.lineSeparator());
                    }
                    Scene scene = new Scene(root, 400, 400);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("设置查询信息");
                    stage.setScene(scene);
                    EventHandler<WindowEvent> paramT = new EventHandler<WindowEvent>() {

                        @Override
                        public void handle(WindowEvent paramT) {
                            params.setText(s);
                            if (serviceAddress.getText().trim().length() == 0) {
                                serviceAddress.setText("http://172.16.2.102:4404/Better517na.CommonPagerService.WebHost/JavaPager.asmx?wsdl ");
                            }
                            method.setText("javaJosonPager");
                            svc.setText("com.better517na.pagerService.JavaPagerSoap");
                            filePath.setText("com.better517na.pagerService.jar");
                        }
                    };
                    stage.setOnHiding(paramT);
                    stage.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            text.appendText(e.getCause().getMessage() + System.lineSeparator());
        }
    }

    @FXML
    protected void invokService(ActionEvent event) {
        try {
            String svcStr = svc.getText();
            String methodStr = method.getText();
            String serviceAddressStr = serviceAddress.getText();
            String paramsStr = params.getText();
            String[] paramArr = paramsStr.split("\\|\\|");

            if (svcStr == null || svcStr.length() == 0) {
                text.appendText("契约名不能为空" + System.lineSeparator());
                return;
            }
            if (methodStr == null || methodStr.length() == 0) {
                text.appendText("方法名不能为空" + System.lineSeparator());
                return;
            }
            if (serviceAddressStr == null || serviceAddressStr.length() == 0) {
                text.appendText("服务地址不能为空" + System.lineSeparator());
                return;
            }

            // 提前访问，校验服务是否正常
            try {
                String str = serviceAddressStr.trim();
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet hp = new HttpGet(str);

                RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(2000).build();
                hp.setConfig(requestConfig);
                HttpResponse result = httpclient.execute(hp);
            } catch (Exception e) {
                e.printStackTrace();
                if (e.getMessage().contains("connect")) {
                    text.appendText(System.lineSeparator() + "服务挂了，请手动切换服务地址" + System.lineSeparator());
                    return;
                }
            }
            Class<?> serviceClass = null;
            URLClassLoader myClassLoader1 = null;
            try {
                myClassLoader1 = new URLClassLoader(new URL[] { new URL("file:" + filePath.getText()) }, Thread.currentThread().getContextClassLoader());
                serviceClass = myClassLoader1.loadClass(svcStr.trim());
            } catch (Exception e) {
                e.printStackTrace();
                text.appendText(e.getCause().getMessage() + System.lineSeparator());
                text.appendText("加载类:" + svcStr + "异常,请加入相关jar包" + System.lineSeparator());
                if (myClassLoader1 != null) {
                    myClassLoader1.close();
                }
                return;
            }
            Method[] methods = serviceClass.getMethods();
            Method matchedMethod = matchMethod(methodStr, methods);
            if (matchedMethod == null) {
                text.appendText("方法名不存在" + System.lineSeparator());
                if (myClassLoader1 != null) {
                    myClassLoader1.close();
                }
                return;
            }
            Object[] paramsFinal = new Object[0];
            if (paramsStr.trim().length() > 0) {
                paramsFinal = convertParam(matchedMethod, paramArr);
            }
            Object o = methodInvoke(serviceClass, matchedMethod, paramsFinal, serviceAddressStr);
            if (o.getClass() == String.class && rb.isSelected()) {
                o = JsonUtil.formatJson((String) o);
            }
            text.appendText(System.lineSeparator() + String.valueOf(o) + System.lineSeparator());
            if (myClassLoader1 != null) {
                myClassLoader1.close();
            }
            result = String.valueOf(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加方法注释.
     * 
     * @param matchedMethod
     * @param paramArr
     * @return .
     */
    private Object[] convertParam(Method matchedMethod, String[] paramArr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Class<?>[] types = matchedMethod.getParameterTypes();
        Object[] obj = new Object[paramArr.length];
        for (int i = 0; i < types.length; i++) {
            Class<?> class1 = types[i];
            if (class1 == Integer.class) {
                obj[i] = Integer.valueOf(paramArr[i]);
            } else if (class1 == Date.class) {
                obj[i] = sdf.format(paramArr[i]);
            } else if (class1 == int.class) {
                obj[i] = Integer.valueOf(paramArr[i]).intValue();
            } else if (class1 == String.class) {
                obj[i] = paramArr[i];
            }

        }
        return obj;
    }

    /**
     * 添加方法注释.
     * 
     * @param methodStr
     * @param methods
     *            .
     * @return
     */
    private Method matchMethod(String methodStr, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(methodStr)) {
                return method;
            }
        }
        return null;
    }

    private Object methodInvoke(Class<?> t, Method method, Object[] objects, String serviceAddressStr) {
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setAddress(serviceAddressStr);// 172.16.7.202:8093
        factoryBean.setServiceClass(t);
        Object o = factoryBean.create();
        try {
            switch (objects.length)
                {
                case 0:
                    return method.invoke(o);
                case 1:
                    return method.invoke(o, objects[0]);
                case 2:
                    return method.invoke(o, objects[0], objects[1]);
                case 3:
                    return method.invoke(o, objects[0], objects[1], objects[2]);
                case 4:
                    return method.invoke(o, objects[0], objects[1], objects[2], objects[3]);
                case 5:
                    return method.invoke(o, objects[0], objects[1], objects[2], objects[3], objects[4]);
                default:
                    break;
                }
            return null;
        } catch (Exception e) {
            // e.printStackTrace();
            text.appendText("调用异常" + System.lineSeparator());
            text.appendText(e.getCause().getMessage() + System.lineSeparator());
            return null;
        }
    }

    @FXML
    public void importData(ActionEvent event) {

        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        // FXMLLoader f = new FXMLLoader();
                        Object c = FXMLLoader.load(getClass().getResource("FXML_tableview_Import.fxml"));
                        root = (Parent) c;
                        // FXMLTableViewControllerMain c2 =
                        // (FXMLTableViewControllerMain) f.getController();
                        // c2.pageSize.setText("20");
                        s = params.getText().trim();
                        if (s != null && s.length() > 0) {
                            MPageParamVo param = new Gson().fromJson(s, MPageParamVo.class);
                            TextField databaseName = (TextField) root.lookup("#databaseName");
                            databaseName.setText(String.valueOf(param.getDatabaseName().trim()));
                            TextField tblName2 = (TextField) root.lookup("#tblName");
                            TextField fldName2 = (TextField) root.lookup("#fldName");
                            TextField value = (TextField) root.lookup("#value");
                            value.setText(getValue(param, result));
                            tblName2.setText(String.valueOf(param.getTblName()));
                            fldName2.setText(String.valueOf(param.getFldName().trim()));

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        text.appendText(e.getCause().getMessage() + System.lineSeparator());
                    }
                    Scene scene = new Scene(root, 400, 400);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("设置导入信息");
                    stage.setScene(scene);
                    // EventHandler<WindowEvent> paramT = new
                    // EventHandler<WindowEvent>() {
                    //
                    // @Override
                    // public void handle(WindowEvent paramT) {
                    // params.setText(s);
                    // serviceAddress.setText("http://172.16.2.102:4404/Better517na.CommonPagerService.WebHost/JavaPager.asmx?wsdl
                    // ");
                    // method.setText("javaJosonPager");
                    // svc.setText("com.better517na.pagerService.JavaPagerSoap");
                    // filePath.setText("com.better517na.pagerService.jar");
                    // }
                    // };
                    // stage.setOnHiding(paramT);
                    stage.show();
                }

                private String getValue(MPageParamVo param, String result) {
                    // TODO Auto-generated method stub
                    Gson g = new Gson();
                    DateVo d = g.fromJson(result, DateVo.class);
                    List<HashMap<String, String>> list = d.getDataInfo();
                    valueList = new ArrayList<String>();
                    String[] fldArr = param.getFldName().trim().split(",");
                    for (HashMap<String, String> hashMap : list) {
                        StringBuffer sb = new StringBuffer();

                        for (String string : fldArr) {
                            sb.append("'" + hashMap.get(string.trim()) + "',");
                        }

                        if (sb.toString().endsWith(",")) {
                            sb.deleteCharAt(sb.toString().length() - 1);
                        }
                        valueList.add(sb.toString());
                    }
                    return valueList.get(0);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            text.appendText(e.getCause().getMessage() + System.lineSeparator());
        }

    }

}
