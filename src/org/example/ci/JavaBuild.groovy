package org.example.ci

class JavaBuild implements Serializable {
    def steps

    JavaBuild(steps) {
        this.steps = steps
    }

    def build(Map config) {
        steps.sh "mvn clean compile"
    }

    def test(Map config) {
        steps.sh "mvn test"
    }

    def packageApp(Map config) {
        steps.sh "mvn package"
    }
}
