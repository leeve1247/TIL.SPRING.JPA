package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    //EnumType 은 Ordinal 쓰지 마세요... python의 list와 dict 을 상상해보십시오
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}
