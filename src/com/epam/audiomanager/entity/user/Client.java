package com.epam.audiomanager.entity.user;

import java.math.BigDecimal;
import java.util.Objects;

public class Client extends User {
    private boolean bonus;
    private BigDecimal money = BigDecimal.valueOf(500);

    public Client() {
        super();
    }

    public Client(String email, String login, String firstName, String secondName, TypeUser typeUser, boolean bonus) {
        super(login, typeUser, firstName, secondName, email);
        this.bonus = bonus;
    }

    public Client(int id, String login, TypeUser type, String firstName, String secondName, String email, boolean bonus,
                  BigDecimal money)
    {
        super(id, login, type, firstName, secondName, email);
        this.bonus = bonus;
        this.money = money;
    }

    public boolean isBonus() {
        return bonus;
    }

    public void setBonus(boolean bonus) {
        this.bonus = bonus;
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
        return isBonus() == client.isBonus() &&
                Objects.equals(getMoney(), client.getMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isBonus(), getMoney());
    }

    @Override
    public String toString() {
        return "Client{" +
                "bonus=" + bonus +
                ", money=" + money +
                '}';
    }
}
