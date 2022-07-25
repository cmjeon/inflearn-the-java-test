package me.whiteship.inflearnthejavatest;

public class Study {

    private StudyStatus status = StudyStatus.DRAFT;

    private int limit;

    public Study(int limit) {
        if(limit < 0) {
            throw new IllegalArgumentException("limit 는 0 보다 커야 한다.");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }
}
