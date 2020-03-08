pipeline {
  agent {
    kubernetes {
      yamlFile 'kube-tests.yaml'
    }
  }
  stages {
    stage('Build') {
      steps {
      
        container('maven') {
          sh 'mvn clean package'
        }

        container('docker') {
          sh 'docker build -t k8s-webapp .'
          sh 'docker tag k8s-webapp astoupin/k8s-webapp'
          sh 'docker push astoupin/k8s-webapp'
        }

      }
    }
     
  }
}