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
        stage ('SONAR ANALYSIS') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=d3f28c7bd8a6f97b8de41c1334e29cbd91847c23 -Dsonar.java.binaries=target"
                }
            }
            
        }stage ('DEPLOY BACKEND') {
            
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', onFailure: false, war: 'target/tasks-backend.war'    
            }
            
        }
       
    }
}
