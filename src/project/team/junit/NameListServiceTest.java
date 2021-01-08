package project.team.junit;

import org.junit.Test;
import project.team.domain.Employee;
import project.team.service.NameListService;

public class NameListServiceTest {
    @Test
    public void testGetAllEmployees(){
        NameListService nameListService = new NameListService();

        Employee[] employees = nameListService.getAllEmployees();

        for (int i = 0; i < employees.length; i++){
            System.out.println(employees[i].toString());
        }
    }

    @Test
    public void testGetEmployee(){
        int id = 20;

        NameListService nameListService = new NameListService();
        try {
            Employee employee = nameListService.getEmployee(id);
            System.out.println(employee);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
