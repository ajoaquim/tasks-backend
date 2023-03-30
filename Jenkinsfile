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
            
        }
        
        stage ('DEPLOY BACKEND') {
            
            steps {
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001')], contextPath: 'tasks-backend', onFailure: false, war: 'target/tasks-backend.war'    
            }
            
        }
        stage ('API TESTS') {
            
            steps {
                dir ('api-tests') {
                    git branch: 'main', credentialsId: 'github_jenkins', url: 'https://github.com/ajoaquim/tasks-api-test'
                    bat 'mvn test'
                }
            }
            
        }
        stage ('DEPLOY FRONTEND') {
            
            steps {
                dir ('tasks-frontend') {
                    git branch: 'master', credentialsId: 'github_jenkins', url: 'https://github.com/ajoaquim/tasks-frontend'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8001')], contextPath: 'tasks', onFailure: false, war: 'target/tasks.war'    
                }
            }
            

        }
        stage ('FUNCIONAIS TESTS') {
            
            steps {
                dir ('funcionais-tests') {
                    git branch: 'main', credentialsId: 'github_jenkins', url: 'https://github.com/ajoaquim/tasks-funcionais-test'
                    bat 'mvn test'
                }
            }
            
        }
        stage ('DEPLOY DE PRODUCAO') {
            
            steps {
                                  
                bat 'docker-compose build'
                bat 'docker-compose up -d'
               
            }
    }
}
