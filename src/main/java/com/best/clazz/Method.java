package com.best.clazz;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by rietsu on 2019/2/1.
 */
public class Method {

    private String returnClassName;
    private String returnClassSimpleName;
    private List<Field> parameters;
    private String methodName;

    public String getReturnClassSimpleName() {
        return returnClassSimpleName;
    }

    public void setReturnClassSimpleName(String returnClassSimpleName) {
        this.returnClassSimpleName = returnClassSimpleName;
    }

    public List<Field> getParameters() {
        return parameters;
    }

    public void setParameters(List<Field> parameters) {
        this.parameters = parameters;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getReturnClassName() {
        return returnClassName;
    }

    public void setReturnClassName(String returnClassName) {
        this.returnClassName = returnClassName;
    }

    public String getParametersStr() {
        return StringUtils.join(parameters.stream().map(this::getParamStr).collect(Collectors.toList()), " ,");
    }

    private String getParamStr(Field field) {
        return field.getFieldClassSimpleName() + " " + field.getFieldName();
    }
}
