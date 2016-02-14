/*
 * 文件名：DateVo.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： DateVo.java
 * 修改人：hongjian
 * 修改时间：2016年1月8日
 * 修改内容：新增
 */
package javaFXClient;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author hongjian
 */
public class DateVo {
    private int totalCount;
    private List<HashMap<String, String>> dataInfo;

    /**
     * 设置totalCount.
     * 
     * @return 返回totalCount.
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 获取totalCount.
     * 
     * @param totalCount
     *            要设置的totalCount.
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 设置dataInfo.
     * 
     * @return 返回dataInfo.
     */
    public List<HashMap<String, String>> getDataInfo() {
        return dataInfo;
    }

    /**
     * 获取dataInfo.
     * 
     * @param dataInfo
     *            要设置的dataInfo.
     */
    public void setDataInfo(List<HashMap<String, String>> dataInfo) {
        this.dataInfo = dataInfo;

    }
}