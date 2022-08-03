package me.kzv.datajpa.repository;

public interface NestedClosedProjections {

    String getUsername();

    TeamInfo getTeam();

    interface TeamInfo {

        String getName();
    }
}