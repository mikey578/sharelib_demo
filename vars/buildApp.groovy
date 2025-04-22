def call(Map config = [:]) {
    stage('Build') {
        echo "Building ${config.appName ?: 'application'}..."
        sh 'date;sleep 5s;date'  // hoặc npm/yarn/mvn tùy tech stack
    }
}

