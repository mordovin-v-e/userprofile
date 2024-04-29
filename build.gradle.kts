import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.openapi.generator") version "7.4.0"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
    kotlin("kapt") version "1.9.23"
}

group = "ru.otus"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

extra["springdocOpenapiVersion"] = "2.5.0"
extra["mapstructVersion"] = "1.5.5.Final"
extra["liquibaseVersion"] = "4.27.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Mapping
    implementation("org.mapstruct:mapstruct:${property("mapstructVersion")}")
    kapt("org.mapstruct:mapstruct-processor:${property("mapstructVersion")}")

    // DB
    implementation("org.liquibase:liquibase-core:${property("liquibaseVersion")}")
    runtimeOnly("org.postgresql:postgresql")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocOpenapiVersion")}")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }

    dependsOn(tasks.openApiGenerate)
}

springBoot {
    mainClass.set("ru.otus.userprofile.UserProfileApplicationKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val oasPackage = "$group.${rootProject.name}"

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/src/main/resources/static/api.yaml")
    apiPackage.set("$oasPackage.api")
    invokerPackage.set("$oasPackage.invoker")
    modelPackage.set("$oasPackage.dto")
    packageName.set(oasPackage)
    typeMappings.set(mapOf("DateTime" to "java.time.LocalDateTime"))
    configOptions.set(
        mapOf(
            "useSpringBoot3" to true.toString(),
            "delegatePattern" to true.toString(),
            "dateLibrary" to "java8",
            "enumPropertyNaming" to "UPPERCASE",
            "exceptionHandler" to false.toString(),
            "gradleBuildFile" to false.toString(),
            "modelMutable" to true.toString()
        )
    )
}

sourceSets {
    named("main") {
        java.srcDir(openApiGenerate.outputDir)
    }
}