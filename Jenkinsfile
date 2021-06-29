pipeline {
    agent any
     tools {
        maven 'MAVEN_HOME'
      }

    stages {
     stage ('Initialize') {
                      steps {
                          bat '''
                              echo "PATH = ${PATH}"
                              echo "M2_HOME = ${M2_HOME}"
                          '''
                      }
                  }

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