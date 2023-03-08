package core.basesyntax.dao;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface StorageTransactionsDao {
    List<String[]> convertFileIntoList(File inputFile);

    File sentReport(String data);

    String formingReport(Map<String, Integer> info);
}
