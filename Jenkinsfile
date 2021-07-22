pipeline {
    agent any

    tools {
        maven "MAVEN_HOME"
    }
	
    stages {
        stage('Checkout') {
           steps {
                script {
                  checkout([$class: 'GitSCM', branches: [[name: '${branch}']], extensions: [], userRemoteConfigs: [[credentialsId: '06bde1cc-c4d8-41be-a083-b7eeee6658e9', url: 'https://github.com/harshvegada/WebAutomation_Mar21_Hybrid.git']]])
                }

            }
        }
        stage('Build') {
            steps {
                git 'https://github.com/harshvegada/WebAutomation_Mar21_Hybrid.gitt'
            }
        }
         stage('Run Test Cases') {
            steps {
                bat 'mvn clean test -Denv=QA -Dbroser=chrome -DxmlFile=testng'
            }
        }
    }
}
