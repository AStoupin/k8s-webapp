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
		 withCredentials([usernameColonPassword(credentialsId: 'dockerpwd', passwordVariable: 'USERPASS')]) {
		    sh '''
		      echo $USERPASS >> aaa
		    '''
		  } 
		      
          sh 'cat aaa'
		          
          sh 'mvn clean package'
        }

        container('docker') {
		 withCredentials([usernameColonPassword(credentialsId: 'dockerpwd', passwordVariable: 'USERPASS')]) {
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