pipeline {
    agent any


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