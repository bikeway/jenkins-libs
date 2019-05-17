def call(result, MailList){
    if (params.SEND_EMAIL) {
        emailext (
        mimeType: 'text/html',
        subject: "������ ${result}: ������ '${env.JOB_NAME} [${env.BUILD_NUMBER}]'",
        body: """<p>������ ${result}: ������ '${env.JOB_NAME} [${env.BUILD_NUMBER}]':</p>
            <p>��������� ���������� �� ������: "<a href="${env.BUILD_URL}">${env.JOB_NAME} [${env.BUILD_NUMBER}]</a>"</p>""",
        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        to: "${MailList}"
    )
    } 
}