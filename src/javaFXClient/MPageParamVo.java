/*
 * 文件名：MPageParamVo.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： MPageParamVo.java
 * 修改人：hongjian
 * 修改时间：2015年12月31日
 * 修改内容：新增
 */
package javaFXClient;

/**
 * 
 * @author hongjian
 */
public class MPageParamVo {
    /** . */
    private String ApplicationName = "";
    /** . */
    private int PageSize = 1;
    /** . */
    private int CurrentPage = 1;
    /** . */
    private String BeginBizTime;
    /** . */
    private String EndBizTime = BeginBizTime;
    /** . */
    private String DatabaseName;
    /** . */
    private String TblName;
    /** . */
    private String Id = "";
    /** . */
    private String FldName = "";
    /** . */
    private String condition = "";
    /** . */
    private String specialKey = "";
    /** . */
    private String hashKey = "";
    /** . */
    private String fldSort = "";
    /**
     * .
     */
    private String dist = "false";

    /**
     * 设置applicationName.
     * 
     * @return 返回applicationName.
     */
    public String getApplicationName() {
        return ApplicationName;
    }

    /**
     * 获取applicationName.
     * 
     * @param applicationName
     *            要设置的applicationName.
     */
    public void setApplicationName(String applicationName) {
        ApplicationName = applicationName;
    }

    /**
     * 设置pageSize.
     * 
     * @return 返回pageSize.
     */
    public int getPageSize() {
        return PageSize;
    }

    /**
     * 获取pageSize.
     * 
     * @param pageSize
     *            要设置的pageSize.
     */
    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    /**
     * 设置currentPage.
     * 
     * @return 返回currentPage.
     */
    public int getCurrentPage() {
        return CurrentPage;
    }

    /**
     * 获取currentPage.
     * 
     * @param currentPage
     *            要设置的currentPage.
     */
    public void setCurrentPage(int currentPage) {
        CurrentPage = currentPage;
    }

    /**
     * 设置beginBizTime.
     * 
     * @return 返回beginBizTime.
     */
    public String getBeginBizTime() {
        return BeginBizTime;
    }

    /**
     * 获取beginBizTime.
     * 
     * @param beginBizTime
     *            要设置的beginBizTime.
     */
    public void setBeginBizTime(String beginBizTime) {
        BeginBizTime = beginBizTime;
    }

    /**
     * 设置endBizTime.
     * 
     * @return 返回endBizTime.
     */
    public String getEndBizTime() {
        return EndBizTime;
    }

    /**
     * 获取endBizTime.
     * 
     * @param endBizTime
     *            要设置的endBizTime.
     */
    public void setEndBizTime(String endBizTime) {
        EndBizTime = endBizTime;
    }

    /**
     * 设置databaseName.
     * 
     * @return 返回databaseName.
     */
    public String getDatabaseName() {
        return DatabaseName;
    }

    /**
     * 获取databaseName.
     * 
     * @param databaseName
     *            要设置的databaseName.
     */
    public void setDatabaseName(String databaseName) {
        DatabaseName = databaseName;
    }

    /**
     * 设置tblName.
     * 
     * @return 返回tblName.
     */
    public String getTblName() {
        return TblName;
    }

    /**
     * 获取tblName.
     * 
     * @param tblName
     *            要设置的tblName.
     */
    public void setTblName(String tblName) {
        TblName = tblName;
    }

    /**
     * 设置id.
     * 
     * @return 返回id.
     */
    public String getId() {
        return Id;
    }

    /**
     * 获取id.
     * 
     * @param id
     *            要设置的id.
     */
    public void setId(String id) {
        Id = id;
    }

    /**
     * 设置fldName.
     * 
     * @return 返回fldName.
     */
    public String getFldName() {
        return FldName;
    }

    /**
     * 获取fldName.
     * 
     * @param fldName
     *            要设置的fldName.
     */
    public void setFldName(String fldName) {
        FldName = fldName;
    }

    /**
     * 设置condition.
     * 
     * @return 返回condition.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * 获取condition.
     * 
     * @param condition
     *            要设置的condition.
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * 设置specialKey.
     * 
     * @return 返回specialKey.
     */
    public String getSpecialKey() {
        return specialKey;
    }

    /**
     * 获取specialKey.
     * 
     * @param specialKey
     *            要设置的specialKey.
     */
    public void setSpecialKey(String specialKey) {
        this.specialKey = specialKey;
    }

    /**
     * 设置hashKey.
     * 
     * @return 返回hashKey.
     */
    public String getHashKey() {
        return hashKey;
    }

    /**
     * 获取hashKey.
     * 
     * @param hashKey
     *            要设置的hashKey.
     */
    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    /**
     * 设置fldSort.
     * 
     * @return 返回fldSort.
     */
    public String getFldSort() {
        return fldSort;
    }

    /**
     * 获取fldSort.
     * 
     * @param fldSort
     *            要设置的fldSort.
     */
    public void setFldSort(String fldSort) {
        this.fldSort = fldSort;
    }

    /**
     * 设置dist.
     * 
     * @return 返回dist.
     */
    public String isDist() {
        return dist;
    }

    /**
     * 获取dist.
     * 
     * @param dist
     *            要设置的dist.
     */
    public void setDist(String dist) {
        this.dist = dist;
    }

}
