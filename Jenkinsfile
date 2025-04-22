@Library('my-shared-lib') _
def jobUrl = "${env.BUILD_URL}"
pipeline {
    agent any

    environment {
        APP_NAME = 'order-service'
        ENV = 'dev'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                buildApp(appName: APP_NAME)
            }
        }

        stage('Deploy') {
            steps {
                deployApp(appName: APP_NAME, env: ENV)
            }
        }
    }
   post {
        success {
            notifyTelegram("✅ Build Passed: [See job](${jobUrl})")
        }
        failure {
            notifyTelegram("❌ Build Failed!* [See job](${jobUrl})")
        }
    }

}

