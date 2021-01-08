package project.team.domain;

import project.team.service.Status;

public class Programmer extends Employee {
    private int memberId;   //用来记录成员加入开发团队后在团队中的ID
    private Status status = Status.FREE;    //声明三个对象属性，分别表示成员的状态。
                                            // FREE-空闲    BUSY-已加入开发团队    VOCATION-正在休假
    private Equipment equipment;    //表示该成员领用的设备

    public Programmer(){

    }

    public Programmer(int id, String name, int age, double salary, Equipment equipment) {
        super(id, name, age, salary);
        this.equipment = equipment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public String getDetailsForTeam(){
        return memberId + "/" + getId() + "\t" + getName() + "\t" + getAge() + "\t\t" + getSalary() + "\t" + "程序员";
    }
    @Override
    public String toString() {
        return getDetails() + "\t程序员\t" + status + "\t\t\t\t\t" + equipment.getDescription();
    }
}
