plugins {
    alias(libs.plugins.convention.kotlin.jvm)
    application
}

dependencies {}

application {
    // Define the main class for the application.
    mainClass.set("com.omricat.experiments.compose.app.AppKt")
}
