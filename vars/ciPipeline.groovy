def call(Map config = [:]) {
    pipeline {
        agent any

        stages {
            stage('Init') {
                steps {
                    script {
                        echo "Language: ${config.language}"
                    }
                }
            }

            stage('Build') {
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
        }
    }
}
