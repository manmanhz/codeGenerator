package com.best.datamodal.java;

import java.util.List;
import java.util.Set;

import com.best.clazz.Field;
import com.best.clazz.Method;
import com.best.datamodal.DataModal;

/**
 * Created by rietsu on 2019/2/1.
 */
public interface JavaDataModal extends DataModal {

    /**
     * 要生成的Java类的包名
     *
     * @return
     */
    String getPackageName();

    /**
     * 要生成的Java类，需要引入的类。包含了注解、继承、实现、域变量等
     *
     * @return
     */
    Set<String> getImportClassNames();

    /**
     * Java类的注解
     *
     * @return
     */
    List<String> getAnnotations();

    /**
     * Java类名
     *
     * @return
     */
    String getClassSimpleName();

    /**
     * Java类继承的类名，也可用于接口的继承
     *
     * @return
     */
    String getExtendClassSimpleName();

    /**
     * Java类继承的泛型
     *
     * @return
     */
    String getExtendGenericClassSimpleName();

    /**
     * Java类实现的接口名
     *
     * @return
     */
    List<String> getImplementsClassSimpleNames();

    /**
     * 将Java类的实现接口名，组装成a, b, c的字符串返回
     *
     * @return
     */
    String getImplementsClassSimpleNamesStr();

    /**
     * Java类的域变量
     *
     * @return
     */
    List<Field> getFields();

    /**
     * Java类的方法
     *
     * @return
     */
    List<Method> getMethods();

    String getBasePackageLocation();

    String getExtendsClassName();
}
