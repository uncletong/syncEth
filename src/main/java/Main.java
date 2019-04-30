import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;

public class Main {

    public static void main(String[] args){
        Ethereum ethereum = EthereumFactory.createEthereum();
        ethereum.addListener(new BlockListener(ethereum, new BlockCache()));
    }
}
