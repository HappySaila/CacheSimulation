import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by HappySaila on 2016/09/10.
 * WLSGRA012
 */
public class CPU {
    //will generate numbers and look call for them

    //region instantiate
    private Cache l1Cache;
    private Cache l2Cache;
    private boolean isL2Cache;
    public int l1Hit = 0;
    public int l2Hit = 0;
    public int miss = 0;
    private ArrayList<Integer> data = new ArrayList<>();
    //endregion

    //region constructors
    public CPU(Cache l1Cache, Cache l2Cache, String file) {
        this.l1Cache = l1Cache;
        this.l2Cache = l2Cache;
        isL2Cache = true;
        readData(file);
    }

    public CPU(Cache cache, String file) {
        this.l1Cache = cache;
        isL2Cache = false;
        readData(file);
    }

    public CPU(String file){
        readData(file);
        miss = data.size();
    }
    //endregion

    //region methods

    private void readData(String file){
        Scanner sc = null;
        try {
            sc = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNext()){
            data.add(Utils.toDecimal(sc.nextLine()));//convert the hex value to a decimal
        }
        if (l1Cache!=null){
            for (int i : data) {
                if(isL2Cache){
                    handleL2(i);
                }else{
                    handleL1(i);
                }
            }
        }
    }

    private void handleL2(int i){
        //will increment hits and misses accordingly and manage caches accordingly
        if (l1Cache.hasVal(i)){
            l1Hit++;
        }
        else if (l2Cache.hasVal(i)){
            l2Hit++;
//          copy value from l2cache to l1 cache
            l1Cache.setBlock(i);
        }
        else{
            //value is not in cache and needs to be copied from RAM
            miss++;
//          put value in both caches
            l1Cache.setBlock(i);
            l2Cache.setBlock(i);
        }
    }
    private void handleL1(int i){
        //will increment hits and misses accordingly and manage caches accordingly
        if (l1Cache.hasVal(i)){
            l1Hit++;
        }
        else{
            //value is not in cache and needs to be copied from RAM
            miss++;
//          put value in both caches
            l1Cache.setBlock(i);
        }
    }

    public int getCost(){
        return (l1Hit*10+l2Hit*100+miss*1000)/(l1Hit+l2Hit+miss);
    }
    //endregion

}
