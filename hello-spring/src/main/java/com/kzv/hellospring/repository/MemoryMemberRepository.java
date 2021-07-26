package com.kzv.hellospring.repository;

import com.kzv.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //store에서 loop돌다가 filter가 member가 name이 같은걸 findAny하나라도 찾으면 반환 끝까지 돌았는데 없으면 Optional로 인해 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //member
    }

    public void clearStore(){
        store.clear();
    }
}
