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

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/API_Report.html',
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
