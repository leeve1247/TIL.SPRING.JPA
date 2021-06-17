package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    //관계형 db 는 다대다일 경우 중간 매핑이 있지만, 실전에서 못쓴다. 변형하는 일이 없고..
    //JPA 에서는 이런게 된다 보여주려는 용도

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //데이터가 있는곳에 메서드가 있는 것이 응집력이 높아 관리가 편해서 좋다.

    /**
     * stock 증가
     */
    public void addstock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void addstock(int quantity){
        int restStock = this.stockQuantity -= quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock;
    }
}
