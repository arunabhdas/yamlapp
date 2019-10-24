buildscript {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0-alpha01")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.60-eap-25")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
}
