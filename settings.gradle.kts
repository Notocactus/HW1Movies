plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "HW1Movies"
include("src:main:resources")
findProject(":src:main:resources")?.name = "resources"
