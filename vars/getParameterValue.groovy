
String call(Map buildParams, String keyName){
   
    def defaultParams = getDefaultParams()
    if(env."${keyName}" != null ){
        println "ENV: ��� ����� ${keyName} ������� �������� " +  env."${keyName}"
        return env."${keyName}"
    }else{
        if(buildParams."${keyName}" != null ){
            println "buildParams: ��� ����� ${keyName} ������� �������� " +  buildParams."${keyName}"
            return buildParams."${keyName}"
        }
        if(defaultParams."${keyName}" != ''){
            println "defaultParams: ��� ����� ${keyName} ������� �������� " +  defaultParams."${keyName}"
            return defaultParams."${keyName}"
        }
        println "�������� ��� ����� �� ������� ${keyName} ���������� ������ ������"
        return new String()
    }
}


def getDefaultParams(){
    return [
        // General
        'IS_FILE_CONTUR': 'true', //���������� ������ �� �������� ����
        'FILE_BASE_PATH': './build/ib', // '���� � �������� ����'
        'USING_DOCKER': 'false', // ��������� ������ � ���������� DOCKER
        'SEND_EMAIL': 'false', // �������� ���������� �� �����
        'EMAILS_FOR_NOTIFICATION':"", // �������� ���� ��� �����������
        'AGENT': 'windows', // �����
        'V8VERSION': "8.3", // ������ ���������
        'DB_USER_CREDENTIONALS_ID': "", // ������������� �������������� ������������ �������������� ����

        // Gitsync
        'PATH_TO_GITSYNC_CONF': './tools/JSON/gitsync_conf.JSON', // ���� � ����������������� ����� GITSYNC
        
        //Ci
        'PATH_TO_TAMPLATE_BASE' :'./examples/demo.dt',
        'SOURCE_PATH':'./src/cf', // ���� � ������� ����.
        'UCCODE': 'locked', // ������ ���������� �������������� ���� 
        'LOCK_MESSAGE': '������������ ������ �������� CI',
        'VRUNNER_CONF': 'tools/JSON/vRunner.json', // ���� � ����������������� ����� vrunner
        'PROCEDURE_SINTAX_CHECK': 'false', // ��������� �������������� �������� ���������� 1�
        'PROCEDURE_TDD_TEST': 'false', // ��������� TDD ������������
        'PROCEDURE_BDD_TEST': 'false' // �������� BDD ������������

    ]
}

def printDefaultParams(){
    println "${getDefaultParams()}" 
}





