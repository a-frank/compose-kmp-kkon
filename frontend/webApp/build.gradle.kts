plugins {
	alias(libs.plugins.multiplatform)
	alias(libs.plugins.compose)
}

kotlin {
	js(IR) {
		moduleName = "kkon-web-app"
		browser {
			commonWebpackConfig(Action {
				outputFileName = "kkon-web-app.js"
			})
		}
		binaries.executable()
	}

	sourceSets {
		getByName("jsMain") {
			dependencies {
				implementation(compose.html.core)
				implementation(compose.runtime)

				implementation(project(":frontend:shared:model"))
				implementation(project(":frontend:shared:pois"))
				implementation(libs.koin)
			}
		}
	}
}
