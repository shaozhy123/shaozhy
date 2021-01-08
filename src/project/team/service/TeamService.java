package project.team.service;

import project.team.domain.Architect;
import project.team.domain.Designer;
import project.team.domain.Employee;
import project.team.domain.Programmer;

public class TeamService {
    private int counter = 1;    //用来为开发团队新增成员自动生成团队中的唯一ID
    private final int MAX_MEMBER = 5;   //表示开发团队最大成员数
    private Programmer[] team = new Programmer[MAX_MEMBER]; //用来保存当前团队中的各成员对象
    private int total;  //记录团队成员的实际人数

    public Programmer[] getTeam(){
        Programmer[] team = new Programmer[total];
        for (int i = 0; i < total; i++){
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException {
        //失败信息包含以下几种：
        //成员已满，无法添加
        if (total >= MAX_MEMBER){
            throw new TeamException("成员已满，无法添加");
        }
        //该成员不是开发人员，无法添加
        if (!(e instanceof Programmer)){
            throw new TeamException("该成员不是开发人员，无法添加");
        }
        //该员工已在本开发团队中
        if (isExist(e)){
            throw new TeamException("该员工已在本开发团队中");
        }
        //该员工已是某团队成员
        if (Status.BUSY.equals(((Programmer) e).getStatus().getSTATE())){
            throw new TeamException("该员工已是某团队成员");
        }
        //该员正在休假，无法添加
        if (Status.VOCATION.equals(((Programmer) e).getStatus().getSTATE())){
            throw new TeamException("该员正在休假，无法添加");
        }
        //团队中至多只能有一名架构师
        //团队中至多只能有两名设计师
        //团队中至多只能有三名程序员
        int numOfArc = 0,numOfDes = 0, numOfPro = 0;
        for (int i = 0; i < total; i++){
            if (team[i] instanceof Architect){
                numOfArc++;
            } else if (team[i] instanceof Designer){
                numOfDes++;
            } else if (team[i] instanceof Programmer){
                numOfPro++;
            }
        }

        if (e instanceof Architect){
            if (numOfArc >= 1){
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (e instanceof Designer){
            if (numOfDes >= 2){
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (e instanceof Programmer){
            if (numOfPro >= 3){
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }

        //将e添加到team中
        team[total++] = (Programmer) e;
        ((Programmer) e).setStatus(Status.BUSY);
        ((Programmer) e).setMemberId(counter++);
    }

    private boolean isExist(Employee e){
        for (int i = 0; i < total; i++){
            if (team[i].getId() == e.getId()){
                return true;
            }
        }
        return false;
    }

    public void removeMember(int memberId) throws TeamException {
        int i = 0;
        for (; i < total; total++){
            if (team[i].getMemberId() == memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        //未找到指定元素
        if (i == total){
            throw new TeamException("未找到指定员工，删除失败！");
        }
        //后一个覆盖前一个
        for (int j = i + 1; j < total; j++){
            team[j - 1] = team[j];
        }
        //写法一：
//        team[total - 1] = null;
//        total--;
        //写法二：
        team[--total] = null;
    }
}
