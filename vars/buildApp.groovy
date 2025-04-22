def call(Map config = [:]) {
    stage('Build') {
        echo "Building ${config.appName ?: 'application'}..."
        sh 'echo 123'  // hoặc npm/yarn/mvn tùy tech stack
    }
}

