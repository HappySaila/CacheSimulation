/**
 * Created by HappySaila on 2016/09/09.
 * WLSGRA012
 */
public abstract class Cache {
//    parent of all cache objects
    private int numberBlocks;
    private Block[] cache;

    public Cache(int numberBlocks) {
        cache = new Block[numberBlocks];
    }
}
