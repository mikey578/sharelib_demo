def call(Map config = [:]) {
    def java = new org.example.ci.JavaBuild(this)

    stage("Build Java") {
        java.build(config)
    }

    stage("Test Java") {
        java.test(config)
    }

    stage("Package Java") {
        java.packageApp(config)
    }
}
