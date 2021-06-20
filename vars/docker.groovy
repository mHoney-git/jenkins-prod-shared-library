def call(Map config = [:]){
    pipeline {
	    environment {
		    registry="${config.repo}:${config.tag}"
		    registryCredential="Honey_Docker"
		    dockerImage=""
	  	  }
    agent any 
    stages {
        stage ("Building docker image") {
            steps { 
		  script {dockerImage=docker.build registry}
                }
            }

	stage ("Pushing docker image to docker hub") {
		steps {
			script {docker.withRegistry("",registryCredential) {dockerImage.push() }}
		}
	}

	stage ("Cleaning up extras") {
		steps {
			sh "docker rmi $registry"
		}
	}
        }
    }
}