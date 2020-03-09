FROM astoupin/wf10-base


COPY --chown=jboss ./customization/. /opt/jboss/wildfly/customization

COPY --chown=jboss ./target/*.war /opt/jboss/wildfly/customization
COPY --chown=jboss ./customization/k8s-webapp.properties /opt/jboss/wildfly/standalone/configuration/k8s-webapp.properties


