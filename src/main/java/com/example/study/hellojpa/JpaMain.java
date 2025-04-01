package com.example.study.hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
            // 하나만 만들어야됨
            EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("hello");

            // 트랜잭션 단위로 사용
            EntityManager entityManager = managerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin(); // 트랜잭션 시작
        try {
            // 조회
            //  entityManager.find(Member.class, 1L);

            // 조건 조회 JPQL
            List<Member> selectMFromMember = entityManager.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : selectMFromMember) {
                System.out.println("member.getName() = " + member.getName());
            }

            // 저장
            //  Member member = new Member();
            //  member.setId(1L);
            //  member.setName("HelloA");
            //  entityManager.persist(member); // 멤버 저장

            // 업데이트
            Member member = entityManager.find(Member.class, 1L);
            member.setName("Jack"); // persist 안해도 업데이트 됨

            // 삭제
            // entityManager.remove(entityManager.find(Member.class, 1L)); // 삭제

            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        } finally {
            entityManager.close(); // 커넥션을 물고 사용하기 때문에 꼭 닫아줘야함
        }
            managerFactory.close();
    }
}
