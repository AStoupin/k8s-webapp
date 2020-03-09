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
        }

      }
    }

    stage('Build & push image') {
      steps {
        container('docker') {
		 withCredentials([usernamePassword(credentialsId: 'dockerpwd',usernameVariable: 'USERNAME', passwordVariable: 'USERPASS')]) {

		    sh '''
		      docker build -t k8s-webapp  -t k8s-webapp:${BUILD_NUMBER} .

	          cd target
	          export artifactId=$(ls *.war)
	          echo artifactId=$artifactId 

		      docker tag k8s-webapp astoupin/k8s-webapp 
		      docker tag k8s-webapp $artifactId 
		      
		      docker login -u $USERNAME -p $USERPASS
		      docker push astoupin/k8s-webapp 
		    '''
		  }          
          
        }

      }
    }
    
    stage('Deploy') {
      steps {
        container('kubectl') {
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