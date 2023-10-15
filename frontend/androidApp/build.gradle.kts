plugins {
	alias(libs.plugins.multiplatform)
	alias(libs.plugins.android.application)
	alias(libs.plugins.compose)
	alias(libs.plugins.google.maps.secrets)
}

kotlin {
	androidTarget()

	sourceSets {
		getByName("androidMain") {
			dependencies {
				implementation(project(":frontend:shared:ui"))
				implementation(project(":frontend:shared:pois"))
				implementation(libs.koin)
			}
		}
	}
}

android {
	compileSdk = (findProperty("android.compileSdk") as String).toInt()
	namespace = findProperty("package.name").toString()

	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

	defaultConfig {
		applicationId = "com.lexfury.kkon"
		minSdk = (findProperty("android.minSdk") as String).toInt()
		targetSdk = (findProperty("android.targetSdk") as String).toInt()
		versionCode = 1
		versionName = "1.0"
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlin {
		jvmToolchain(17)
	}
}
