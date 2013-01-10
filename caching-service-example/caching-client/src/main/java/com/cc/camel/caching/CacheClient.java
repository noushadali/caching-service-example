package com.cc.camel.caching;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Class to demonstrate using a Karaf caching servcie.
 *
 * @author Dean Harrington
 * @version 0.0.1-SNAPSHOT
 *
 */

public class CacheClient {

    /** Reference to the EHCache CacheManager. */
    private CacheManager cacheManager;
    /** EHCache Cache object. */
    private Cache cache;
    /** Number of iterations. */
    private final Integer iterations = 10;

    /** Default constructor. */
    public CacheClient() {
    }

    /** Convenience constructor that sets the CacheManager.
     * @param cacheManager0 The CacheManager to use
     */
    public CacheClient(final CacheManager cacheManager0) {
        this.cacheManager = cacheManager0;
    }

    /** Init method to create and load the cache. */
    public final void init() {
        cacheManager.addCache("TestCache");
        cache = cacheManager.getCache("TestCache");
        loadCache();
    }

    /** Destroy method to remove the cache. */
    public final void destroy() {
        printCache();
        cacheManager.removeCache("TestCache");
    }

    /** Method to load the cache with arbitrary data. */
    public final void loadCache() {
        for (Integer i = 0; i < iterations; i++) {
            Element element = new Element(i.toString(), new CacheObject());
            cache.put(element);
        }
    }

    /** Method to print the contents of the cache. */
    public final void printCache() {
        List<?> keys = cache.getKeys();
        for (Object key : keys) {
            System.out.println("Key: " + key);
            System.out.println("Value: " + cache.get(key).toString());
        }
    }

    /**
     * Get the cacheManager.
     * @return the CacheManager
     */
    public final CacheManager getCacheManager() {
    return cacheManager;
    }

    /**
     * Set the cacheManager.
     * @param cacheManager0 The CacheManager to set
     */
    public final void setCacheManager(final CacheManager cacheManager0) {
        this.cacheManager = cacheManager0;
    }
}
