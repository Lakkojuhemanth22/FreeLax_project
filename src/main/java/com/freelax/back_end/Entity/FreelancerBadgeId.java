package com.freelax.back_end.Entity;

import java.io.Serializable;

public class FreelancerBadgeId implements Serializable {

    private Long userId;
    private Long badgeId;

    // Default constructor
    public FreelancerBadgeId() {}

    public FreelancerBadgeId(Long userId, Long badgeId) {
        this.userId = userId;
        this.badgeId = badgeId;
    }

    // Getters, setters, equals, and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreelancerBadgeId that = (FreelancerBadgeId) o;

        if (!userId.equals(that.userId)) return false;
        return badgeId.equals(that.badgeId);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + badgeId.hashCode();
        return result;
    }
}
