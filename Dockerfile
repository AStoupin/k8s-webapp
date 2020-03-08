FROM astoupin/wf10-base


COPY --chown=jboss ./target/*.jar /opt/jboss/wildfly/customization

COPY --chown=jboss ./customization/. /opt/jboss/wildfly/customization


USER jboss

CMD ["/bin/bash", "/opt/jboss/wildfly/customization/execute.sh"]
