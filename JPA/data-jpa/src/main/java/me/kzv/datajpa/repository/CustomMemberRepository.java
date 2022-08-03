package me.kzv.datajpa.repository;

import me.kzv.datajpa.entity.Member;

import java.util.List;

public interface CustomMemberRepository {

    List<Member> findMemberCustom();
}
