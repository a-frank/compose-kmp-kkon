import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
	alias(libs.plugins.multiplatform)
	alias(libs.plugins.compose)
}

kotlin {
	jvm()

	sourceSets {
		getByName("jvmMain") {
			dependencies {
				implementation(compose.desktop.currentOs)
				implementation(project(":frontend:shared:ui"))
				implementation(project(":frontend:shared:pois"))
				implementation(libs.koin)
			}
		}
	}
}

compose.desktop {
	application {
		mainClass = "com.lexfury.kkon.MainKt"

		nativeDistributions {
			targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
			packageName = "KotlinMultiplatformComposeDesktopApplication"
			packageVersion = "1.0.0"
		}
	}
}
