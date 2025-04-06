package com.example.study.context;

import com.example.study.hellojpa.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ExContext {
    public static void main(String[] args) {
        EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = managerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // find 시 영속성 컨텍스트에 1차캐시에 존재x 시 -> DB에서 조회 후 1차캐시에 저장
            // find 시 영속성 컨텐스트 1차캐시에 존재시 -> DB에서 조회x 쿼리 안날라감
            Member member1 = entityManager.find(Member.class, 1);
            Member member2 = entityManager.find(Member.class, 1);
            System.out.println("member1 == member2 : " + (member1 == member2)); // true
            System.out.println("member1 == member2 : " + (member1.equals(member2))); // true

            // 비영속
            Member member = new Member();
            member.setId(3L);
            member.setName("HelloC");

            // 영속
            entityManager.persist(member);

            // 준영속 (영속성 컨텍스트에서 분리)
            entityManager.detach(member);

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        managerFactory.close();
    }
}
