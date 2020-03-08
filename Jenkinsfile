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
		 withCredentials([usernameColonPassword(credentialsId: '70d38d8e-f71e-4727-b1aa-b0d8fd11e48d', variable: 'USERPASS')]) {
		    sh '''
		      docker build -t k8s-webapp .
		      docker tag k8s-webapp astoupin/k8s-webapp
		      docker login -u astoupin -p $USERPASS
		      docker push astoupin/k8s-webapp 
		    '''
		  }          
          
        }

      }
    }
     
  }
}