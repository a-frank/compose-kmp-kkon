import SwiftUI
import SharedUI

@main
struct iOSApp: App {
	init() {
        KoinInitializerKt.doInitKoin()
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
