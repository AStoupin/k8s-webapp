pipeline {
  agent {
    kubernetes {
      yamlFile 'kube-tests.yaml'
    }
  }
  stages {

      
    stage('Build component') {
      steps {
      
        container('docker-node') {
          sh 'mvn clean package'
        }

      }
    }
/*
    stage('Build & push image') {
      steps {
        container('docker') {
		 withCredentials([usernamePassword(credentialsId: 'dockerpwd',usernameVariable: 'USERNAME', passwordVariable: 'USERPASS')]) {

		    sh '''
		      docker build -t k8s-webapp .

	          cd target
	          export artifactId=$(ls *.war)
	          echo artifactId=$artifactId 

		      docker tag k8s-webapp astoupin/k8s-webapp 
		      docker tag k8s-webapp astoupin/k8s-webapp:$artifactId__${BUILD_NUMBER}   
		      
		      docker login -u $USERNAME -p $USERPASS
		      docker push astoupin/k8s-webapp
		      docker push astoupin/k8s-webapp:$artifactId__${BUILD_NUMBER}
		    '''
		  }          
          
        }

      }
    }
    */
    stage('Deploy') {
      steps {
        container('docker-node') {
        //update pods
		    sh '''
				kubectl patch deployment k8s-webapp -p \
				  \"{\\\"spec\\\":{\\\"template\\\":{\\\"metadata\\\":{\\\"annotations\\\":{\\\"date\\\":\\\"`date +\'%s\'`\\\"}}}}}"
		    '''
          
        }

      }
    }     
  }
}