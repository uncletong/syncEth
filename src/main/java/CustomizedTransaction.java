import org.ethereum.util.ByteUtil;

class CustomizedTransaction {

    private String hash;
    private String nonce;
    private String value;
    private String sendAddress;
    private String receiveAddress;
    private String gasPrice;
    private String gasLimit;
    private String gasUsed;

    private CustomizedTransaction(TransactionBuilder transactionBuilder){
        this.hash = transactionBuilder.hash;
        this.nonce = transactionBuilder.nonce;
        this.value = transactionBuilder.value;
        this.sendAddress = transactionBuilder.sendAddress;
        this.receiveAddress = transactionBuilder.receiveAddress;
        this.gasPrice = transactionBuilder.gasPrice;
        this.gasLimit = transactionBuilder.gasLimit;
        this.gasUsed = transactionBuilder.gasUsed;

    }

    public String getHash() {
        return hash;
    }

    public String getNonce() {
        return nonce;
    }

    public String getValue() {
        return value;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public static class TransactionBuilder{
        private String hash;
        private String nonce;
        private String value;
        private String sendAddress;
        private String receiveAddress;
        private String gasPrice;
        private String gasLimit;
        private String gasUsed;

        public TransactionBuilder hash(byte[] bytes){
            this.hash =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder nonce(byte[] bytes){
            this.nonce =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder value(byte[] bytes){
            this.value =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder sendAddress(byte[] bytes){
            this.sendAddress =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder receiveAddress(byte[] bytes){
            this.receiveAddress =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder gasPrice(byte[] bytes){
            this.gasPrice =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder gasLimit(byte[] bytes){
            this.gasLimit =  ByteUtil.toHexString(bytes);
            return this;
        }

        public TransactionBuilder gasUsed(byte[] bytes){
            this.gasUsed =  ByteUtil.toHexString(bytes);
            return this;
        }

        public CustomizedTransaction build(){
            return new CustomizedTransaction(this);
        }
    }

}
