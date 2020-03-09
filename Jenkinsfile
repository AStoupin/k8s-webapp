pipeline {
  agent {
    kubernetes {
      yamlFile 'kube-tests.yaml'
    }
  }
  stages {

      
    stage('Build component') {
      steps {
      
        container('maven') {
          sh 'mvn clean package'
          sh '''
          cd target
          artifactId=$(ls *.war | grep dep)
          echo $artifactId 
          
          '''
        }

      }
    }
    
  }
}