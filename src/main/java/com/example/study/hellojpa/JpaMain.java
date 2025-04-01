package com.example.study.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        try {
            // 하나만 만들어야됨
            EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("hello");

            // 트랜잭션 단위로 사용
            EntityManager entityManager = managerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin(); // 트랜잭션 시작

            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            entityManager.persist(member); // 멤버 저장

            transaction.commit();

            entityManager.close();

            managerFactory.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
