package org.hackstyle.dto;


public class Index {

    private String health;
    private String status;
    private String index;
    private String uuid;
    private String primary;
    private String replica;
    private String totalDocs;
    private String deletedDocs;
    private String storeSize;
    private String priStoreSize;
    private String mapping;
    private String settings;


    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getReplica() {
        return replica;
    }

    public void setReplica(String replica) {
        this.replica = replica;
    }

    public String getTotalDocs() {
        return totalDocs;
    }

    public void setTotalDocs(String totalDocs) {
        this.totalDocs = totalDocs;
    }

    public String getDeletedDocs() {
        return deletedDocs;
    }

    public void setDeletedDocs(String deletedDocs) {
        this.deletedDocs = deletedDocs;
    }

    public String getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(String storeSize) {
        this.storeSize = storeSize;
    }

    public String getPriStoreSize() {
        return priStoreSize;
    }

    public void setPriStoreSize(String priStoreSize) {
        this.priStoreSize = priStoreSize;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }
    
    
    
}
