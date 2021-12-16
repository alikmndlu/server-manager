package com.alikmndlu.servermanager.service.impl;

import com.alikmndlu.servermanager.model.Server;
import com.alikmndlu.servermanager.model.enumeration.ServerStatus;
import com.alikmndlu.servermanager.repository.ServerRepository;
import com.alikmndlu.servermanager.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static com.alikmndlu.servermanager.model.enumeration.ServerStatus.SERVER_DOWN;
import static com.alikmndlu.servermanager.model.enumeration.ServerStatus.SERVER_UP;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server createServer(Server server) {
        log.info("Saving new server : {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server pingServer(String ipAddress) throws IOException {
        log.info("Pining server ip : {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setServerStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server getById(Long serverId) {
        log.info("Fetching server by id : {}", serverId);
        return serverRepository.findById(serverId).get();
    }

    @Override
    public Server updateServer(Server server) {
        log.info("Updating server : {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean deleteServerByID(Long serverId) {
        log.info("Deleting server by id : {}", serverId);
        serverRepository.deleteById(serverId);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = {
                "server1.png",
                "server2.png",
                "server3.png",
                "server4.png"
        };
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/sever/image/" + imageNames[new Random().nextInt(imageNames.length)]).toUriString();
    }
}
