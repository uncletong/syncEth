import org.ethereum.core.Block;
import org.ethereum.core.BlockHeader;
import org.ethereum.core.TransactionReceipt;
import org.ethereum.facade.Ethereum;
import org.ethereum.listener.EthereumListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class BlockListener extends EthereumListenerAdapter {

    private Ethereum ethereum;
    private BlockCache container;

    public BlockListener(Ethereum ethereum, BlockCache container){
        this.ethereum = ethereum;
        this.container = container;
    }

    //override the onBlock method to add the block to the cache when ethereumj recieve a block.
    @Override
    public void onBlock(Block block, List<TransactionReceipt> receipts) {
        super.onBlock(block, receipts);
        //build header, transactions and uncles
        CustomizedHeader header = buildHeader(block);
        List<CustomizedTransaction> transactions = buildTransactions(receipts);
        List<CustomizedHeader > uncles = buildUncles(block);

        CustomizedBlock custBlock = new CustomizedBlock(header, transactions, uncles);
        container.add(custBlock);

    }

    private List<CustomizedHeader> buildUncles(Block block) {
        List<CustomizedHeader> uncles = new ArrayList<>();
        for (BlockHeader header : block.getUncleList()){
            CustomizedHeader unlce = new CustomizedHeader.HeaderBuilder()
                    .hash(header.getHash())
                    .parentHash(header.getParentHash())
                    .uncleHash(header.getUnclesHash())
                    .coinbase(header.getCoinbase())
                    .stateRoot(header.getStateRoot())
                    .txTrieRoot(header.getTxTrieRoot())
                    .receiptTrieRoot(header.getReceiptsRoot())
                    .difficulty(header.getDifficulty())
                    .timestamp(header.getTimestamp())
                    .number(header.getNumber())
                    .gasLimit(header.getGasLimit())
                    .gasUsed(header.getGasUsed())
                    .extraData(header.getExtraData())
                    .mixHash(header.getMixHash())
                    .nonce(header.getNonce())
                    .build();
            uncles.add(unlce);
        }
        return uncles;

    }

    private CustomizedHeader buildHeader(Block block){
        return new CustomizedHeader.HeaderBuilder()
                .hash(block.getHash())
                .parentHash(block.getParentHash())
                .uncleHash(block.getUnclesHash())
                .coinbase(block.getCoinbase())
                .stateRoot(block.getStateRoot())
                .txTrieRoot(block.getTxTrieRoot())
                .receiptTrieRoot(block.getReceiptsRoot())
                .difficulty(block.getDifficulty())
                .timestamp(block.getTimestamp())
                .number(block.getNumber())
                .gasLimit(block.getGasLimit())
                .gasUsed(block.getGasUsed())
                .extraData(block.getExtraData())
                .mixHash(block.getMixHash())
                .nonce(block.getNonce())
                .build();
    }

    private List<CustomizedTransaction> buildTransactions(List<TransactionReceipt> receipts){
        List<CustomizedTransaction> transactions = new ArrayList<>();
        for (TransactionReceipt transactionReceipt : receipts){
            CustomizedTransaction transaction = new CustomizedTransaction.TransactionBuilder()
                    .hash(transactionReceipt.getTransaction().getHash())
                    .nonce(transactionReceipt.getTransaction().getNonce())
                    .value(transactionReceipt.getTransaction().getValue())
                    .receiveAddress(transactionReceipt.getTransaction().getReceiveAddress())
                    .sendAddress(transactionReceipt.getTransaction().getSender())
                    .gasPrice(transactionReceipt.getTransaction().getGasPrice())
                    .gasLimit(transactionReceipt.getTransaction().getGasLimit())
                    .gasUsed(transactionReceipt.getGasUsed())
                    .build();
            transactions.add(transaction);
        }
        return transactions;
    }
}
