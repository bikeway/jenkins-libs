#!/usr/bin/env groovy
def call(Map buildEnv){
  
    pipeline {
       
        agent {
            label getParameterValue(buildEnv, 'AGENT')
        }

        environment{
            def PATH_TO_TAMPLATE_BASE       = getParameterValue(buildEnv, 'PATH_TO_TAMPLATE_BASE')
        }

        post {  //����������� ����� ������
            always {
                // junit. ���������: testResults: '**/out/junit/*.xml'
                // allure ��������� results: [[path: 'out/allure'], [path: 'out/addallure.xml']]
            }
            failure {
                cmdRun("echo ��������� ��������� ��� ������")      
            }
            success {
               // ��� ������)
            } 
        }
    
        stages {
            
            stage("���������� ��������� �������") {
                steps {                      
                    timestamps {
                        script{
                            timeout(20) {
                                prepareBase(buildEnv) 
                            }
                        }
                            
                    }
                }
            }

            stage('�������������� ��������'){
                steps {
                    timestamps {
                        script{
                            timeout(20) {
                                try{
                                    println "LOG: PROCEDURE_SINTAX_CHECK -  ${PROCEDURE_SINTAX_CHECK}"
                                    if(PROCEDURE_SINTAX_CHECK.trim().equals("true")){
                                        syntaxCheck(buildEnv) 
                                    } 
                                } catch (err) {
                                    currentBuild.result = 'FAILURE'
                                }
                            }                                                 
                        }
                    }
                }
            }
            
            stage('������� ������������'){
                steps {
                    timestamps {
                        script{
                            timeout(20) {
                                try{
                                    println "LOG: PROCEDURE_SINTAX_CHECK -  ${PROCEDURE_TDD_TEST}"
                                    if(PROCEDURE_TDD_TEST.trim().equals("true")){
                                        tddTesting(buildEnv)
                                    }
                                } catch (err) {
                                    currentBuild.result = 'FAILURE'
                                }
                            }                             
                        }
                        
                    }
                }
            }

            stage('�������������� ������������'){
                steps {
                    timestamps {
                        script{
                            timeout(20) {
                                try{
                                    if(PROCEDURE_BDD_TEST.trim().equals("true")){
                                        bddTesting(buildEnv)
                                    }
                                } catch (err) {
                                    currentBuild.result = 'FAILURE'
                                }
                            }
                        }                   
                    }
                }
            }
            
            stage('������ ��������'){
                steps {
                    timestamps {
                        script{
                            timeout(20) {
                                try{
                                    if(currentBuild.result != 'FAILURE'){
                                        buildRelise(buildEnv)
                                    }
                                } catch (err) {
                                    currentBuild.result = 'FAILURE'
                                }
                            }
                        }                   
                    }
                }
            }
        }
    }
}

def call(){
    call([:])  
}

// �������������� �������� ���� � ������
def prepareBase(Map buildEnv){
    def connectionString = getConnectionString(buildEnv)
    if (fileExists("${PATH_TO_TAMPLATE_BASE}")) { 
        println "LOG: tamplate DB file exist"
        // init-dev 
        // compile 
        // updatedb 
    } else {
         // init-dev 
    }  
                
     // run 

    if (fileExists('compile.log')) {
        archiveArtifacts 'compile.log'
    }
      
}

def syntaxCheck(Map buildEnv) {   
    
}

// ������� ������������ (BDD)
def tddTesting(Map buildEnv){   
    
}

// Vanessa-Add 
def bddTesting(Map buildEnv){
  
}



def buildRelise(Map buildEnv, String connectionString){

}

def getDBUserCredentialsId() {
   
}

