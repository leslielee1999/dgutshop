package org.dgutstu.dgutshop.core.storage;

import org.dgutstu.dgutshop.core.util.CharUtil;
import org.dgutstu.dgutshop.db.domain.DgutshopStorage;
import org.dgutstu.dgutshop.db.service.DgutshopStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * 提供存储服务类，所有存储服务均由该类对外提供
 * 对象存储：本地、阿里云、腾讯、七牛云
 */
public class StorageService {
    //  标志使用的对象存储
    private String active;
    //  对象存储类型
    private Storage storage;
    @Autowired
    private DgutshopStorageService dgutshopStorageService;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param fileName      文件索引名
     *
     * @return 组装好的对象存储model
     */
    public DgutshopStorage store(InputStream inputStream, long contentLength, String contentType, String fileName) {
        //  生成唯一的文件索引
        String key = generateKey(fileName);
        //  将文件存储到对应的对象存储中
        storage.store(inputStream, contentLength, contentType, key);
        //  生成文件的访问链接
        String url = generateUrl(key);
        DgutshopStorage storageInfo = new DgutshopStorage();
        storageInfo.setName(fileName);
        storageInfo.setSize((int) contentLength);
        storageInfo.setType(contentType);
        storageInfo.setKey(key);
        storageInfo.setUrl(url);
        //  将对象存储的信息转换为一个model并存储数据库对应的表里
        dgutshopStorageService.add(storageInfo);

        return storageInfo;
    }

    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        String suffix = originalFilename.substring(index);

        String key = null;
        DgutshopStorage storageInfo = null;

        /*
        *    查找索引是否已存在
        */
        do {
            key = CharUtil.getRandomString(20) + suffix;
            storageInfo = dgutshopStorageService.findByKey(key);
        } while (storageInfo != null);

        return key;
    }

    public Stream<Path> loadAll() {
        return storage.loadAll();
    }

    public Path load(String keyName) {
        return storage.load(keyName);
    }

    public Resource loadAsResource(String keyName) {
        return storage.loadAsResource(keyName);
    }

    public void delete(String keyName) {
        storage.delete(keyName);
    }

    private String generateUrl(String keyName) {
        return storage.generateUrl(keyName);
    }
}
