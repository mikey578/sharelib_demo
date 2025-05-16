def call(Map config = [:]) {
    echo "Language is ${config.language}"
    echo "Project is ${config.project}"
    pipeline {
        agent none

        stages {
            stage('Build') {
                agent {label 'build'}
                steps {    
                    script {
                        checkoutByTag(repoUrl, tag, String credentialsId = 'default-cred')
                        if (config.language == 'java') {
                            buildJava(config)
                        } else if (config.language == 'php') {
                            buildPhp(config)
                        } else {
                            error "Unsupported language: ${config.language}"
                        }
                    
                }
            }
            stage('Push to s3/ecr/arifact') {
                agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "Push code"'
                    }
                }
            }
            stage('update upstream proxy') {
                agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "canary deployment"'
                    }
                }
            }
            stage('deploy to prd server') {
                agent {label 'build'}
                steps {    
                    script {
                        sh 'echo "download source from repository"'
                        sh 'generation configuration'
                        sh 'restart services'
                        sh 'healcheck function'
                        sh 'update proxy'
                    }
                }
            }
            stage('clean') {
                agent {label 'build'}
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
        userRemoteConfigs: [[
            url: repoUrl,
            credentialsId: credentialsId
        ]]
    ])
}

