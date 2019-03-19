package com.best.datamodal.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;
import com.best.clazz.Field;
import com.best.clazz.Method;

/**
 * Created by rietsu on 2019/2/1.
 */
public abstract class AbstractJavaDataModal implements JavaDataModal {

    public static final String PACKAGE_SEPARATE = ".";
    public static final String DIRECTORY_SEPARATE = "/";
    private Class baseClass;

    public AbstractJavaDataModal(Class baseClass) {
        this.baseClass = baseClass;
    }

    public Class getBaseClass() {
        return baseClass;
    }

    String getModuleName() {
        return ConfigHelper.getModalClassModuleName();
    }

    Set<String> getFieldImportClassNames() {
        Set<String> importClassNames = new HashSet<>();
        for (Field field : getFields()) {
            if (needImport(field.getFieldClassName())) {
                importClassNames.add(field.getFieldClassName());
            }
        }
        return importClassNames;
    }

    protected boolean needImport(String className) {
        return StringUtils.isNotEmpty(className) && ("java.util.Date".equals(className)
            || "java.math.BigDecimal".equals(className)
            || "java.util.List".equals(className)
            || !StringUtils.startsWith(className, "java"));
    }

    @Override
    public String getPackageName() {
        if (StringUtils.isEmpty(getModuleName())) {
            return getBasePackageLocation();
        } else {
            return getBasePackageLocation() + PACKAGE_SEPARATE + getModuleName();
        }
    }

    @Override
    public List<Field> getFields() {
        return new ArrayList<>();
    }

    @Override
    public String getImplementsClassSimpleNamesStr() {
        return StringUtils.join(getImplementsClassSimpleNames(), ",");
    }

    @Override
    public List<String> getImplementsClassSimpleNames() {
        return new ArrayList<>();
    }

    @Override
    public List<Method> getMethods() {
        return new ArrayList<>();
    }

    public String getClassName() {
        return getPackageName() + PACKAGE_SEPARATE + getClassSimpleName();
    }

    @Override
    public String getExtendClassSimpleName() {
        if (StringUtils.isEmpty(getExtendsClassName())) {
            return null;
        }
        String[] extendClassPackageNames = StringUtils.split(getExtendsClassName(), ".");
        return extendClassPackageNames[extendClassPackageNames.length - 1];
    }

    @Override
    public String getExtendGenericClassSimpleName() {
        return null;
    }

    @Override
    public Set<String> getImportClassNames() {
        return new HashSet<>();
    }

    @Override
    public List<String> getAnnotations() {
        return new ArrayList<>();
    }

    protected abstract String getProjectPath();

    @Override public String getOutputFileDirPath() {
        return getProjectPath() + StringUtils.replace(getPackageName(), PACKAGE_SEPARATE, DIRECTORY_SEPARATE);
    }

    @Override public String getOutputFilePath() {
        return getOutputFileDirPath() + DIRECTORY_SEPARATE + getClassSimpleName() + ".java";
    }
}
