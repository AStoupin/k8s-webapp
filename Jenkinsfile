pipeline {
  agent {
    kubernetes {
		label  'jenkins-slave-label'
		
    }
    
  }
  stages {

   // stage('Approval') {
   //   steps {
   //     script {
   //       def userInput = input(id: 'confirm', message: 'Apply Terraform?', parameters: [ [$class: 'BooleanParameterDefinition', defaultValue: false, description: 'Apply terraform', name: 'confirm'] ])
   //     }
   //   }
   // }
  	stage ('Invoke_pipeline') {
        steps {
            build job: 'pipeline1', parameters: [
            string(name: 'param1', value: "value1")
            ]
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

	          cd target
	          export artifactId=$(ls *.war)
	          echo artifactId=$artifactId 

		      docker tag k8s-webapp astoupin/k8s-webapp 
		      docker tag k8s-webapp astoupin/k8s-webapp:${artifactId}__${BUILD_NUMBER}   
		      
		      docker login -u $USERNAME -p $USERPASS
		      docker push astoupin/k8s-webapp
		      docker push astoupin/k8s-webapp:${artifactId}__${BUILD_NUMBER}
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
				kubectl patch deployment k8s-webapp -n default -p \
				  \"{\\\"spec\\\":{\\\"template\\\":{\\\"metadata\\\":{\\\"annotations\\\":{\\\"date\\\":\\\"`date +\'%s\'`\\\"}}}}}"
		    '''
          
        }

      }
    }  
        
  }
}