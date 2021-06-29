pipeline {
    agent any
     tools {
        maven 'MAVEN_HOME'
      }

    stages {


        stage('Install stage') {
            steps {
              sh 'mvn clean install'
        }
    }
    }
    post {
    always{
    cleanWs()
    }
    }


    }