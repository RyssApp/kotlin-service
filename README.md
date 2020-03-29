# Kotlin Service template

## What to change
- Package name: `app.ryss.service`
- Main Service class: `app.ryss.service.core.Service` 
- Service Name: `app.ryss.service.core.Service#NAME`
- Gradle Main Class: `build.gradle.kts#application#mainClassName`
- Gradle output: `build.gradle.kts#tasks#shadowJar`
- Protobuf source: `build.gradle.kts#sourceSets##create#proto#srcDir`
- Gradle project name: `settings.gradle.kts#rootProject.name`
