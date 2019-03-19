package com.best.datamodal.java;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;

/**
 * Created by rietsu on 2019/2/2.
 */
public class ServiceJavaDataModal extends AbstractJavaDataModal implements JavaDataModal {

    public ServiceJavaDataModal(Class baseClass) {
        super(baseClass);
    }

    @Override
    protected String getProjectPath() {
        return ConfigHelper.getServiceProjectPath();
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
    public String getExtendsClassName() {
        return ConfigHelper.getString("service.class.extends");
    }

    @Override
    public String getBasePackageLocation() {
        return ConfigHelper.getString("service.class.package");
    }

    @Override
    public String getClassSimpleName() {
        return getBaseClass().getSimpleName() + "Service";
    }

    @Override
    public String getExtendClassSimpleName() {
        return null;
    }
}
