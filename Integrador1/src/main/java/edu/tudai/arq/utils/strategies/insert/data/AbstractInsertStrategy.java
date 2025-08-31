package edu.tudai.arq.utils.strategies.insert.data;

public abstract class AbstractInsertStrategy implements InsertStrategy {
    protected final String resourcePath;

    protected AbstractInsertStrategy(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Override
    public String getResourcePath() {
        return resourcePath;
    }
}

