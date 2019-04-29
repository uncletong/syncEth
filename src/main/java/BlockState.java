public class BlockState {
    private String hash;
    private boolean MainChain;
    private String parentHash;
    private long number;

    public BlockState(String hash, boolean mainChain, String parentHash, long number) {
        this.hash = hash;
        MainChain = mainChain;
        this.parentHash = parentHash;
        this.number = number;
    }

    public void setMainChain(boolean mainChain) {
        MainChain = mainChain;
    }

    public String getHash() {
        return hash;
    }

    public boolean isMainChain() {
        return MainChain;
    }

    public String getParentHash() {
        return parentHash;
    }

    public long getNumber() {
        return number;
    }
}