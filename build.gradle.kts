// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    //Para especificar el plugin ksp de Kotlin, se debe usar una versión compatible con la versión
    //de kotlin para android (línea anterior) aquí: https://github.com/google/ksp/tags
    //Normalmente, la última estable que empiece por los mismos 3 dígitos. En este caso, 1.9.0
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
}