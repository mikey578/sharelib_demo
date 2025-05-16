package org.example.ci

class PhpBuild implements Serializable {
    def steps

    PhpBuild(steps) {
        this.steps = steps
    }

    def installDependencies() {
        steps.sh "composer install"
    }

    def runTests() {
        steps.sh "vendor/bin/phpunit"
    }
}
