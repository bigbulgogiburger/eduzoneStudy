package org.example;

import org.example.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            logic(em);

            //해당 시점에서 flush()가 호출된다.
            tx.commit();

        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {

            em.close();
        }
        emf.close();

    }

    private static void logic(EntityManager em){

        // 비영속 상태
        Member member = new Member();
        member.setId("1");
        member.setAge(20);
        member.setUsername("23");

        //영속성 컨텍스트에 등록(영속 상태)
        em.persist(member);


        //준영속 상태로 변경
        em.detach(member);

        //영속 상태로 변경
        em.merge(member);





        member.setAge(300);
        //수정 시에 엔티티 매니저의 업데이트 메소드 사용..?;

        //한건조회(영속 상태)
        Member findMember = em.find(Member.class, member.getId());

        //엔티티 생성
        Member member1 = new Member("mem1","편도훈",21);
        Member member2 = new Member("mem2","편도",22);
        Member member3 = new Member("mem3","편",23);

        //영속성 컨텍스트에 저장
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        //JPQL을 실행하여, 실제로 db에 쿼리를 날릴 때에, 위의 사항들은 db에 커밋되지 않은 상태.
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();




        em.remove(member);

    }
}