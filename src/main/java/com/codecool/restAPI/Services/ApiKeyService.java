package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.ApiKeyDAO;
import com.codecool.restAPI.Models.ApiKey;

import java.util.List;

public class ApiKeyService {
    private static ApiKeyDAO apiKeyDao;

    public ApiKeyService() {
        apiKeyDao = new ApiKeyDAO();
    }

    public void persist(ApiKey entity) {
        apiKeyDao.openCurrentSessionWithTransaction();
        apiKeyDao.persist(entity);
        apiKeyDao.closeCurrentSessionWithTransaction();
    }

    public void update(ApiKey entity) {
        apiKeyDao.openCurrentSessionWithTransaction();
        apiKeyDao.update(entity);
        apiKeyDao.closeCurrentSessionWithTransaction();
    }

    public ApiKey findById(Long id) {
        apiKeyDao.openCurrentSession();
        ApiKey apiKey = apiKeyDao.findById(id);
        apiKeyDao.closeCurrentSession();
        return apiKey;
    }

    public void delete(Long id) {
        apiKeyDao.openCurrentSessionWithTransaction();
        ApiKey apiKey = apiKeyDao.findById(id);
        apiKeyDao.delete(apiKey);
        apiKeyDao.closeCurrentSessionWithTransaction();
    }

    public List<ApiKey> findAll() {
        apiKeyDao.openCurrentSession();
        List<ApiKey> apiKeys = apiKeyDao.findAll();
        apiKeyDao.closeCurrentSession();
        return apiKeys;
    }

    public boolean checkIfKeyExists(String key) {
        List<ApiKey> apiKeys = findAll();

        for(ApiKey currentApiKey: apiKeys) {
            if(currentApiKey.getKey().equals(key)) return true;
        }
        return false;
    }

    public ApiKeyDAO apiKeyDao() {
        return apiKeyDao;
    }
}
