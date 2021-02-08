pipeline {
  agent any
  stages {
    stage ('Build Backend') {
      steps {
        bat 'mvn clean package -DskipTests=true'
      }
    }
    stage ('Unit tests') {
      steps {
        bat 'mvn test'
      }
    }
    stage ('Deploy backend') {
      steps {
        deploy adapters: [tomcat9(credentialsId: 'tomcat-login', path: '', url: 'http://192.168.179.101:8080/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
      }
    }
    stage ('API Tests') {
      steps {
        git branch: 'main', credentialsId: 'github_login', url: 'https://github.com/zambrinf/tasks-api-test'
        bat 'mvn test'
      }
    }
  }
}

