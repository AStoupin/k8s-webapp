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

  	stage ('Deploy') {
        steps {
            build job: 'deployComponent', parameters: [
            string(name: 'deploymentName', value: 'k8s-webapp')
            ]
        }
    }   
        
  }
}