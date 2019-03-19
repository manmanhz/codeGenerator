package com.best.generator;

import java.io.IOException;

import com.best.datamodal.DataModal;
import com.best.datamodal.java.DaoImplJavaDataModal;

import freemarker.template.Template;

/**
 * Created by rietsu on 2019/2/15.
 */
public class DaoImplGenerator extends CodeGenerator {

    @Override
    protected Template getTemplate() throws IOException {
        return FreemarkerTemplateFactory.instance().getTemplate("class");
    }

    @Override
    protected DataModal getDataModal() throws ClassNotFoundException {
        return new DaoImplJavaDataModal(getBaseClass());
    }
}
