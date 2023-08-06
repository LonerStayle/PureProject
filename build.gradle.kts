import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
}

group = "me.dlwls"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("com.google.dagger:dagger:2.47")
    annotationProcessor("com.google.dagger:dagger-compiler:2.47")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {

    kotlinOptions.apply {
        jvmTarget = "1.8"
        allWarningsAsErrors = false
        freeCompilerArgs = listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=kotlinx.coroutines.FlowPreview",
            "-Xopt-in=kotlinx.coroutines.DelicateCoroutinesApi",
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
            "-Xopt-in=kotlin.Experimental"
        )

    }
}

