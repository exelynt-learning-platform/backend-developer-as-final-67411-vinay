package com.exelynt.resourcebookingsystem.service;

import java.util.List;

import com.exelynt.resourcebookingsystem.entity.Resource;

public interface ResourceService {

    Resource addResource(Resource resource);

    List<Resource> getAllResources();

    Resource getResourceById(int id);
    Resource updateResource(int id, Resource resource);
    void deleteResource(int id);
}