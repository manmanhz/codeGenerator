package com.best.datamodal.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;

/**
 * Created by rietsu on 2019/2/2.
 */
public class ServiceImplJavaDataModal extends AbstractJavaDataModal implements JavaDataModal {

    private String dynamicImplementClassName;
    private String dynamicImplementClassSimpleName;
    private ServiceJavaDataModal serviceJavaDataModal;

    public ServiceImplJavaDataModal(Class baseClass) {
        super(baseClass);

        serviceJavaDataModal = new ServiceJavaDataModal(baseClass);
        setDynamicImplementClassName(serviceJavaDataModal.getClassName());
        setDynamicImplementClassSimpleName(serviceJavaDataModal.getClassSimpleName());
    }

    @Override
    public Set<String> getImportClassNames() {
        Set<String> importClassNames = new HashSet<>();
        if (StringUtils.isNotEmpty(getExtendsClassName())) {
            importClassNames.add(getExtendsClassName());
        }
        return importClassNames;
    }

    @Override
    protected String getProjectPath() {
        return ConfigHelper.getServiceProjectPath();
    }

    @Override
    public String getClassSimpleName() {
        return getBaseClass().getSimpleName() + "ServiceImpl";
    }

    @Override
    public List<String> getImplementsClassSimpleNames() {
        return Arrays.asList(dynamicImplementClassSimpleName);
    }

    @Override
    public String getBasePackageLocation() {
        return ConfigHelper.getString("serviceImpl.class.package");
    }

    @Override
    public String getExtendsClassName() {
        return ConfigHelper.getString("serviceImpl.class.extends");
    }

    public void setDynamicImplementClassName(String dynamicImplementClassName) {
        this.dynamicImplementClassName = dynamicImplementClassName;
    }

    public void setDynamicImplementClassSimpleName(String dynamicImplementClassSimpleName) {
        this.dynamicImplementClassSimpleName = dynamicImplementClassSimpleName;
    }
}
