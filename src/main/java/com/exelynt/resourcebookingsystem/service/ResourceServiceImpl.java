package com.exelynt.resourcebookingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exelynt.resourcebookingsystem.entity.Resource;
import com.exelynt.resourcebookingsystem.repository.ResourceRepository;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource getResourceById(int id) {
        return resourceRepository.findById(id).orElse(null);
    }
    
    @Override
    public Resource updateResource(int id, Resource resource) {
        Resource existing = resourceRepository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(resource.getName());
            existing.setDescription(resource.getDescription());
            existing.setType(resource.getType());
            existing.setAvailable(resource.isAvailable());
            existing.setPrice(resource.getPrice());

            return resourceRepository.save(existing);
        }

        return null;
    }
    
    @Override
    public void deleteResource(int id) {
        resourceRepository.deleteById(id);
    }
}
