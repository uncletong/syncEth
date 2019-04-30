import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

class BlockCache {

    private BlockState bestBlock;
    //data to be stored, all of blocks in data belongs to mainchain.
    private Map<String, CustomizedBlock> data;
    //index records the main chain information to help choose the fork.
    private Map<Long, List<BlockState>> index;
    //cacheBlock method get data in blockQueue to choose the main chain.
    private BlockingQueue<CustomizedBlock> blockQueue= new LinkedBlockingDeque(Integer.MAX_VALUE);
    //processBlock method get data in to toFlush to store in the database.
    private BlockingQueue<CustomizedBlock> toFlush = new LinkedBlockingDeque(Integer.MAX_VALUE);
    //the size of the cache to choose the main chain
    private static final int FLUSH_SIZE_LIMIT = 12;

    private Thread blockCacheService;
    private Thread blockProcessService;

    private Runnable cacheBlockTask = new Runnable() {
        @Override
        public void run() {
            cacheBlock();
        }
    };

    private Runnable processBlockTask = new Runnable() {
        @Override
        public void run() {
            processBlock();
        }
    };

    public BlockCache() {
        this.data = new ConcurrentHashMap<>();
        this.index = new ConcurrentHashMap<>();
        init();
    }

    public void init(){
        blockCacheService = new Thread(cacheBlockTask, "cacheBlockThread");
        blockProcessService = new Thread(processBlockTask, "processBlockThread");
        blockProcessService.start();
        blockCacheService.start();
    }

    void add(CustomizedBlock custBlock) {
        blockQueue.add(custBlock);
    }

    private void cacheBlock(){
        while (!Thread.interrupted()){
            try {
                CustomizedBlock block  = blockQueue.take();
                data.put(block.getHeader().getHash(), block);
                BlockState blockState = new BlockState(block.getHeader().getHash(), true, block.getHeader().getParentHash(), block.getHeader().getNumber());

                if (!index.containsKey(block.getHeader().getNumber())){
                    index.put(block.getHeader().getNumber(), Collections.singletonList(blockState));
                    //TODO check the main chain and rebuild the branch
                } else{
                    //TODO check the main chain and rebuild the branch
                    index.get(block.getHeader().getNumber()).add(blockState);
                }
                bestBlock = blockState;

                //when the cache of state arrive 12(LIMIT_SIZE), then the main chain is ensured. call toFlush().
                if (index.size() > FLUSH_SIZE_LIMIT){
                    toFlush();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private void processBlock(){
        while (!Thread.interrupted()){
            try {
              CustomizedBlock block = toFlush.take();
              DAO.store(block);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void toFlush() {
        long number = bestBlock.getNumber() - FLUSH_SIZE_LIMIT;

        if (index.containsKey(number)){
            List<BlockState> states = index.get(number);

            for (BlockState s : states){
                if (s.isMainChain()){
                    toFlush.add(data.get(s.getHash()));
                }
                data.remove(s.getHash());
            }
            index.remove(number);
        }
    }


}
