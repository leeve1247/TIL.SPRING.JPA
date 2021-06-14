package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    //여기 Entity Manager를 Bean에 주입
    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    //단일 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //전체 조회
    public List<Member> findAll(){
        //SQL 거의 같지만 조금 다르다
        //SQL -> data Query, JPQL -> Entity 기준
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    //이름기반 선택 조회
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
