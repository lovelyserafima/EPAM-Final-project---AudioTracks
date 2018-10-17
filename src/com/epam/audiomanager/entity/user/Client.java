package com.epam.audiomanager.entity.user;

import java.math.BigDecimal;
import java.util.Objects;

public class Client extends User {
    private BigDecimal money;

    public Client() {
        super();
    }

    public Client(String email, String login, String firstName, String secondName, TypeUser typeUser) {
        super(login, typeUser, firstName, secondName, email);
    }

    public Client(int id, String login, TypeUser type, String firstName, String secondName, String email,
                  BigDecimal money) {
        super(id, login, type, firstName, secondName, email);
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(getMoney(), client.getMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMoney());
    }

    @Override
    public String toString() {
        return "Client{" +
                "money=" + money +
                '}';
    }
}
