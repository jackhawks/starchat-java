package com.starchat.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

import java.nio.file.Paths;
import java.util.Collections;

/**
 * Code Generator
 *
 * @author Jack
 */
public class CodeGenerator {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/starchat", "jack", "123456")
            .schema("starchat");

    private static final String PROJECT_PATH = Paths.get(System.getProperty("user.dir")).toString();

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> builder
                        .author("Jack")
                        .outputDir(PROJECT_PATH + "/src/main/java")
                        .disableOpenDir()
                )
                .packageConfig(builder -> builder
                        .parent("com.starchat")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper"))
                )
                .strategyConfig(builder -> {
                            builder.entityBuilder()
                                    .javaTemplate("/templates/generator/entity.java")
                                    .enableFileOverride()
                                    .enableLombok()
                                    .disableSerialVersionUID()
                                    .enableChainModel()
                                    .enableActiveRecord();
                            builder.controllerBuilder()
                                    .template("/templates/generator/controller.java")
                                    .enableFileOverride()
                                    .enableRestStyle();
                            builder.serviceBuilder()
                                    .serviceTemplate("/templates/generator/service.java")
                                    .serviceImplTemplate("/templates/generator/serviceImpl.java")
                                    .enableFileOverride();
                            builder.mapperBuilder()
                                    .mapperTemplate("/templates/generator/mapper.java")
                                    .mapperXmlTemplate("/templates/generator/mapper.xml")
                                    .enableFileOverride();
                        }
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
