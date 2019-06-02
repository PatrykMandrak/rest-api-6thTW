package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.ApiKeyDAO;
import com.codecool.restAPI.Models.ApiKey;

import java.util.List;

public class ApiKeyModelService implements IModelService<ApiKey> {
    private static ApiKeyDAO apiKeyDao;

    public ApiKeyModelService() {
        apiKeyDao = new ApiKeyDAO();
    }

    @Override
    public void persist(ApiKey entity) {
        apiKeyDao.openCurrentSessionWithTransaction();
        apiKeyDao.persist(entity);
        apiKeyDao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(ApiKey entity) {
        apiKeyDao.openCurrentSessionWithTransaction();
        apiKeyDao.update(entity);
        apiKeyDao.closeCurrentSessionWithTransaction();
    }

    @Override
    public ApiKey findById(Long id) {
        apiKeyDao.openCurrentSession();
        ApiKey apiKey = apiKeyDao.findById(id);
        apiKeyDao.closeCurrentSession();
        return apiKey;
    }

    @Override
    public void delete(Long id) {
        apiKeyDao.openCurrentSessionWithTransaction();
        ApiKey apiKey = apiKeyDao.findById(id);
        apiKeyDao.delete(apiKey);
        apiKeyDao.closeCurrentSessionWithTransaction();
    }

    @Override
    public void deleteAll() {
        apiKeyDao().openCurrentSessionWithTransaction();
        apiKeyDao().deleteAll();
        apiKeyDao().closeCurrentSessionWithTransaction();
    }

    @Override
    public List<ApiKey> findAll() {
        apiKeyDao.openCurrentSession();
        List<ApiKey> apiKeys = apiKeyDao.findAll();
        apiKeyDao.closeCurrentSession();
        return apiKeys;
    }

    public boolean checkIfKeyExists(String key) {
        List<ApiKey> apiKeys = findAll();

        for (ApiKey currentApiKey : apiKeys) {
            if (currentApiKey.getKey().equals(key)) return true;
        }
        return false;
    }

    public ApiKeyDAO apiKeyDao() {
        return apiKeyDao;
    }
}
