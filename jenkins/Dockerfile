# Use the official Jenkins LTS image as a base
FROM jenkins/jenkins:lts

# Install Docker inside Jenkins container to allow Jenkins to control Docker containers
USER root
RUN apt-get update && \
    apt-get install -y apt-transport-https ca-certificates curl software-properties-common && \
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" && \
    apt-get update && \
    apt-get install -y docker-ce-cli

# Give Jenkins user permission to run Docker
RUN usermod -aG docker jenkins

# Switch back to the Jenkins user
USER jenkins
