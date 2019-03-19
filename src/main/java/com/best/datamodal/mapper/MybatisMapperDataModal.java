package com.best.datamodal.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.best.ConfigHelper;
import com.best.clazz.DbInfo;
import com.best.datamodal.DataModal;
import com.best.datamodal.java.AbstractJavaDataModal;
import com.best.datamodal.java.DaoJavaDataModal;

/**
 * Created by rietsu on 2019/2/2.
 */
public class MybatisMapperDataModal implements DataModal {

    private static final String PROJECT_PATH = "/Users/rietsu/code/800best/myCodeGenerator/src/resources/mapper/";
    private Class baseClass;

    public MybatisMapperDataModal(Class baseClass) {
        this.baseClass = baseClass;
    }

    public List<String> getSqlColumns() {
        List<String> sqlColumnNames = new ArrayList<>();

        for (Field field : baseClass.getDeclaredFields()) {
            sqlColumnNames.add(converColumnName(field.getName()));
        }

        return sqlColumnNames;
    }

    private String converColumnName(String name) {
        name = name.trim();
        StringBuffer retName = new StringBuffer();
        retName.append(name.charAt(0));
        for (int i = 1; i < name.length(); i++) {

            if ((name.charAt(i) < 91 && name.charAt(i) > 64)) {
                retName.append("_");
            }
            retName.append(name.charAt(i));
        }
        return retName.toString().toLowerCase();
    }

    public List<String> getJavaFields() {
        List<String> javaFields = new ArrayList<>();

        for (Field field : baseClass.getDeclaredFields()) {
            javaFields.add(field.getName());
        }

        return javaFields;
    }

    public String getSequenceName() {
        String sequenceName = "";
        if (baseClass.isAnnotationPresent(DbInfo.class)) {
            sequenceName = ((DbInfo) baseClass.getAnnotation(DbInfo.class)).seqName();
        } else {
            sequenceName = getTableName() + "_SEQ";
        }
        return sequenceName.toUpperCase();
    }

    public String getTableName() {
        String tableName = "";
        if (baseClass.isAnnotationPresent(DbInfo.class)) {
            tableName = ((DbInfo) baseClass.getAnnotation(DbInfo.class)).tableName();
        } else {
            tableName = converColumnName(baseClass.getSimpleName());
        }
        return tableName.toUpperCase();
    }

    public List<String> getConditions() {
        List<String> ifConditions = new ArrayList<>();

        for (Field field : baseClass.getDeclaredFields()) {

            if (String.class.equals(field.getType())) {
                ifConditions.add(field.getName() + " != null and " + field.getName() + " != ''");
            } else if (List.class.equals(field.getType())) {
                ifConditions.add(field.getName() + " != null and " + field.getName() + ".size() > 0");
            } else {
                ifConditions.add(field.getName() + " != null");
            }
        }

        return ifConditions;
    }

    public String getMapperNamespace() {
        DaoJavaDataModal daoJavaDataModal = new DaoJavaDataModal(baseClass);
        return daoJavaDataModal.getPackageName() + AbstractJavaDataModal.PACKAGE_SEPARATE
            + daoJavaDataModal.getClassSimpleName();
    }

    public String getParameterAliasBo() {
        return baseClass.getSimpleName();
    }

    public String getParameterAliasVo() {
        return baseClass.getSimpleName() + "Vo";
    }

    public String getParameterAliasSo() {
        return baseClass.getSimpleName() + "So";
    }

    @Override
    public String getOutputFileDirPath() {
        return getProjectPath();
    }

    private String getProjectPath() {
        return ConfigHelper.getMapperProjectPath();
    }

    @Override
    public String getOutputFilePath() {
        return getProjectPath() + StringUtils.uncapitalize(baseClass.getSimpleName()) + "Mapper.xml";
    }
}
