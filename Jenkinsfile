node('maven') {
    stage ('OpenShift Image Build') {
        openshiftBuild(buildConfig: 'icodejava-customers')
    }
    stage ('Verify OpenShift Deployment') {
        openshiftVerifyDeployment(deploymentConfig: 'icodejava-customers')
    }
    stage ('Scale OpenShift Deployment') {
        openshiftScale(deploymentConfig: 'icodejava-customers', replicaCount: '2')
    }
}
