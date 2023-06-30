package ru.sanctio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {

    @Id
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "passport_number")
    private int passportNymber;

    public Passport() {
    }

    public Passport(int passportNymber) {
        this.passportNymber = passportNymber;
    }

    public Passport(Person person, int passportNymber) {
        this.person = person;
        this.passportNymber = passportNymber;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportNymber() {
        return passportNymber;
    }

    public void setPassportNymber(int passportNymber) {
        this.passportNymber = passportNymber;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "person=" + person +
                ", passportNymber=" + passportNymber +
                '}';
    }
}
