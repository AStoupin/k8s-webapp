pipeline {
  agent {
    kubernetes {
      yamlFile 'kube-tests.yaml'
    }
  }
  stages {
    stage('Deploy') {
      steps {
        container('kubectl') {
		    sh '''
				kubectl patch deployment k8s-webapp -p \
				  \"{\\\"spec\\\":{\\\"template\\\":{\\\"metadata\\\":{\\\"annotations\\\":{\\\"date\\\":\\\"`date +\'%s\'`\\\"}}}}}"
		    '''
          
        }

      }
    } 
      
    stage('Build component') {
      steps {
      
        container('maven') {
          sh 'mvn clean package'
        }

      }
    }

    stage('Build & push image') {
      steps {
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