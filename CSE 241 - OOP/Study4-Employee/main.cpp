#include <iostream>
#include <string>

using namespace std;

class Employee
{
public:
    Employee();
    Employee(const string& nameInfo, const string& SSNInfo);

    const string& getName()const{return name;}
    const string& getSSN()const{return SSN;}
    double getNetPay()const{return netPay;}

    void setName(const string& newNameInfo);
    void setSSN(const string& newSSNInfo);
    void setNetPay(double newNetPayInfo);

    void printInfo()const;

    virtual void printCheck()const=0;

private:
    string name;
    string SSN;
    double netPay;

};

class SalariedEmployee : public Employee
{
public:
    SalariedEmployee();
    SalariedEmployee(const string& nameInfo, const string& SSNInfo, double weeklySalaryInfo);

    double getWeeklySalary()const{return weeklySalary;}

    void setWeeklySalary(double newSalaryInfo);

    virtual void printCheck()const override;

private:
    double weeklySalary;

};

class HourlyEmployee : public Employee
{
public:
    HourlyEmployee();
    HourlyEmployee(const string& nameInfo, const string& SSNInfo, double rateInfo, double hoursInfo);

    double getRate()const{return rate;}
    double getHoursWorked()const{return hoursWorked;}

    void setRate(double newRateInfo);
    void setHoursWorked(double newHoursInfo);

    virtual void printCheck()const override;

private:
    double rate;
    double hoursWorked;

};

class BasedHourlyEmployee : public HourlyEmployee
{
public:
    BasedHourlyEmployee();
    BasedHourlyEmployee(const string& nameInfo, const string& SSNInfo, double rateInfo, double hoursInfo, double baseInfo);

    double getBase()const{return base;}

    void setBase(double newBaseInfo);

    virtual void printCheck()const final;

private:
    double base;//monthly (30h=1week, 120h=1month)

};




int main()
{
    SalariedEmployee e1;
    e1.setName("Jade");
    e1.setSSN("200104004094");
    e1.setWeeklySalary(500);
    e1.printCheck();
    cout<<endl;

    HourlyEmployee e2;
    e2.setName("Irena");
    e2.setSSN("72");
    e2.setHoursWorked(35);
    e2.setRate(22);
    e2.printCheck();
    cout<<endl;

    BasedHourlyEmployee e3("JadeIrena", "190704001", 25, 30, 3000);
    e3.printCheck();
    cout<<endl;
    cout<<endl;

    Employee* p1;
    p1=&e1;
    p1->printCheck();
    cout<<endl;
    p1=&e2;
    p1->printCheck();
    cout<<endl;

    HourlyEmployee* p2;
    p2=&e3;
    p2->printCheck();

    return 0;
}




Employee::Employee()
    :name(" "), SSN(" "), netPay(0)
{/*Intentionally left empty*/}

Employee::Employee(const string& nameInfo, const string& SSNInfo)
    :name(nameInfo), SSN(SSNInfo), netPay(0)
{/*Intentionally left empty*/}

void Employee::setName(const string& newNameInfo)
{
    name=newNameInfo;
}

void Employee::setSSN(const string& newSSNInfo)
{
    SSN=newSSNInfo;
}

void Employee::setNetPay(double newNetPayInfo)
{
    netPay=newNetPayInfo;
}

void Employee::printInfo()const
{
    cout<<"Name&Surname: "<<getName()<<endl<<
    "SSN: "<<getSSN()<<endl;
}



SalariedEmployee::SalariedEmployee()
    :Employee(), weeklySalary(0)
{
    setNetPay(getWeeklySalary());
}

SalariedEmployee::SalariedEmployee(const string& nameInfo, const string& SSNInfo, double weeklySalaryInfo)
    :Employee(nameInfo, SSNInfo)
{
    setWeeklySalary(weeklySalaryInfo);
}

void SalariedEmployee::setWeeklySalary(double newSalaryInfo)
{
    weeklySalary=newSalaryInfo;
    while(getWeeklySalary()<=0)
    {
        cout<<"Invalid salary, Please enter the weekly salary again."<<endl;
        cin>>weeklySalary;
    }
    setNetPay(getWeeklySalary());
}

void SalariedEmployee::printCheck()const
{
    Employee::printInfo();

    cout<<"Weekly Pay: "<<getNetPay()<<"$"<<endl;
}



HourlyEmployee::HourlyEmployee()
    :Employee(), rate(0), hoursWorked(0)
{
    setNetPay(getRate()*getHoursWorked());
}

HourlyEmployee::HourlyEmployee(const string& nameInfo, const string& SSNInfo, double rateInfo, double hoursInfo)
    :Employee(nameInfo, SSNInfo)
{
    setHoursWorked(hoursInfo);
    setRate(rateInfo);
}

void HourlyEmployee::setHoursWorked(double hoursInfo)
{
    hoursWorked=hoursInfo;
    while(getHoursWorked()<0)
    {
        cout<<"Invalid hours info, please enter how many hours the employee has worked again."<<endl;
        cin>>hoursWorked;
    }
    setNetPay(getHoursWorked()*getRate());
}

void HourlyEmployee::setRate(double newRateInfo)
{
    rate=newRateInfo;
    while(getRate()<=0)
    {
        cout<<"Invalid rate info, please enter the rate again."<<endl;
        cin>>rate;
    }
    setNetPay(getHoursWorked()*getRate());
}

void HourlyEmployee::printCheck()const
{
    Employee::printInfo();

    cout<<"Rate: "<<getRate()<<endl<<
    "Hours Worked: "<<getHoursWorked()<<endl<<
    "Net Pay: "<<getNetPay()<<"$"<<endl;
}



BasedHourlyEmployee::BasedHourlyEmployee()
    :HourlyEmployee(), base(0)
{
    setNetPay((getHoursWorked()*getRate())+(getBase()/(getHoursWorked()/120)));
}

BasedHourlyEmployee::BasedHourlyEmployee(const string& nameInfo, const string& SSNInfo, double rateInfo, double hoursInfo, double baseInfo)
    :HourlyEmployee(nameInfo, SSNInfo, rateInfo, hoursInfo)
{
    setBase(baseInfo);
}

void BasedHourlyEmployee::setBase(double newBaseInfo)
{
    base=newBaseInfo;
    while(base<0)
    {
        cout<<"Invalid base info, please enter the base again."<<endl;
        cin>>base;
    }
    setNetPay((getHoursWorked()*getRate())+(getBase()*(getHoursWorked()/120)));
}

void BasedHourlyEmployee::printCheck()const
{
    Employee::printInfo();

    cout<<"Rate: "<<getRate()<<endl<<
    "Hours Worked: "<<getHoursWorked()<<endl<<
    "Base: "<<getBase()<<"$"<<endl<<
    "Net Pay: "<<getNetPay()<<"$"<<endl;
}

