package com.best;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by rietsu on 2019/2/13.
 */
public class ConfigHelper {

    private static Configuration properties;

    static {
        try {
            properties = new PropertiesConfiguration("config.properties");
        } catch (ConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    private ConfigHelper() {}

    public static Configuration getInstance() throws ConfigurationException {
        if (properties == null) {
            properties = new PropertiesConfiguration("config.properties");
        }

        return properties;
    }

    public static String getModalClassName() {
        return getNotEmptyString("modal.class.name");
    }

    private static String getNotEmptyString(String propertyKey) {
        String modalClassName = properties.getString(propertyKey);
        if (modalClassName != null && !"".equals(modalClassName)) {
            return modalClassName;
        } else {
            throw new IllegalArgumentException(propertyKey + " can't empty.");
        }
    }

    public static String getModalClassBasePackage() {
        return getNotEmptyString("modal.class.package.base");
    }

    public static String getModalClassModuleName() {
        return getString("modal.class.module.name");
    }

    public static String getString(String propertyKey) {
        String propertiesString = properties.getString(propertyKey);
        return StringUtils.isEmpty(propertiesString) ? null : propertiesString;
    }

    public static String[] getStringArray(String propertyKey) {
        return properties.getStringArray(propertyKey);
    }

    public static String getCommonProjectPath() {
        return getProjectPath("project.path.common");
    }

    private static String getProjectPath(String projectPathKey) {
        String projectPath = getString(projectPathKey);
        if (StringUtils.isNotEmpty(projectPath)) {
            return projectPath;
        } else {
            return getProjectPath();
        }
    }

    public static String getProjectPath() {
        return getNotEmptyString("project.path");
    }

    public static String getDaoProjectPath() {
        return getProjectPath("project.path.dao");
    }

    public static String getServiceProjectPath() {
        return getProjectPath("project.path.service");
    }

    public static String getFacadeProjectPath() {
        return getProjectPath("project.path.facade");
    }

    public static String getMapperProjectPath() {
        return getProjectPath("project.path.mapper");
    }

    public static String getTemplateDir() {
        String templateDir = getString("template.dir");
        if (StringUtils.isEmpty(templateDir)) {
            templateDir = "/templates";
        }
        return templateDir;
    }
}
