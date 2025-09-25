
pipeline {
    agent any

    environment {
        // Docker image names
        BACKEND_IMAGE = "chanu/doctor-backend"
        FRONTEND_IMAGE = "chanu/doctor-frontend"
        DOCKERHUB_CREDENTIALS = 'dockerhub-creds'  // Jenkins credentials ID
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/chanakyaboyini/Doctor-Booking-App.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS) {
                        dir('backend') {
                            def backendImage = docker.build("${BACKEND_IMAGE}:${env.BUILD_NUMBER}")
                            backendImage.push()
                            backendImage.push("latest")
                        }
                        dir('frontend') {
                            def frontendImage = docker.build("${FRONTEND_IMAGE}:${env.BUILD_NUMBER}")
                            frontendImage.push()
                            frontendImage.push("latest")
                        }
                    }
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                sh 'docker compose down || true'
                sh 'docker compose up -d'
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful! Access app at http://localhost:3000"
        }
        failure {
            echo "❌ Build/Deploy failed. Check logs."
        }
    }
}