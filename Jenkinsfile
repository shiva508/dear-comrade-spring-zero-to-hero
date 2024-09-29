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
		stage('Info'){
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
		stage('Build'){
			steps{
				echo "Build : started"
				sh "mvn clean compile"
				echo "Build : completed"
			}
		}

		stage('Test'){
			steps{
				echo "Test : started"
				bat "mvn test"
				echo "Test : completed"
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