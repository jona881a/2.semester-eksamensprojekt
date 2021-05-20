package com.examsproject.nordicmotorhome.Repository;

import com.examsproject.nordicmotorhome.Model.Customer;
import com.examsproject.nordicmotorhome.Model.CustomerDebt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDebtRepo {

    @Autowired
    JdbcTemplate template;

    public List<CustomerDebt> fetchAll() {
        String sql = "SELECT * FROM customersdebt";
        RowMapper<CustomerDebt> customersDebt = new BeanPropertyRowMapper<>();

        return template.query(sql,customersDebt);
    }

    public CustomerDebt createCustomerDebt(CustomerDebt c) {
        String sql = "INSERT INTO customersdebt(customerdebtID,contractID,contractstartdate,contractenddate," +
                "waccancelled,cancellationdate,dayssincecancellation,totalprice) VALUES(?,?,?,?,?,?,?,?)";

        template.update(sql,c.getCustomerDebtID(),c.getContractID(),c.getContractStartDate(),c.getContractEndDate(),
                c.getWasCancelled(),c.getCancellationDate(),c.getDaysSinceCancellation(),c.getTotalPrice());

        return c;
    }

    public CustomerDebt findCustomerDebtByID(int customerDebtID) {
        String sql = "SELECT * FROM customersdebt WHERE customerdebtID = ?";
        RowMapper<CustomerDebt> customerDebt = new BeanPropertyRowMapper<>();
        CustomerDebt c = template.queryForObject(sql,customerDebt);

        return c;
    }

    public boolean deleteCustomerDebt(int customerDebtID) {
        String sql = "DELETE FROM customersdebt WHERE customerdebtID = ?";

        return template.update(sql,customerDebtID) < 0;
    }

    public CustomerDebt updateCustomerDebt(int customerDebtID, CustomerDebt c) {
        String sql = "UPDATE customersdebt SET customerdebtID = ?,contractID = ?,contractstartdate = ?,contractenddate = ?," +
                "waccancelled = ?,cancellationdate = ?,dayssincecancellation = ?,totalprice = ?) WHERE customerdebtid = ?";
        template.update(sql,c.getCustomerDebtID(),c.getContractID(),c.getContractStartDate(),c.getContractEndDate(),
                c.getWasCancelled(),c.getCancellationDate(),c.getDaysSinceCancellation(),c.getTotalPrice());
        return c;
    }
}
