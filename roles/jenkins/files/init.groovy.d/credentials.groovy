import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.plugins.credentials.impl.*;

String keyfile="/var/lib/jenkins/kubernetes.pfx"
String password="jenkins"

// Kubernetes keypair
def ksm1 = new CertificateCredentialsImpl.FileOnMasterKeyStoreSource(keyfile)
Credentials ck1 = new CertificateCredentialsImpl(CredentialsScope.GLOBAL,"kubernetes-certkey", "CN=Jenkins", password, ksm1)
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), ck1)

// Github token
Credentials c = (Credentials) new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL,"jenkins-github-token", "Jenkins github token", "wouterhummelink", System.getenv('JENKINS_GITHUB_TOKEN'))
SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), c)
