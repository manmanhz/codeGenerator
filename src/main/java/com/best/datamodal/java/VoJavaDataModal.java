package com.best.datamodal.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import java.util.Set;
import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;
import com.best.clazz.Field;

/**
 * Created by rietsu on 2019/2/1.
 */
public class VoJavaDataModal extends AbstractJavaDataModal implements JavaDataModal {

    public VoJavaDataModal(Class baseClass) {
        super(baseClass);
    }

    @Override
    public Set<String> getImportClassNames() {

        Set<String> importClassNames = new HashSet<>();
        importClassNames.add("lombok.Data");
        importClassNames.addAll(getFieldImportClassNames());

        if (StringUtils.isNotEmpty(getExtendsClassName())) {
            importClassNames.add(getExtendsClassName());
        }
        return importClassNames;
    }

    @Override
    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        for (java.lang.reflect.Field field : getBaseClass().getDeclaredFields()) {
            fields.add(new Field(field.getType().getName(), field.getType().getSimpleName(), field.getName()));
        }
        return fields;
    }

    @Override
    public String getBasePackageLocation() {
        return ConfigHelper.getString("vo.class.package");
    }

    @Override
    public String getExtendsClassName() {
        return ConfigHelper.getString("vo.class.extends");
    }

    @Override
    public List<String> getAnnotations() {
        return Arrays.asList("Data");
    }

    @Override
    protected String getProjectPath() {
        return ConfigHelper.getCommonProjectPath();
    }

    @Override
    public String getClassSimpleName() {
        return getBaseClass().getSimpleName() + "Vo";
    }

    @Override
    public String getExtendGenericClassSimpleName() {
        return null;
    }

}
