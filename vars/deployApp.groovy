def call(Map config = [:]) {
    stage('Deploy') {
        echo "Deploying ${config.appName} to ${config.env}..."
        sh "echo deploy"
    }
}

