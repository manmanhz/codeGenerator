package com.best.clazz;

/**
 * Created by rietsu on 2019/2/1.
 */
public class Field {

    private String fieldClassSimpleName;
    private String fieldName;
    private String fieldClassName;


    public Field(String fieldClassName, String fieldClassSimpleName, String fieldName) {
        this.fieldClassName = fieldClassName;
        this.fieldClassSimpleName = fieldClassSimpleName;
        this.fieldName = fieldName;
    }

    public String getFieldClassSimpleName() {
        return fieldClassSimpleName;
    }

    public void setFieldClassSimpleName(String fieldClassSimpleName) {
        this.fieldClassSimpleName = fieldClassSimpleName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldClassName() {
        return fieldClassName;
    }

    public void setFieldClassName(String fieldClassName) {
        this.fieldClassName = fieldClassName;
    }
}
