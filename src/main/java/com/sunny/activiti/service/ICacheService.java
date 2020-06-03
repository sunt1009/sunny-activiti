package com.sunny.activiti.service;
/**
* @Description:缓存服务
* @ClassName: ICacheService
* @author sunt
* @date 2019年4月2日下午2:20:11
 */
public interface ICacheService {


    /**
     * 保存字符串到缓存
     * @param cacheKey 缓存key
     * @param cacheValue 缓存值
     * @param timeout 过期时间(分钟)
     */
    void cacheStringData(String cacheKey, String cacheValue, long timeout);

    /**
     * 通过key获取缓存信息
     * @param cacheKey 缓存key
     * @return 缓存值
     */
    String getCacheInfoByCode(String cacheKey);

    /**
     * @Title: cacheObjData
     * @Description:缓存对象
     * @param cacheKey
     * @param cacheValue
     * @param timeout
     */
    void cacheObjData(String cacheKey, Object cacheValue, long timeout);

    /**
     * @Title: getCacheInfoByCode
     * @Description:获取缓存对象
     * @param cacheKey
     * @return
     */
    Object getObjCacheByCode(String cacheKey);

    /**
     * 删除指定的key
     * @param cacheKey
     * @return
     */
    boolean delCacheByCode(String cacheKey);
}
