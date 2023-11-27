/*ASSIGNENMENT 08:
DOCKER
You have GitHub repository with 3 branches- (Eg: 23Q1, 23Q2, 23Q3).
Each branch has its own individual index.html file.
We have docker machine and 3 containers- 23Q1, 23Q2, 23Q3 (port- 80,90,8080)
Now let’s create a Jenkins pipeline job and deploy index.html inside respective
containers (Eg: index.html of 23Q1 branch be deployed on container 23Q1 and so on…)*/
pipeline{
    agent{
        label{
            label "built-in"
            customWorkspace "/mnt/multi-branch"
        }
    }
    stages{
        stage("Container-1"){
            steps{
                sh "rm -rf *"
                git branch: '23Q1', changelog: false, credentialsId: 'Git-Token', poll: false, url: 'https://github.com/pskarne/multi-branch.git'
                sh "chmod 777 index.html"
                sh "docker cp index.html C23Q1:/usr/local/apache2/htdocs/"
                
            }
        }
        stage("Container-2"){
            steps{
                sh "rm -rf *"
                git branch: '23Q2', changelog: false, credentialsId: 'Git-Token', poll: false, url: 'https://github.com/pskarne/multi-branch.git' 
                sh "chmod 777 index.html"
                sh "docker cp index.html C23Q2:/usr/local/apache2/htdocs/"
            }
        }
        stage("Container-3"){
            steps{
                sh "rm -rf *"
                git branch: '23Q3', changelog: false, credentialsId: 'Git-Token', poll: false, url: 'https://github.com/pskarne/multi-branch.git'
                sh "chmod 777 index.html"
                sh "docker cp index.html C23Q3:/usr/local/apache2/htdocs/"
            }
        }
    }
}