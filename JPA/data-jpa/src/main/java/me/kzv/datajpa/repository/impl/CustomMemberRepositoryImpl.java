package me.kzv.datajpa.repository.impl;

import lombok.RequiredArgsConstructor;
import me.kzv.datajpa.entity.Member;
import me.kzv.datajpa.repository.CustomMemberRepository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class CustomMemberRepositoryImpl implements CustomMemberRepository {

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }
}
