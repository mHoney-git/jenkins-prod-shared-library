def call(Map config = [:]){
    pipeline {
    agent any 
    stages {
        stage ("shared pipeline for deploying apache to it's respective server") {
            steps { 
		    ansiblePlaybook credentialsId: "AWS-SSH-KEY", disableHostKeyChecking: true, installation: "Ansible", inventory: "${config.apachehost}", playbook: "${config.apachefile}"
                }
            }
      	}
    }
}