def call(Map config = [:]) {
    def php = new org.example.ci.PhpBuild(this)

    stage("Install PHP dependencies") {
        php.installDependencies()
    }

    stage("Run PHP tests") {
        php.runTests()
    }
}
