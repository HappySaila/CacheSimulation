/**
 * Created by HappySaila on 2016/09/09.
 * WLSGRA012
 */
public class Cache {
//    parent of all cache objects
    private Integer[][] cache;
    private int bytes;//the size of an individual block
    private int numberBlocks;

    public Cache(int numberBlocks, int bytes) {
        cache = new Integer[numberBlocks][bytes];
        this.numberBlocks = numberBlocks;
        this.bytes = bytes;
    }

    public boolean hasVal(int i){
//        will return true if the value is in the cache
        int index = i%numberBlocks;
        int offset = (i/numberBlocks)%bytes;
        Integer value = cache[index][offset];
        if (cache[index][offset]!=null){//checking if there is  value in that block
            if(value==i){
                return true;
            }
        }
        return false;
    }

    public void setBlock(int i){
        int index = i%numberBlocks;
        int offset = (i/numberBlocks)%bytes;
        cache[index][offset]=i;
    }
}
