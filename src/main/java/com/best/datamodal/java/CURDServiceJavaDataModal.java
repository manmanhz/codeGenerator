package com.best.datamodal.java;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.best.clazz.Field;
import com.best.clazz.Method;

/**
 * Created by rietsu on 2019/2/14.
 */
public class CURDServiceJavaDataModal extends ServiceJavaDataModal implements JavaDataModal {

    private VoJavaDataModal voJavaDataModal;
    private SoJavaDataModal soJavaDataModal;

    public CURDServiceJavaDataModal(Class baseClass) {
        super(baseClass);
        voJavaDataModal = new VoJavaDataModal(baseClass);
        soJavaDataModal = new SoJavaDataModal(baseClass);
    }

    @Override
    public Set<String> getImportClassNames() {
        Set<String> importClassNames = super.getImportClassNames();

        // method import
        List<Method> methods = getMethods();
        if (methods != null) {
            for (Method method : methods) {
                if (needImport(method.getReturnClassName())) {
                    importClassNames.add(method.getReturnClassName());
                }

                // method parameter import
                if (method.getParameters() == null || method.getParameters().isEmpty()) {
                    continue;
                }
                for (Field parameter : method.getParameters()) {
                    if (needImport(parameter.getFieldClassName())) {
                        importClassNames.add(parameter.getFieldClassName());
                    }
                }
            }
        }

        return importClassNames;
    }

    @Override
    public List<Method> getMethods() {
        // CRUD METHOD
        Method c = new Method();
        c.setParameters(Arrays.asList(new Field(voJavaDataModal.getClassName(), voJavaDataModal.getClassSimpleName(),
            StringUtils.uncapitalize(voJavaDataModal.getClassSimpleName()))));
        c.setReturnClassSimpleName("void");
        c.setMethodName("save");

        Method u = new Method();
        u.setParameters(Arrays.asList(new Field(voJavaDataModal.getClassName(), voJavaDataModal.getClassSimpleName(),
            StringUtils.uncapitalize(voJavaDataModal.getClassSimpleName()))));
        u.setReturnClassSimpleName("void");
        u.setMethodName("update");

        Method r = new Method();
        r.setParameters(Arrays.asList(new Field(Long.class.getName(), Long.class.getSimpleName(), "id")));
        r.setReturnClassSimpleName(voJavaDataModal.getClassSimpleName());
        r.setReturnClassName(voJavaDataModal.getClassName());
        r.setMethodName("find");

        Method d = new Method();
        d.setParameters(Arrays.asList(new Field(Long.class.getName(), voJavaDataModal.getClassSimpleName(), "id")));
        d.setReturnClassSimpleName("void");
        d.setMethodName("delete");

        return Arrays.asList(c, r, u, d);
    }
}
