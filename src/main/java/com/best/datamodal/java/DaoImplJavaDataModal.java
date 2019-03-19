package com.best.datamodal.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;

/**
 * Created by rietsu on 2019/2/1.
 */
public class DaoImplJavaDataModal extends AbstractJavaDataModal implements JavaDataModal {

    private String dynamicImplementClassName;
    private String dynamicImplementClassSimpleName;
    private DaoJavaDataModal daoJavaDataModal;

    public DaoImplJavaDataModal(Class baseClass) {
        super(baseClass);
        daoJavaDataModal = new DaoJavaDataModal(baseClass);
    }

    @Override
    public Set<String> getImportClassNames() {
        Set<String> importClassNames = new HashSet<>();
        if (StringUtils.isNotEmpty(getExtendsClassName())) {
            importClassNames.add(getExtendsClassName());
        }
        importClassNames.add(getBaseClass().getName());
        importClassNames.add(getDynamicImplementClassName());
        return importClassNames;
    }

    @Override
    public String getBasePackageLocation() {
        return ConfigHelper.getString("daoImpl.class.package");
    }

    @Override
    public String getExtendsClassName() {
        return ConfigHelper.getString("daoImpl.class.extends");
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
        return getBaseClass().getSimpleName() + "DaoImpl";
    }

    @Override
    public String getExtendGenericClassSimpleName() {
        return getBaseClass().getSimpleName();
    }

    @Override
    public List<String> getImplementsClassSimpleNames() {
        return Arrays.asList(getDynamicImplementClassSimpleName());
    }

    /**
     * 设置个性化的实现类，DaoImpl实现的Dao名，是在生成时才知道的。
     *
     */
    private String getDynamicImplementClassName() {
        return daoJavaDataModal.getClassName();
    }

    private String getDynamicImplementClassSimpleName() {
        return daoJavaDataModal.getClassName();
    }
}
