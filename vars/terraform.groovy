def call(Map config = [:]){
pipeline {
    agent any 
    stages {
        stage ("deploy terraform") {
            steps { 
                withCredentials([[
                    $class: "AmazonWebServicesCredentialsBinding",
                    credentialsId: "AwsCredentials",
                    accessKeyVariable: "AWS_ACCESS_KEY_ID",
                    secretKeyVariable: "AWS_SECRET_ACCESS_KEY"
                ]])
                    sh "terraform init -backend-config=${config.access_key}=${AWS_ACCESS_KEY_ID} -backend-config=${config.secret_key}=${AWS_SECRET_ACCESS_KEY}"
                    sh "terraform plan"
                    sh "terraform apply"
                }
                }
            }
        }
}
