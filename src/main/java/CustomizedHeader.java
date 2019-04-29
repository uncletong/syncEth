import org.ethereum.util.ByteUtil;

public class CustomizedHeader {

    private String hash;
    private String parentHash;
    private String uncleHash;
    private String coinbase;
    private String stateRoot;
    private String txTrieRoot;
    private String receiptTrieRoot;
    private String difficulty;
    private long number;
    private long timestamp;
    private String gasLimit;
    private long gasUsed;
    private String extraData;
    private String mixHash;
    private String nonce;

    private CustomizedHeader(HeaderBuilder headerBuilder) {
        this.hash = headerBuilder.hash;
        this.parentHash = headerBuilder.parentHash;
        this.uncleHash = headerBuilder.uncleHash;
        this.coinbase = headerBuilder.coinbase;
        this.stateRoot = headerBuilder.stateRoot;
        this.txTrieRoot = headerBuilder.txTrieRoot;
        this.receiptTrieRoot = headerBuilder.receiptTrieRoot;
        this.difficulty = headerBuilder.difficulty;
        this.number = headerBuilder.number;
        this.timestamp = headerBuilder.timestamp;
        this.gasLimit = headerBuilder.gasLimit;
        this.gasUsed = headerBuilder.gasUsed;
        this.extraData = headerBuilder.extraData;
        this.mixHash = headerBuilder.mixHash;
        this.nonce = headerBuilder.nonce;

    }

    public String getHash() {
        return hash;
    }

    public String getParentHash() {
        return parentHash;
    }

    public String getUncleHash() {
        return uncleHash;
    }

    public String getCoinbase() {
        return coinbase;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public String getTxTrieRoot() {
        return txTrieRoot;
    }

    public String getReceiptTrieRoot() {
        return receiptTrieRoot;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public long getNumber() {
        return number;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public long getGasUsed() {
        return gasUsed;
    }

    public String getExtraData() {
        return extraData;
    }

    public String getMixHash() {
        return mixHash;
    }

    public String getNonce() {
        return nonce;
    }

    public static class HeaderBuilder{
        private String hash;
        private String parentHash;
        private String uncleHash;
        private String coinbase;
        private String stateRoot;
        private String txTrieRoot;
        private String receiptTrieRoot;
        private String difficulty;
        private long number;
        private long timestamp;
        private String gasLimit;
        private long gasUsed;
        private String extraData;
        private String mixHash;
        private String nonce;

        public HeaderBuilder extraData(byte[] bytes){
            this.extraData = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder mixHash(byte[] bytes){
            this.mixHash = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder nonce(byte[] bytes){
            this.nonce = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder stateRoot(byte[] bytes){
            this.stateRoot = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder gasUsed(long gasUsed){
            this.gasUsed = gasUsed;
            return this;
        }

        public HeaderBuilder gasLimit(byte[] bytes){
            this.gasLimit = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder difficulty(byte[] bytes){
            this.difficulty = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder number(long number){
            this.number = number;
            return this;
        }

        public HeaderBuilder timestamp(long timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public HeaderBuilder txTrieRoot(byte[] bytes){
            this.txTrieRoot = ByteUtil.toHexString(bytes);
            return this;
        }


        public HeaderBuilder receiptTrieRoot(byte[] bytes){
            this.receiptTrieRoot = ByteUtil.toHexString(bytes);
            return this;
        }

        public HeaderBuilder hash(byte[] hash){
            this.hash = ByteUtil.toHexString(hash);
            return this;
        }

        public HeaderBuilder parentHash(byte[] parentHash){
            this.parentHash = ByteUtil.toHexString(parentHash);
            return this;
        }

        public HeaderBuilder uncleHash(byte[] uncleHash){
            this.uncleHash = ByteUtil.toHexString(uncleHash);
            return this;
        }

        public HeaderBuilder coinbase(byte[] coinbase){
            this.coinbase = ByteUtil.toHexString(coinbase);
            return this;
        }

        public CustomizedHeader build(){
            return new CustomizedHeader(this);
        }


    }
}
