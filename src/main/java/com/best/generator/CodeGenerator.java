package com.best.generator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.best.ConfigHelper;
import com.best.datamodal.DataModal;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by rietsu on 2019/2/1.
 */
public abstract class CodeGenerator {

    protected Class getBaseClass() throws ClassNotFoundException {
        String basePackageName = ConfigHelper.getModalClassBasePackage();
        String moduleName = ConfigHelper.getModalClassModuleName();
        String classSimpleName = ConfigHelper.getModalClassName();

        if (StringUtils.isEmpty(classSimpleName)) {
            throw new RuntimeException("基础Modal类名不能为空");
        }
        String className = StringUtils.EMPTY;
        if (StringUtils.isNotEmpty(basePackageName)) {
            className += basePackageName + ".";
        }
        if (StringUtils.isNotEmpty(moduleName)) {
            className += moduleName + ".";
        }
        if (StringUtils.isNotEmpty(classSimpleName)) {
            className += classSimpleName;
        }

        return Class.forName(className);
    }

    public void generate() throws IOException, TemplateException, ClassNotFoundException {
        // file path：项目路径+包路径+文件名
        File javaFilePath = new File(getDataModal().getOutputFileDirPath());
        javaFilePath.mkdirs();

        Map root = new HashMap();
        root.put("dataModal", getDataModal());

//         Writer out = new FileWriter(getDataModal().getOutputFilePath());
        Writer out = new PrintWriter(System.out);
        getTemplate().process(root, out);
        System.out.println(getDataModal().getOutputFilePath());
    }

    protected abstract DataModal getDataModal() throws ClassNotFoundException;

    protected abstract Template getTemplate() throws IOException;
}
