package com.best.generator;

import java.io.IOException;

import com.best.datamodal.DataModal;
import com.best.datamodal.java.DaoJavaDataModal;

import freemarker.template.Template;

/**
 * Created by rietsu on 2019/2/15.
 */
public class DaoGenerator extends CodeGenerator {

    @Override
    protected Template getTemplate() throws IOException {
        return FreemarkerTemplateFactory.instance().getTemplate("interface");
    }

    @Override
    protected DataModal getDataModal() throws ClassNotFoundException {
        return new DaoJavaDataModal(getBaseClass());
    }
}
