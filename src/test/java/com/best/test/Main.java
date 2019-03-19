package com.best.test;

import java.io.IOException;

import com.best.GroupGenerator;

import freemarker.template.TemplateException;

/**
 * Created by rietsu on 2019/2/1.
 */
public class Main {

    public static void main(String[] args) throws IOException, TemplateException, ClassNotFoundException {

        new GroupGenerator().generate();
    }
}
