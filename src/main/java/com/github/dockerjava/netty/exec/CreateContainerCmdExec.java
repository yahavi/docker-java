package com.github.dockerjava.netty.exec;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateContainerCmd.Exec;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.netty.MediaType;
import com.github.dockerjava.netty.WebTarget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateContainerCmdExec extends AbstrSyncDockerCmdExec<CreateContainerCmd, CreateContainerResponse>
        implements CreateContainerCmd.Exec {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateContainerCmdExec.class);

    public CreateContainerCmdExec(WebTarget baseResource, DockerClientConfig dockerClientConfig) {
        super(baseResource, dockerClientConfig);
    }

    @Override
    protected CreateContainerResponse execute(CreateContainerCmd command) {
        WebTarget webResource = getBaseResource().path("/containers/create");

        if (command.getName() != null) {
            webResource = webResource.queryParam("name", command.getName());
        }

        LOGGER.trace("POST: {} ", webResource);
        return webResource.request().accept(MediaType.APPLICATION_JSON)
                .post(command, CreateContainerResponse.class);
    }

}
