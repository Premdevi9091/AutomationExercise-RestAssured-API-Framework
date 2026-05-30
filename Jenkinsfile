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

        stage('Verify Tools') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run API Tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/API_Report_AutomationExcercise.html',
                                 fingerprint: true,
                                 allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            junit testResults: '**/surefire-reports/junitreports/*.xml',
                  allowEmptyResults: true
        }
    }
}