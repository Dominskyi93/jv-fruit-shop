package core.basesyntax.strategy;

import core.basesyntax.model.Operation;
import core.basesyntax.db.StorageTransactions;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculationServiceImpl implements CalculationService {
    @Override
    public void initializationStorage(List<String[]> convertedFileIntoList) {
        Map<String, Integer> startBalance = convertedFileIntoList.stream()
                .filter(i -> i[0].equals(Operation.BALANCE.getCode()))
                .collect(Collectors.toMap(i -> i[1], i -> Integer.parseInt(i[2])));
        StorageTransactions.setStorage(startBalance);
    }

    @Override
    public void calculation(List<String[]> convertedFileIntoList) {
        for (String[] transaction : convertedFileIntoList) {
            if (transaction[0].equals(Operation.SUPPLY.getCode())) {
                int tempAmount = Integer.parseInt(transaction[2])
                        + StorageTransactions.getStorage().get(transaction[1]);
                StorageTransactions.getStorage().put(transaction[1], tempAmount);
            } else if (transaction[0].equals(Operation.RETURN.getCode())) {
                int tempAmount = Integer.parseInt(transaction[2])
                        + StorageTransactions.getStorage().get(transaction[1]);
                StorageTransactions.getStorage().put(transaction[1], tempAmount);
            } else if (transaction[0].equals(Operation.PURCHASE.getCode())) {
                int amount = StorageTransactions.getStorage().get(transaction[1])
                        - Integer.parseInt(transaction[2]);
                StorageTransactions.getStorage().put(transaction[1], amount);
            }
        }
    }
}
