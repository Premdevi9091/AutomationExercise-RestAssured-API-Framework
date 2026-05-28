pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME'
        maven 'MAVEN_HOME'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Premdevi9091/AutomationExercise-RestAssured-API-Framework.git'
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/allure-results/**', fingerprint: true
                archiveArtifacts artifacts: 'target/API_Report_AutomationExcercise.html', fingerprint: true
            }
        }
    }

    post {
        always {
            junit '**/surefire-reports/*.xml'
        }
    }
}
