plugins {
    java
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Modules
    implementation(project(":services"))
    implementation(project(":model"))

    // Spring Framework
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework:spring-test:5.2.6.RELEASE")

    // MyBatis and Postgres Database
    implementation("org.mybatis:mybatis:3.5.5")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
    implementation("org.postgresql:postgresql:42.2.14")

    // Unit Tests
    testImplementation("junit:junit:4.13.1")
}