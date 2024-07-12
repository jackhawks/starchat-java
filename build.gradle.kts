plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.starchat"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("com.mysql:mysql-connector-j:9.0.0")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter:3.5.7")
    implementation("com.baomidou:mybatis-plus-generator:3.5.7")
    implementation("org.freemarker:freemarker:2.3.33")
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.1")
    implementation("com.github.whvcse:easy-captcha:1.6.2")
    implementation("org.openjdk.nashorn:nashorn-core:15.4")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.apache.commons:commons-collections4:4.5.0-M2")
    implementation("commons-io:commons-io:2.16.1")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
