package edu.tudai.arq.utils.strategies.insert.data;

public interface InsertStrategy {
    String getResourcePath();
    void insert(String[] campos, int lineNo);
}
