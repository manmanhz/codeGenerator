package com.best.generator;

import com.best.ConfigHelper;
import java.io.IOException;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * Created by rietsu on 2019/2/15.
 */
public class FreemarkerTemplateFactory {

    private Configuration freemarkerConfiguration;

    private static FreemarkerTemplateFactory freemarkerTemplateFactory = new FreemarkerTemplateFactory();

    private FreemarkerTemplateFactory() {

        // init freemarker
        freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_27);
        TemplateLoader ldr = new ClassTemplateLoader(this.getClass(), ConfigHelper.getTemplateDir());
        freemarkerConfiguration.setTemplateLoader(ldr);
        // freemarkerConfiguration.setDirectoryForTemplateLoading(new File(ConfigHelper.getTemplateDir()));
        freemarkerConfiguration.setDefaultEncoding("UTF-8");
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        freemarkerConfiguration.setLogTemplateExceptions(false);
        freemarkerConfiguration.setWrapUncheckedExceptions(true);
    }

    public static FreemarkerTemplateFactory instance() {
        return freemarkerTemplateFactory;
    }

    public Template getTemplate(String template) throws IOException {
        if ("class".equals(template)) {
            return getClassTemplate();
        } else if ("interface".equals(template)) {
            return getInterfaceTemplate();
        } else if ("mapper".equals(template)) {
            return getMapperTemplate();
        } else {
            return getTemplateByName(template);
        }
    }

    private Template getTemplateByName(String tempalteName) throws IOException {
        return freemarkerConfiguration.getTemplate(tempalteName);
    }

    private Template getClassTemplate() throws IOException {
        return freemarkerConfiguration.getTemplate("class.ftlh");
    }

    private Template getInterfaceTemplate() throws IOException {
        return freemarkerConfiguration.getTemplate("interface.ftlh");
    }

    private Template getMapperTemplate() throws IOException {
        return freemarkerConfiguration.getTemplate("mapper/mapper.ftlh");
    }

}
