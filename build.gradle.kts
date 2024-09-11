plugins {
    id("java-library")
    id("maven-publish")
    id("signing")
}

version = "1.21.30-SNAPSHOT"
group = "org.cloudburstmc"
description = "Updates Minecraft: Bedrock Edition block states to the latest revision"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    annotationProcessor(libs.lombok)
    compileOnly(libs.lombok)
    api(libs.jackson.databind)
    api(libs.nbt)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.api)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "org.cloudburstmc.blockstateupdater")
    }
}

publishing {
    repositories {
        maven {
            name = "maven-deploy"
            url = uri(
                    System.getenv("MAVEN_DEPLOY_URL")
                            ?: "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            )
            credentials {
                username = System.getenv("MAVEN_DEPLOY_USERNAME") ?: "username"
                password = System.getenv("MAVEN_DEPLOY_PASSWORD") ?: "password"
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                inceptionYear.set("2020")
                packaging = "jar"
                url.set("https://github.com/CloudburstMC/BlockStateUpdater")

                scm {
                    connection.set("scm:git:git://github.com/CloudburstMC/BlockStateUpdater.git")
                    developerConnection.set("scm:git:ssh://github.com/CloudburstMC/BlockStateUpdater.git")
                    url.set("https://github.com/CloudburstMC/BlockStateUpdater")
                }

                licenses {
                    license {
                        name.set("LGPL 3.0")
                        url.set("https://www.gnu.org/licenses/lgpl-3.0.en.html")
                    }
                }

                developers {
                    developer {
                        name.set("CloudburstMC Team")
                        organization.set("CloudburstMC")
                        organizationUrl.set("https://github.com/CloudburstMC")
                    }
                }
            }
        }
    }
}

signing {
    if (System.getenv("PGP_SECRET") != null && System.getenv("PGP_PASSPHRASE") != null) {
        useInMemoryPgpKeys(System.getenv("PGP_SECRET"), System.getenv("PGP_PASSPHRASE"))
        sign(publishing.publications["maven"])
    }
}
