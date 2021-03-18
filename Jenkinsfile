pipeline {
    agent any
    tools {
        maven 'Maven 3'
        jdk 'Java 8'
    }
    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '5'))
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage ('Deploy') {
            when {
                branch "master"
            }
            steps {
                rtMavenDeployer (
                        id: "maven-deployer",
                        serverId: "opencollab-artifactory",
                        releaseRepo: "maven-releases",
                        snapshotRepo: "maven-snapshots"
                )
                rtMavenResolver (
                        id: "maven-resolver",
                        serverId: "opencollab-artifactory",
                        releaseRepo: "maven-deploy-release",
                        snapshotRepo: "maven-deploy-snapshot"
                )
                rtMavenRun (
                        pom: 'pom.xml',
                        goals: 'source:jar install -DskipTests',
                        deployerId: "maven-deployer",
                        resolverId: "maven-resolver"
                )
                rtPublishBuildInfo (
                        serverId: "opencollab-artifactory"
                )
            }
        }
    }

    post {
        always {
            deleteDir()
            withCredentials([string(credentialsId: 'nukkitx-discord-webhook', variable: 'DISCORD_WEBHOOK')]) {
                discordSend description: "**Build:** [${currentBuild.id}](${env.BUILD_URL})\n**Status:** [${currentBuild.currentResult}](${env.BUILD_URL})", footer: 'NukkitX Jenkins', link: env.BUILD_URL, successful: currentBuild.resultIsBetterOrEqualTo('SUCCESS'), title: "${env.JOB_NAME} #${currentBuild.id}", webhookURL: DISCORD_WEBHOOK
            }
        }
    }
}