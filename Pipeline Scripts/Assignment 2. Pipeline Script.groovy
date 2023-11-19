/*ASSIGNMENT 2: 
Create a git repository with three branches 2023Q1, 2023Q2, 2023Q3 each branch has
its own index.html file. Create 3 jobs for three branches, run each job and website
should be deploy on our httpd server with different code for different job, Using
Jenkins Pipeline Job.*/
pipeline{
    agent{
        label{
            label "built-in"
            customWorkspace "/mnt/workspace"
        }
    }
    stages{
        stage("httpd-server"){
            steps{
                sh "sudo yum install httpd -y"
                sh "sudo service httpd start"
            }
        }
        stage("git-clone"){
            steps{
                git branch: '23Q1', changelog: false, credentialsId: 'git-token', poll: false, url: 'https://github.com/pskarne/multi-branch.git'
            }
        }
        stage("deploy-index.html"){
            steps{
                sh "sudo chmod -R 777 /var/www/html/"
                sh "cp -r index.html /var/www/html/"
            }
        }
    }
}