@Library('my-shared-lib') _

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
}

