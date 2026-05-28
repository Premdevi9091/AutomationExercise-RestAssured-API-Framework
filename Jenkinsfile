pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
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