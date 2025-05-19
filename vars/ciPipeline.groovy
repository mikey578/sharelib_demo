def call(Map config = [:]) {
    
    pipeline {
        agent agent-1

        stages {
            stage('Build') {
             //   agent {label 'build'}
                steps { 
                    script {
                        if (config.language == 'java') {
                            buildJava(config)
                        } else if (config.language == 'php') {
                            buildPhp(config)
                        } else {
                            error "Unsupported language: ${config.language}"
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