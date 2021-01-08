package project.team.view;

import project.team.domain.Employee;
import project.team.domain.Programmer;
import project.team.service.NameListService;
import project.team.service.TeamException;
import project.team.service.TeamService;

public class TeamView {
    //listSvc和teamSvc属性：供类中的方法使用
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    //enterMainMenu ()方法：主界面显示及控制方法。
    public void enterMainMenu(){
        boolean loopFlag = true;
        char menu = '0';

        while (loopFlag){
            if (menu != '1'){
                listAllEmployees();
            }
            System.out.println("1-团队列表  2-添加团队成员  3-删除团队成员 4-退出   请选择(1-4)：");
            menu = TSUtility.readMenuSelection();
            switch (menu){
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("是否退出（Y/N）：");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y'){
                        loopFlag = false;
                    }
                    break;
            }
        }
    }

    //listAllEmployees ()方法：以表格形式列出公司所有成员
    //getTeam()方法：显示团队成员列表操作
    //addMember ()方法：实现添加成员操作
    //deleteMember ()方法：实现删除成员操作
    private void listAllEmployees(){
        System.out.println("\n-------------------------------开发团队调度软件--------------------------------\n");

        Employee[] employees = listSvc.getAllEmployees();
        if (employees == null || employees.length == 0){
            System.out.println("公司中没有任何员工信息！");
            System.out.println("-------------------------------------------------------------------------------");
            return;
        }

        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");

        for (Employee e : employees){
            System.out.println(" " + e);
        }
        System.out.println("-------------------------------------------------------------------------------");

    }

    private void getTeam(){
        System.out.println("\n--------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if (team == null || team.length == 0){
            System.out.println("开发团队目前没有成员！");
            System.out.println("-----------------------------------------------------");
            return;
        }
        System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
        for (Programmer pro : team){
            System.out.println(" " + pro.getDetailsForTeam());
        }
        System.out.println("-----------------------------------------------------");
    }

    private void addMember(){
        System.out.println("---------------------添加成员---------------------");
        System.out.print("请输入要添加的员工ID：");
        int id = TSUtility.readInt();

        try {
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功！");
        } catch (TeamException e) {
            System.out.println("添加失败，原因：" + e.getMessage());
        }
        //按回车继续...
        TSUtility.readReturn();
    }

    private void deleteMember(){
        System.out.println("---------------------删除成员---------------------");
        System.out.print("请输入要删除员工的TID：");
        int id = TSUtility.readInt();
        System.out.print("确认是否删除(Y/N)：");
        char isDelete = TSUtility.readConfirmSelection();
        if (isDelete == 'N')
            return;
        try {
            teamSvc.removeMember(id);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因：" + e.getMessage());
        }
        // 按回车键继续...
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
