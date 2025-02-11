pipeline{
    agent{
        label{
            label "dev"
            customWorkspace "/mnt/slave-1/workplace"
        }
    }
    stages{
        stage("git+MVN install"){
            steps{
                sh "sudo yum install git -y"
                sh "rm -rf *"
                dir('/mnt/build-tool'){
                    sh "rm -rf apache-maven-3.9.5"
                    sh "sudo wget https://dlcdn.apache.org/maven/maven-3/3.9.5/binaries/apache-maven-3.9.5-bin.zip"
                    sh "sudo unzip apache-maven-3.9.5-bin.zip"
                    sh "sudo rm -rf apache-maven-3.9.5-bin.zip"
                    sh "sudo chmod -R 777 apache-maven-3.9.5"
                }
            }
        }
        stage("Git-Clone"){
            steps{
                git changelog: false, credentialsId: 'git-token', poll: false, url: 'https://github.com/pskarne/project.git'
            }
        }
        stage("MVN-Build"){
            environment{
                #To set environment path variable
				PATH = "/mnt/build-tool/apache-maven-3.9.5/bin:$PATH"
			}
	     	steps{
		    	sh "mvn clean install"
		 }
        }
        stage("Tomcat"){
            steps{
                sh "mkdir server"
                dir("/mnt/server"){
                    sh "sudo wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.97/bin/apache-tomcat-8.5.97.zip"        
                    sh "sudo unzip apache-tomcat-8.5.97.zip"
                    sh "sudo rm -rf apache-tomcat-8.5.97.zip"
                    sh "sudo chmod -R 777 apache-tomcat-8.5.97"
                    sh "mv /mnt/slave-1/workplace/target/LoginWebApp.war /mnt/server/apache-tomcat-8.5.97/webapps/"
                }
            }
        }

    }
}