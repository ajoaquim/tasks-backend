pipeline {
    agent any
    stages {
        stage ('BUILD BACKEND') {
            steps {
               bat 'mvn clean package -DskipTests=true' 
            }
            
        }
        stage ('UNIT TESTS') {
            steps {
               bat 'mvn test' 
            }
            
        }
    }
}