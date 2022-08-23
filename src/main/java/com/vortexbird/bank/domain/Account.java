package com.vortexbird.bank.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "acco_id")
    private String accoId;

    private BigDecimal balance;

    private Boolean enable;

    private String password;

    private Long version;

    // bi-directional many-to-one association to Customer
    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    // bi-directional many-to-one association to RegisteredAccount
    @OneToMany(mappedBy = "account")
    private List<RegisteredAccount> registeredAccounts;

    // bi-directional many-to-one association to Transaction
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
}