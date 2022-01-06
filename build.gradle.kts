import base_plugin.configureSubProject

buildscript {
    repositories.applyDefault()
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpathAll(PluginDependencies.implementation)
    }
}

allprojects {
    repositories.applyDefault()
}
project.configureSubProject()
