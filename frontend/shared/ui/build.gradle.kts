plugins {
	alias(libs.plugins.multiplatform)
	alias(libs.plugins.android.library)
	alias(libs.plugins.compose)
	alias(libs.plugins.touchlab.skie)
}

kotlin {
	androidTarget()
	jvm("desktop")
	listOf(
		iosX64(),
		iosArm64(),
		iosSimulatorArm64()
	).forEach { iosTarget ->
		iosTarget.binaries.framework {
			baseName = "SharedUI"
			isStatic = true
		}
	}
	js(IR) {
		moduleName = "kkon-ui"
		browser {
			commonWebpackConfig(Action {
				outputFileName = "kkon-ui.js"
			})
		}
		binaries.executable()
	}

	sourceSets {
		val commonMain = getByName("commonMain") {
			dependencies {
				implementation(project(":frontend:shared:model"))
				implementation(project(":frontend:shared:pois"))
				implementation(compose.runtime)
				implementation(compose.foundation)
				implementation(compose.material3)
				@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
				implementation(compose.components.resources)

				implementation(libs.koin)
				implementation(libs.kotlinx.collections.immutable)
				implementation(libs.touchlab.kermit)
			}
		}
		getByName("androidMain") {
			dependencies {
				api(libs.activity.compose)
				api(libs.core.ktx)

				implementation(libs.google.maps)
				implementation(libs.google.maps.utils)
				implementation(libs.google.play.services.maps)
			}
		}
		val iosMain = create("iosMain") {
			dependsOn(commonMain)
			dependencies {
				implementation(libs.koin)
			}
		}
		getByName("iosX64Main").dependsOn(iosMain)
		getByName("iosArm64Main").dependsOn(iosMain)
		getByName("iosSimulatorArm64Main").dependsOn(iosMain)
		getByName("desktopMain") {
			dependencies {
				implementation(compose.desktop.common)

				implementation(libs.jx.map.viewer)
			}
		}
	}
}

android {
	compileSdk = (findProperty("android.compileSdk") as String).toInt()
	namespace = findProperty("package.name").toString()

	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
	sourceSets["main"].res.srcDirs("src/androidMain/res")
	sourceSets["main"].resources.srcDirs("src/commonMain/resources")

	defaultConfig {
		minSdk = (findProperty("android.minSdk") as String).toInt()
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlin {
		jvmToolchain(17)
	}
}
