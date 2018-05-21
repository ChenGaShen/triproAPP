package com.menglin.triproapp.util;
/** 
 * @author CGS 
 * @date 2018年5月18日 下午3:32:06 
 */
import net.sf.ehcache.Cache;  
import net.sf.ehcache.CacheManager;  
import net.sf.ehcache.Element;  
/** 
 *cacheManager：缓存管理器，以前是只允许单例的，不过现在也可以多实例了 
 *cache：缓存管理器内可以放置若干cache，存放数据的实质，所有cache都实现了Ehcache接口 
 *element：单条缓存数据的组成单位 
 * @author Administrator 
 * 
 */  
public class EHcacheUtil {  
  
    private static final String path = "ehcache.xml";  
      
    private static EHcacheUtil ehcacheUtil;  
      
    private CacheManager manager;   
      
    private EHcacheUtil(){  
        manager = CacheManager.create(EHcacheUtil.class.getResourceAsStream(path));  
    }  
      
    public synchronized static EHcacheUtil getInstance(){  
        if(ehcacheUtil == null){  
            return new EHcacheUtil();  
        }  
        return ehcacheUtil;  
    }  
      
    public void put(String cacheName, String key, Object value) {    
        Cache cache = getCache(cacheName);    
        Element element = new Element(key, value);    
        cache.put(element);    
    }    
      
    public Object get(String cacheName, String key) {    
        Cache cache = manager.getCache(cacheName);    
        Element element = cache.get(key);    
        return element == null ? null : element.getObjectValue();    
    }    
      
    public void remove(String cacheName, String key) {    
        Cache cache = manager.getCache(cacheName);    
        cache.remove(key);    
    }    
      
    public Cache getCache(String cacheName){  
        Cache cache = manager.getCache(cacheName);  
        if(cache == null){  
            throw new RuntimeException("根据cacheName="+cacheName+"无法获取到对于的cache!请检查配置文件"+path);  
        }  
        return cache;  
    }  
}  
