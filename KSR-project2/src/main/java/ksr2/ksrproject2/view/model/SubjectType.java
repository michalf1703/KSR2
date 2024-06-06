package ksr2.ksrproject2.view.model;

public enum SubjectType {

    Weight_120_plus("Weight class 120+", "Weight class 120+"),
    Weight_80_120("Weight class 86-100", "Weight class 86-100");


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
