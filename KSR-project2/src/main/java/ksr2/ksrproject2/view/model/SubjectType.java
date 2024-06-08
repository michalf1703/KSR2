package ksr2.ksrproject2.view.model;

public enum SubjectType {

    Weight_120_plus("Junior", "Junior"),
    Weight_80_120("Open", "Open");


    private final String name;
    private final String dbName;

    SubjectType(String name, String dbName) {
        this.name = name;
        this.dbName = dbName;
    }
    public String getDbName() {
        return dbName;
    }
    @Override
    public String toString() {
        return name;
    }
}
