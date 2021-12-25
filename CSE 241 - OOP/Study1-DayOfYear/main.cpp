#include <iostream>

using namespace std;

/*Has a problem with decrement like dayofyear-1 doesnt work properly all the time*/

class DayOfYear{
public:
    //Constructors
    DayOfYear();
    DayOfYear(int dayValue);
    DayOfYear(int dayValue, int monthValue);
    DayOfYear(int dayValue, int monthValue, int yearValue);

    void setDay();
    void setMonth();

    //Getters
    int getDay()const{return day;}
    int getMonth()const{return month;}
    int getYear()const{return year;}

    //Overloaded Operators
    bool operator==(const DayOfYear& other)const;
    const DayOfYear operator+(const DayOfYear& other)const;
    const DayOfYear operator-(const DayOfYear& other)const;
    DayOfYear operator--();
    DayOfYear operator++(int ignore);
    friend ostream& operator<<(ostream& outputStream, const DayOfYear& writtenDay);
    friend istream& operator>>(istream& inputStream, DayOfYear& readDay);

private:
    int day;
    int month;
    int year;
};

class Holiday
{
public:
    Holiday();
    Holiday(int dayValue, int monthValue, int yearValue);

    DayOfYear getDate()const{return date;}
private:
    DayOfYear date;
};

int main()
{
    DayOfYear day1(1, 3, 2000);
    DayOfYear day2;
    DayOfYear day3(13, 07, 2000);
    DayOfYear day4(20, 06, 2020);
    DayOfYear day5(7, 12, 2020);
    DayOfYear day6(30, 12, 2020);
    DayOfYear day7(30, 12, 2020);

    cout<<day1<<endl;
    cout<<day2<<endl;
    cout<<day3<<endl;
    cout<<day4<<endl;
    cout<<day5<<endl;
    cout<<day6<<endl;
    cout<<endl;

    day6++;
    cout<<"Increment: "<<day6<<endl;
    cout<<endl;

    cout<<"Addition: day1+day7 "<<day1+day7<<endl;
    cout<<endl;

    cout<<"Substraction: day1-day7 "<<day1-day7<<endl;
    cout<<"Substraction: day7-day1 "<<day7-day1<<endl;
    cout<<endl;

    cin>>day5;
    cout<<day5<<endl;
    cout<<endl;

    Holiday hday1(29,9,2020);
    cout<<hday1.getDate();
    cout<<endl;

    --day6;
    cout<<"Decrement: "<<day6<<endl;
    cout<<endl;

    return 0;
}

DayOfYear::DayOfYear()
    :day(1), month(1), year(2020)
{/*Intentionally left empty*/}

DayOfYear::DayOfYear(int dayValue)
    :day(dayValue), month(0), year(0)
{
    setDay();
}

DayOfYear::DayOfYear(int dayValue, int monthValue)
    :day(dayValue), month(monthValue), year(2020)
{
    setMonth();
    setDay();
}

DayOfYear::DayOfYear(int dayValue, int monthValue, int yearValue)
    :day(dayValue), month(monthValue), year(yearValue)
{
    setMonth();
    setDay();
}

void DayOfYear::setDay()
{
    while(getDay()<1 || getDay()>30)
    {
        cout<<"You have entered an invalid day value. Please enter again."<<endl;
        cin>>day;
    }
}

void DayOfYear::setMonth()
{
    while(getMonth()<1 || getMonth()>12)
    {
        cout<<"You have entered an invalid month value. Please enter again."<<endl;
        cin>>month;
    }
}

bool DayOfYear::operator==(const DayOfYear& other)const
{
    if(getDay()==other.getDay() && getMonth()==other.getMonth() && getYear()==other.getYear())
        return true;
    else
        return false;
}

const DayOfYear DayOfYear::operator+(const DayOfYear& other)const
{
    int newDay;
    int newMonth;
    int newYear;
    int extra=0;

    newDay=getDay()+other.getDay();
    if(newDay>30)
    {
        extra=newDay/30;
        newDay=newDay-30*extra;
    }

    newMonth=getMonth()+other.getMonth()+extra;
    extra=0;
    if(newMonth>12)
    {
        extra=newMonth/12;
        newMonth=newMonth-12*extra;
    }

    newYear=getYear()+other.getYear()+extra;

    DayOfYear temp(newDay, newMonth, newYear);

    return temp;
}

const DayOfYear DayOfYear::operator-(const DayOfYear& other)const
{
    int newDay;
    int newMonth;
    int newYear;
    int tempDay=day;
    int tempMonth=month;
    int tempYear=year;

    if(other.getDay()>getDay())
    {
        tempMonth-=1;
        tempDay+=30;
    }
    newDay=tempDay-other.getDay();

    if(other.getMonth()>tempMonth)
    {
        tempYear-=1;
        tempMonth+=12;
    }
    newMonth=tempMonth-other.getMonth();

    newYear=tempYear-other.getYear();

    DayOfYear temp(newDay, newMonth, newYear);

    return temp;
}

DayOfYear DayOfYear::operator--()
{
    *this=*this-1;
    DayOfYear temp=*this;

    return temp;
}

DayOfYear DayOfYear::operator++(int ignore)
{
    DayOfYear temp=*this;
    *this=*this+1;

    return temp;
}

ostream& operator<<(ostream& outputStream, const DayOfYear& writtenDay)
{
    if(writtenDay.getDay()<10)
        outputStream<<"0"<<writtenDay.getDay();
    else
        outputStream<<writtenDay.getDay();

    if(writtenDay.getMonth()<10)
        outputStream<<"/ 0"<<writtenDay.getMonth()<<"/ "<<writtenDay.getYear();
    else
        outputStream<<"/ "<<writtenDay.getMonth()<<"/ "<<writtenDay.getYear();

    return outputStream;
}

istream& operator>>(istream& inputStream, DayOfYear& readDay)
{
    cout<<"Enter the day: "<<endl;
    inputStream>>readDay.day;
    readDay.setDay();
    cout<<"Enter the month: "<<endl;
    inputStream>>readDay.month;
    readDay.setMonth();
    cout<<"Enter the year: "<<endl;
    inputStream>>readDay.year;

    return inputStream;
}

Holiday::Holiday()
    :date(1, 1, 2020)
{/*Intentionally left empty*/}

Holiday::Holiday(int dayValue, int monthValue, int yearValue)
    :date(dayValue, monthValue, yearValue)
{/*Intentionally left empty*/}
