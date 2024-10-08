package com.starchat.common.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.starchat.controller.BaseController;
import com.starchat.entity.BaseEntity;
import org.apache.ibatis.type.JdbcType;

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

    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> builder
                        .author("Jack")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .disableOpenDir()
                )
                .dataSourceConfig(builder -> builder.typeConvertHandler((_, typeRegistry, metaInfo) -> {
                    if (metaInfo.getJdbcType() == JdbcType.TINYINT) {
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> builder
                        .parent("com.starchat")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, "src/main/resources/mapper"))
                )
                .strategyConfig(builder -> {
                            builder.entityBuilder()
                                    .enableFileOverride()
                                    .javaTemplate("/templates/generator/entity.java")
                                    .superClass(BaseEntity.class)
                                    .disableSerialVersionUID()
//                                    .enableActiveRecord()
                                    .enableChainModel()
                                    .enableLombok();
                            builder.controllerBuilder()
//                                    .enableFileOverride()
                                    .template("/templates/generator/controller.java")
                                    .superClass(BaseController.class)
                                    .enableRestStyle();
                            builder.serviceBuilder()
//                                    .enableFileOverride()
                                    .serviceTemplate("/templates/generator/service.java")
                                    .serviceImplTemplate("/templates/generator/serviceImpl.java")
                                    .formatServiceFileName("%sService")
                                    .formatServiceImplFileName("%sServiceImp");
                            builder.mapperBuilder()
//                                    .enableFileOverride()
                                    .enableBaseResultMap()
                                    .enableBaseColumnList()
                                    .mapperTemplate("/templates/generator/mapper.java")
                                    .mapperXmlTemplate("/templates/generator/mapper.xml");
                        }
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
