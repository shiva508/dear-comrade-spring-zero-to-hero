pipeline {
	agent any

	environment {
		mavenHome = tool 'dear-comrade-maven'
	}

/* 	tools {
		jdk 'java-21'
	} */
    node {
        stages {

        		stage('Build'){
        			steps {
        				//bat "mvn clean install -DskipTests"
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
    }

}