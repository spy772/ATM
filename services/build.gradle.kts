plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // Modules
    implementation(project(":model"))

    // Spring Framework
    implementation("org.springframework:spring-beans:5.2.6.RELEASE")
    implementation("org.springframework:spring-context:5.2.6.RELEASE")

    // MyBatis and Postgres Database
    implementation("org.mybatis:mybatis:3.5.5")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.3")
    implementation("org.postgresql:postgresql:42.2.14")
}