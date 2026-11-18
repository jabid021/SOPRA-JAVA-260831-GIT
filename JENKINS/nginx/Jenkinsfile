pipeline {
    agent any

    stages {
        stage('Docker Compose') {
            steps {
                sh 'docker compose up -d --build'
            }
        }
    }
}
