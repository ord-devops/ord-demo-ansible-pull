import jenkins.model.Jenkins
import jenkins.security.s2m.AdminWhitelistRule

Jenkins j = Jenkins.instance

if(!j.isQuietingDown()) {
    j.setNumExecutors(8)
    j.getDescriptor("jenkins.CLI").get().setEnabled(false)
    j.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

    Set<String> agentProtocolsList = ['JNLP4-connect', 'Ping']
    if(!j.getAgentProtocols().equals(agentProtocolsList)) {
        j.setAgentProtocols(agentProtocolsList)
        Jenkins.instance.setSlaveAgentPort(50000)
        println "Agent Protocols have changed.  Setting: ${agentProtocolsList}"
        j.save()
    }
    else {
        println "Nothing changed.  Agent Protocols already configured: ${j.getAgentProtocols()}"
    }
}
else {
    println 'Shutdown mode enabled.  Configure Agent Protocols SKIPPED.'
}
