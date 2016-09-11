/**
 * Created by HappySaila on 2016/09/10.
 * WLSGRA012
 */
public class CPU {
    //will generate numbers and look call for them
    private Cache cache;
    private Cache l2Cache;
    private boolean isL2Cache;
    private int hit = 0;
    private int cacheMiss = 0;
    private int l2CacheMiss = 0;

    public CPU(Cache cache, Cache l2Cache) {
        this.cache = cache;
        this.l2Cache = l2Cache;
        isL2Cache = true;
    }

    public CPU(Cache cache) {
        this.cache = cache;
        isL2Cache = false;
    }

    public void get(String hex){
        //convert hex to decimal
        //check cache
        //check l2Cache
    }


}
