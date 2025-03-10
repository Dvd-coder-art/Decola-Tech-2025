package br.com.dio;

import br.com.dio.persistence.EmployeeDAO;
import br.com.dio.persistence.EmployeeAuditDAO;
import br.com.dio.persistence.EmployeeParamDAO;
import br.com.dio.persistence.entity.EmployeeEntity;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Main {

    private final static EmployeeParamDAO employeeDAO= new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDao = new EmployeeAuditDAO();

    public static void main(String[] args) {
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbc", "root", "David_1221!")
                .load();
        flyway.migrate();

        var insert = new EmployeeEntity();
        insert.setName("David'");
        insert.setSalary(new BigDecimal("2800"));
        insert.setBirthday(OffsetDateTime.now().minusYears(30));
        System.out.println(insert);
        employeeDAO.insertWithProcedure(insert);
        System.out.println(insert);

        //employeeDAO.findAll().forEach(System.out::println);

        //System.out.println(employeeDAO.findById(1));

        var update = new  EmployeeEntity();
        update.setId(insert.getId());
        update.setName("Gabriel");
        update.setSalary(new BigDecimal("5500"));
        update.setBirthday(OffsetDateTime.now().minusYears(36).minusDays(7));
        employeeDAO.update(update);

        employeeDAO.delete(insert.getId());

        employeeAuditDao.findAll().forEach(System.out::println);
    }

}
