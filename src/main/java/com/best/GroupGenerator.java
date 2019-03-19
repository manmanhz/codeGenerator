package com.best;

import com.best.generator.CodeGenerator;
import com.best.generator.DaoGenerator;
import com.best.generator.DaoImplGenerator;
import com.best.generator.ServiceGenerator;
import com.best.generator.ServiceImplGenerator;
import com.best.generator.SoGenerator;
import com.best.generator.VoGenerator;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import freemarker.template.TemplateException;

/**
 * Created by rietsu on 2019/2/15.
 */
public class GroupGenerator {

    private Map<String, CodeGenerator> generatorMap = new HashMap<>();

    public GroupGenerator() {

        generatorMap.put("vo", new VoGenerator());
        generatorMap.put("so", new SoGenerator());
        generatorMap.put("dao", new DaoGenerator());
        generatorMap.put("daoImpl", new DaoImplGenerator());
        generatorMap.put("service", new ServiceGenerator());
        generatorMap.put("serviceImpl", new ServiceImplGenerator());
    }

    public void generate() throws TemplateException, IOException, ClassNotFoundException {
        String generatorSelective = ConfigHelper.getString("generator.selective");

        if (StringUtils.isEmpty(generatorSelective)) {
            return;
        }

        if ("all".equalsIgnoreCase(generatorSelective)) {
            Collection<CodeGenerator> allGenerators = generatorMap.values();
            for (CodeGenerator codeGenerator : allGenerators) {
                codeGenerator.generate();
            }
        } else {
            customGenerate(generatorSelective);
        }
    }

    private void customGenerate(String generatorSelective)
        throws TemplateException, IOException, ClassNotFoundException {
        String[] generators = StringUtils.split(generatorSelective, ",");
        for (String generatorName : generators) {

            CodeGenerator generator = generatorMap.get(generatorName);
            if (generator != null) {
                generator.generate();
            } else {
                // 不是默认的生成器，根据类名初始化一个
                try {
                    Class<CodeGenerator> generatorClazz = (Class<CodeGenerator>) Class.forName(generatorName);
                    generatorClazz.newInstance().generate();
                } catch (Exception ex) {
                    throw new ClassNotFoundException("generator.selective 配置项有误，" + generatorName);
                }
            }
        }
    }
}
