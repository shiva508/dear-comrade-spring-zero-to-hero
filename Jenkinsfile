pipeline {
	agent any
	// agent { 
	// 		docker 
	// 			{ 
	// 				image 'maven:3.9.3-eclipse-temurin-17'
	// 			}
	// 	  }

	environment {
		mavenHome 	= tool 'dear-comrade-maven'
		dockerHome 	= tool 'dear-comrade-docker'
		PATH = "$mavenHome/bin:$dockerHome/bin:$PATH"
	}
	stages {
		stage('Build'){
			steps {
				//bat "mvn clean install -DskipTests"
				sh 'mvn --version'
				sh 'docker version'
				echo "PATH : $PATH"
				echo "BRANCH_NAME : $env.BRANCH_NAME"
				echo "CHANGE_AUTHOR : $env.CHANGE_AUTHOR"
				echo "BUILD_ID : $env.BUILD_ID"
				echo "JOB_NAME : $env.JOB_NAME"
			}
		}

		stage('Test'){
			steps{
				//bat "mvn test"
				echo "test"
			}
		}

		stage('Deploy') {
			steps {
			    //bat "mvn jar:jar deploy:deploy"
			    echo "push"
			}
		}
	}
	post{
		always{
			echo "Everything went well"
		}
		success{
			echo "Okay......."
		}
		failure{
			echo "Not Okay....."
		}
	}
	
}