def call(Map config = [:]) {
    
    pipeline {
        agent any

        stages {
            stage('Build') {
             //   agent {label 'build'}
                agent {
                    docker { image 'maven:3.9.0' }
                }
                steps { 
                    script {
                        docker.image('maven:3.9.0').inside('--rm') {
                            sh 'mvn version'
                        }
                    }
                }
            }
            stage('Push to s3/ecr/arifact') {
               // agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "Push code"'
                    }
                }
            }
            stage('update upstream proxy') {
               // agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "canary deployment"'
                    }
                }
            }
            stage('deploy to prd server') {
               // agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "download source from repository"'
                        sh 'echo "generation configuration"'
                        sh 'echo "restart services"'
                        sh 'echo "healcheck function"'
                        sh 'echo "update proxy"'
                    }
                }
            }
            stage('clean') {
               // agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "clean"'
                    }
                }
            }
        }
    }
}
def checkoutByTag(String repoUrl, String tag, String credentialsId = 'default-cred') {
    checkout([
        $class: 'GitSCM',
        branches: [[name: "refs/tags/${tag}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        userRemoteConfigs: [[
            url: repoUrl,
            credentialsId: credentialsId
        ]]
    ])
}