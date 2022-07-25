package me.whiteship.inflearnthejavatest;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;

    private int limit;
    private String name;

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit 는 0 보다 커야 한다.");
        }
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Study{" +
            "status=" + status +
            ", limit=" + limit +
            ", name='" + name + '\'' +
            '}';
    }
}
