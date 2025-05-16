package org.example.ci

class JavaBuild implements Serializable {
    def steps

    JavaBuild(steps) {
        this.steps = steps
    }

    def build(Map config) {
        steps.sh "echo mvn clean compile"
    }

    def test(Map config) {
        steps.sh "echo mvn test"
    }

    def packageApp(Map config) {
        steps.sh "echo mvn package"
    }
}
