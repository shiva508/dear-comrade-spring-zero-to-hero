pipeline {
	agent any
	// agent { 
	// 		docker 
	// 			{ 
	// 				image 'maven:3.9.3-eclipse-temurin-17'
	// 			}
	// 	  }

	// environment {
	// 	mavenHome = tool 'dear-comrade-maven'
	// }
	stages {
		stage('Build'){
			steps {
				//bat "mvn clean install -DskipTests"
				sh 'sudo  mvn --version'
				echo "hi"
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