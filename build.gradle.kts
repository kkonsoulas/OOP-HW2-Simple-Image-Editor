plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    java
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf("src"))
        }
    }
}


application{
    mainClass.set("ce326/hw2/ImageProcessing2")
}

val appTUI = "ce326.hw2.ImageProcessing2"
val appGUI = "ce326.hw2.ImageProcessing"

// Task to create the JAR for the first application
tasks.register<Jar>("jarTUI") {
    dependsOn(tasks.getByName("classes"))
    archiveBaseName.set("appTUI")
    manifest {
        attributes["Main-Class"] = appTUI
    }
    from(sourceSets.main.get().output)
}

// Task to create the JAR for the second application
tasks.register<Jar>("jarGUI") {
    dependsOn(tasks.getByName("classes"))
    archiveBaseName.set("appGUI")
    manifest {
        attributes["Main-Class"] = appGUI
    }
    from(sourceSets.main.get().output)
}



// Task to run the first application
tasks.register<JavaExec>("appTUI") {
    group = "application"
    mainClass.set(appTUI)
    classpath = sourceSets.main.get().runtimeClasspath
    standardInput = System.`in`
}

// Task to run the second application
tasks.register<JavaExec>("appGUI") {
    group = "application"
    mainClass.set(appGUI)
    classpath = sourceSets.main.get().runtimeClasspath
    standardInput = System.`in`
}