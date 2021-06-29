pipeline {
    agent any
     tools {
        maven 'MAVEN_HOME'
      }

    stages {


        stage('Install stage') {
            steps {
              bat 'mvn clean install'
        }
    }
    }
    post {
    always{
    cleanWs()
    }
    }


    }