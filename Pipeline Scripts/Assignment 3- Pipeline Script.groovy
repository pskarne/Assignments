/*ASSIGNMENT 3:
Create 3 repositories having an individual index.html file in each repository. 
You have a httpd server installed on your machine.
Create 3 Jenkins jobs, now whenever you commit any changes to any repository,
index.html of that particular repository gets deployed on httpd server.*/
//Repo1-Job
pipeline{
    agent{
        label{
            label "built-in"
            customWorkspace "/mnt/workspace"
        }
    }
    stages{
        stage("git-clone"){
            steps{
                sh "rm -rf *"
                git credentialsId: 'Git-Token', url: 'https://github.com/pskarne/repo1.git'
            }
        }
        stage("httpd-server"){
            steps{
                sh "sudo yum install httpd -y"
                sh "sudo service httpd start"
                sh "sudo chmod -R 777 /var/www/html"
                sh "cp index.html /var/www/html/"
            }
        }
    }
}

//Repo2-Job
pipeline{
    agent{
        label{
            label "built-in"
            customWorkspace "/mnt/workspace"
        }
    }
    stages{
        stage("git-clone"){
            steps{
                sh "rm -rf *"
                git credentialsId: 'Git-Token', url: 'https://github.com/pskarne/repo2.git'
            }
        }
        stage("httpd-server"){
            steps{
                sh "sudo yum install httpd -y"
                sh "sudo service httpd start"
                sh "sudo chmod -R 777 /var/www/html"
                sh "cp index.html /var/www/html/"
            }
        }
    }
}


//Repo3-Job
pipeline{
    agent{
        label{
            label "built-in"
            customWorkspace "/mnt/workspace"
        }
    }
    stages{
        stage("git-clone"){
            steps{
                sh "rm -rf *"
                git credentialsId: 'Git-Token', url: 'https://github.com/pskarne/repo3.git'
            }
        }
        stage("httpd-server"){
            steps{
                sh "sudo yum install httpd -y"
                sh "sudo service httpd start"
                sh "sudo chmod -R 777 /var/www/html"
                sh "cp index.html /var/www/html/"
            }
        }
    }
}