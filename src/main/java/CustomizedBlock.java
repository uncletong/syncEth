import org.ethereum.core.BlockHeader;

import java.util.List;

//this is the class that record the information that we need.
public class CustomizedBlock {

    //block header
    private CustomizedHeader header;
    //transactions
    private List<CustomizedTransaction> transactions;
    //uncles
    private List<CustomizedHeader> uncles;

    public CustomizedBlock(CustomizedHeader header, List<CustomizedTransaction> transactions, List<CustomizedHeader> uncles) {
        this.header = header;
        this.transactions = transactions;
        this.uncles = uncles;
    }

    public CustomizedHeader getHeader() {
        return header;
    }

    public List<CustomizedTransaction> getTransactions() {
        return transactions;
    }

    public List<CustomizedHeader> getUncles() {
        return uncles;
    }
}
