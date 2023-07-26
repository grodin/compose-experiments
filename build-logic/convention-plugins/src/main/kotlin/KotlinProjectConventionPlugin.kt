import com.omricat.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.base.TestingExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

@Suppress("unused", "UnstableApiUsage")
public class KotlinProjectConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.jvm")
        apply(KtfmtConventionPlugin::class)
        apply(DetektConventionPlugin::class)
      }

      configure<KotlinJvmProjectExtension> { explicitApi() }

      tasks.withType<KotlinCompilationTask<*>>().configureEach {
        it.compilerOptions.apply { languageVersion.set(KotlinVersion.KOTLIN_2_0) }
      }
      configure<TestingExtension> {
        suites.named<JvmTestSuite>("test") {
          useKotlinTest(libs.findVersion("kotlin").map { it.preferredVersion }.get())
        }
      }
    }
  }
}
