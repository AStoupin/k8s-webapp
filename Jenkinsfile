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
		  withCredentials([usernamePassword(credentialsId: 'dockerpwd', usernameVariable: 'USERNAME', passwordVariable: 'USERPASS')]) {
		    sh '''
		      echo $USERPASS >> aaa
		    '''
		  } 
		      
          sh 'cat aaa'
		          
          sh 'mvn clean package'
        }

        container('docker') {
		 withCredentials([usernamePassword(credentialsId: 'dockerpwd',usernameVariable: 'USERNAME', passwordVariable: 'USERPASS')]) {
		    sh '''
		      docker build -t k8s-webapp .
		      docker tag k8s-webapp astoupin/k8s-webapp
		      docker login -u $USERNAME -p $USERPASS
		      docker push astoupin/k8s-webapp 
		    '''
		  }          
          
        }

      }
    }
     
  }
}