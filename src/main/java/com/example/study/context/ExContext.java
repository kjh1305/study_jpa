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
