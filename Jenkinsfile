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

        stage('Deploy stage') {
            steps {
              bat 'mvn clean deploy'
        }
    }
    }
    post {
    always{
    cleanWs()
    }
    }


    }