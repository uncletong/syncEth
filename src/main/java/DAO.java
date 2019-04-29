import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DAO {
    private static MongoClient mongoClient = new MongoClient("localhost", 27017);
    private static MongoDatabase db = mongoClient.getDatabase("eth_blocks");
    private static MongoCollection<Document> collection = db.getCollection("blocks");

    public static void store(CustomizedBlock block){
        Document document = new Document("hash", block.getHeader().getHash())
                .append("parentHash", block.getHeader().getParentHash())
                .append("uncleHash", block.getHeader().getUncleHash())
                .append("coinbase", block.getHeader().getCoinbase())
                .append("stateRoot", block.getHeader().getStateRoot())
                .append("txTrieRoot", block.getHeader().getTxTrieRoot())
                .append("receiptTrieRoot", block.getHeader().getReceiptTrieRoot())
                .append("difficulty", block.getHeader().getDifficulty())
                .append("number", block.getHeader().getNumber())
                .append("timestamp", block.getHeader().getTimestamp())
                .append("gasLimit", block.getHeader().getGasLimit())
                .append("gasUsed", block.getHeader().getGasUsed())
                .append("extraData", block.getHeader().getExtraData())
                .append("mixHash", block.getHeader().getMixHash())
                .append("nonce", block.getHeader().getNonce());
        List<Map<String,String>> uncles = new ArrayList<>();
        for (CustomizedHeader uncle : block.getUncles()){
            Map<String, String> temp_uncle = new ConcurrentHashMap<>();
            temp_uncle.put("hash",uncle.getHash());
            temp_uncle.put("parentHash", uncle.getParentHash());
            temp_uncle.put("uncleHash", uncle.getUncleHash());
            temp_uncle.put("coinbase", uncle.getCoinbase());
            temp_uncle.put("stateRoot", uncle.getStateRoot());
            temp_uncle.put("txTrieRoot", uncle.getTxTrieRoot());
            temp_uncle.put("receiptTrieRoot", uncle.getReceiptTrieRoot());
            temp_uncle.put("difficulty", uncle.getDifficulty());
            temp_uncle.put("number", uncle.getNumber() + "");
            temp_uncle.put("timestamp", uncle.getTimestamp() + "");
            temp_uncle.put("gasLimit", uncle.getGasLimit());
            temp_uncle.put("gasUsed", uncle.getGasUsed()+"");
            temp_uncle.put("extraData", uncle.getExtraData());
            temp_uncle.put("mixHash", uncle.getMixHash());
            temp_uncle.put("nonce", uncle.getNonce());
            uncles.add(temp_uncle);
        }

        List<Map<String, String>> transactions = new ArrayList<>();
        for (CustomizedTransaction transaction : block.getTransactions()){
            Map<String, String> temp_transaction = new ConcurrentHashMap<>();
            temp_transaction.put("hash", transaction.getHash());
            temp_transaction.put("nonce", transaction.getNonce());
            temp_transaction.put("value", transaction.getValue());
            temp_transaction.put("sendAddress", transaction.getSendAddress());
            temp_transaction.put("receiveAddress", transaction.getReceiveAddress());
            temp_transaction.put("gasPrice", transaction.getGasPrice());
            temp_transaction.put("gasLimit", transaction.getGasLimit());
            temp_transaction.put("gasUsed", transaction.getGasUsed());
            transactions.add(temp_transaction);
        }

        document.append("uncles", uncles);
        document.append("transactions", transactions);

        collection.insertOne(document);
    }
}
