package com.alikmndlu.servermanager.service;


import com.alikmndlu.servermanager.model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {

    Server createServer(Server server);

    Server pingServer(String ipAddress) throws IOException;

    Collection<Server> list(int limit);

    Server getById(Long serverId);

    Server updateServer(Server server);

    Boolean deleteServerByID(Long serverId);
}
