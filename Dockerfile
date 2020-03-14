FROM astoupin/wf10-base

# Copy cli-settings 
COPY --chown=jboss ./docker/customization/. /opt/jboss/wildfly/customization
# Copy  war-file
COPY --chown=jboss ./target/*.war /opt/jboss/wildfly/customization

# Copy default property file
COPY --chown=jboss ./docker/customization/k8s-webapp.properties /opt/jboss/wildfly/standalone/configuration/k8s-webapp.properties


