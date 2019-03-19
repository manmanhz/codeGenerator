package com.best.test.modal;

import com.best.clazz.DbInfo;
import java.util.Date;
import java.util.List;

/**
 * Created by rietsu on 2019/1/29.
 */
@DbInfo(tableName = "tbl_test", seqName = "tbl_test_seq")
public class Test {

    private Long id;

    /**
     * 成本中心
     */
    private String costCenter;
    /**
     * 成本中心
     */
    private String costCenterCode;
    /**
     * 说明
     */
    private String remark;

    /**
     * 业务单据号
     */
    private String referCode;

    /**
     * 业务单据号
     */
    private Long referId;

    private Date createdTime;

    private List<String> strList;
}
