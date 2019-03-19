package com.best.datamodal.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;

/**
 * Created by rietsu on 2019/2/1.
 */
public class DaoJavaDataModal extends AbstractJavaDataModal implements JavaDataModal {

    public DaoJavaDataModal(Class baseClass) {
        super(baseClass);
    }

    @Override
    public Set<String> getImportClassNames() {
        Set<String> importClassNames = new HashSet<>();
        if (StringUtils.isNotEmpty(getExtendsClassName())) {
            importClassNames.add(getExtendsClassName());
        }
        importClassNames.add(getBaseClass().getName());
        return importClassNames;
    }

    @Override
    public String getBasePackageLocation() {
        return ConfigHelper.getString("dao.class.package");
    }

    @Override
    public String getExtendsClassName() {
        return ConfigHelper.getString("dao.class.extends");
    }

    @Override
    public List<String> getAnnotations() {
        return new ArrayList<>();
    }

    @Override
    protected String getProjectPath() {
        return ConfigHelper.getDaoProjectPath();
    }

    @Override
    public String getClassSimpleName() {
        return getBaseClass().getSimpleName() + "Dao";
    }

    @Override
    public String getExtendGenericClassSimpleName() {
        return getBaseClass().getSimpleName();
    }
}
