def call(String project, String imageTag, String hubUser) {

    withCredentials([usernamePassword(
        credentialsId: "dockerhub-cred",
        usernameVariable: "USER",
        passwordVariable: "PASS"
    )]) {

        sh """
            
            echo \$PASS | docker login -u \$USER --password-stdin
            docker build -t ${hubUser}/${project}:${imageTag} .
            docker push ${hubUser}/${project}:${imageTag}
        """
    }
}


// def call(String aws_account_id, String region, String ecr_repoName){
    
//     sh """
//      aws ecr get-login-password --region ${region} | docker login --username AWS --password-stdin ${aws_account_id}.dkr.ecr.${region}.amazonaws.com
//      docker push ${aws_account_id}.dkr.ecr.${region}.amazonaws.com/${ecr_repoName}:latest
//     """
// }
