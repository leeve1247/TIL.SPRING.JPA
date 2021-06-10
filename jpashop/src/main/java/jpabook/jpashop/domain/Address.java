package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //JPA 특성상 만들어 놓았구나, 기본 생성자

    protected Address(){
    }

    //생성할 때만 딱 값이 생성되고, 수정이 되어선 아니됩니다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
