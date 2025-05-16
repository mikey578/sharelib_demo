def call(Map config = [:]) {
    
    pipeline {
        agent any

        stages {
            stage('Build') {
             //   agent {label 'build'}
                steps { 
                    echo "Language: ${config.language}"
                    echo "Project: ${config.project}"
                    echo "Env: ${config.env}"
                    echo "CI Server: ${config.ciServer}"   
                    echo "Language: ${config.language}"
                    echo "Project: ${config.project}"
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