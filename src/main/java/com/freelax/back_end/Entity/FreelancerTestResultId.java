package com.freelax.back_end.Entity;

import java.io.Serializable;

public class FreelancerTestResultId implements Serializable {

    private Long userId;
    private Long testId;

    // Default constructor
    public FreelancerTestResultId() {}

    public FreelancerTestResultId(Long userId, Long testId) {
        this.userId = userId;
        this.testId = testId;
    }

    // Getters, setters, equals, and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreelancerTestResultId that = (FreelancerTestResultId) o;

        if (!userId.equals(that.userId)) return false;
        return testId.equals(that.testId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + testId.hashCode();
        return result;
    }
}
