def call(Map config = [:]){
    pipeline {
    agent any 
    stages {
        stage ("deploy jenkins") {
            steps { 
		    ansiblePlaybook credentialsId: "AWS-SSH-KEY", disableHostKeyChecking: true, installation: "Ansible", inventory: "${config.jenkinshosts}", playbook: "${config.jenkinsyml}"
                }
            }
        }
    }
}