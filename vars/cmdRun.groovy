// ������� ���������� ������. ��������� ����������� ��� ������������� MS Windows

// ������ ������: cmdRun.���������("dir /w")

def call(String _command){
    command = _command.replace("%userCredentionalID%", "")
    if (isUnix()) {
        sh "${command}"
    } else {
        bat " chcp 65001\n${command}"
    }
}


def call(String _command, String credentionalID){
    if(credentionalID.trim().isEmpty()){
        call(_command) 
        return   
    }

    withCredentials([usernamePassword(credentialsId: "${credentionalID}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        
        AuthBase = "--db-user " + USERNAME
        try{
            AuthBase = AuthBase + "  --db-pwd " + PASSWORD  
        }catch ( e ) {
            // ������ �� ���������  
        } 
        command = _command.replace("%userCredentionalID%", AuthBase)

        if (isUnix()) {
            sh "${command}"
        } else {
            bat " chcp 65001\n${command}"
        }   
    }     
}

def ���������(def ��������������) {
   call(��������������) 
}

