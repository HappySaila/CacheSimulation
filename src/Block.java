/**
 * Created by HappySaila on 2016/09/09.
 */
public class Block {
    private int tag;
    private int index;
    private String data;
    private boolean valid;

    public Block(int tag, int index, boolean valid) {
        this.tag = tag;
        this.valid = valid;
        this.index = index;
    }

    public boolean isEqual(Block block){
        return this.tag==block.tag;
    }



}
