pipeline {
            agent any
             tools {
                     maven 'M3'
                    }

            stages {
             stage ('Initialize') {
                                      steps {
                                                  sh '''
                                                      echo "PATH = ${PATH}"
                                                      echo "M2_HOME = ${M2_HOME}"
                                                  '''
                                              }
                                  }

            stage('Build stage') {
                                     steps {
                                       sh 'mvn clean compile'
                                    }
                                  }
                   }
    post {
            always{
                    cleanWs()
                    }
         }


    }