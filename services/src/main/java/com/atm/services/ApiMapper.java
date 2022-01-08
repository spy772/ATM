package com.atm.services;

import com.atm.model.Account;
import com.atm.model.Client;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ApiMapper {

    @Select("SELECT * " +
            "FROM accounts " +
            "WHERE clientid = #{clientId}")
    List<Account> getClientById(@Param("clientId") int clientId);

    @Select("SELECT * " +
            "FROM accounts " +
            "WHERE accounttype = 'SAVINGS'")
    List<Account> getAllSavingsAccounts();

    @Select("SELECT * " +
            "FROM accounts " +
            "WHERE accounttype = 'CHECKING'")
    List<Account> getAllCheckingAccounts();

    @Select("SELECT * " +
            "FROM accounts " +
            "WHERE accountid = #{accountId}")
    Account getAccountById(@Param("accountId") int accountId);

    @Update("UPDATE accounts " +
            "SET balance=#{balance}, numoftransactions=#{numOfTransactions}" +
            "WHERE accountid = #{accountId}")
    int updateClient(Account account);

    @Insert("INSERT INTO accounts (clientid, balance, accounttype, numoftransactions)" +
            "VALUES (#{clientId}, 0, #{accountType}, 0)")
    @Options(useGeneratedKeys = true, keyProperty="accountId")
    int createNewAccount(Account account);

    @Insert("INSERT INTO clients (firstname) " +
            "VALUES (#{firstName})")
    @Options(useGeneratedKeys = true, keyProperty="clientId")
    int createNewClient(Client client);
}
