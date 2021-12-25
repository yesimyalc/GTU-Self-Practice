#include <iostream>
#include <cmath>
#include <cstdlib>

/*A lot better class*/

using namespace std;

class Money{
public:
  Money();
  Money(int dollarAmount);
  Money(int dollarAmount, int centAmount);
  Money(double totalAmount);

  void setMoney(int dollarAmount, int centAmount);
  void setMoney(double totalAmount);

  int getDollars()const{return dollars;}
  int getCents()const{return cents;}
  double getTotalAmount()const;

  friend ostream& operator<<(ostream& outputStream, const Money& amount);
  friend istream& operator>>(istream& inputStream, Money& amount);
  Money operator++();
  Money operator++(int ignore);
  Money operator--();
  Money operator--(int ignore);
  const Money operator-()const;
  friend const Money operator+(const Money& current, const Money& other);
  friend const Money operator-(const Money& current, const Money& other);
  friend bool operator==(const Money& current, const Money& other);

private:
    int dollars;
    int cents;

    void checkMoney();
    void moneyArrange(int dollarAmount, int centAmount);

};

int main()
{
    Money m1(4,5);
    Money m2(-4,-5);

    cout<<"m1="<<m1<<endl;
    cout<<"m2="<<m2<<endl;
    cout<<endl;

    Money m4(6.03);
    cout<<"m4="<<m4<<endl;
    cout<<endl;

    Money m5(-5.566);
    cout<<"m5="<<m5<<endl;
    cout<<endl;

    cout<<"m4++: "<<m4++<<endl;
    cout<<"m4="<<m4<<endl;
    cout<<"++m4: "<<++m4<<endl;
    cout<<endl;

    cout<<"m4--: "<<m4--<<endl;
    cout<<"m4="<<m4<<endl;
    cout<<"--m4: "<<--m4<<endl;
    cout<<endl;

    cout<<"m4+10: "<<m4+10<<endl;
    cout<<"m4-10: "<<m4-10<<endl;
    cout<<"10-m4: "<<10-m4<<endl;
    cout<<endl;

    cout<<"Enter the new amount for m2 example:($4.50, $-3.5 ...)"<<endl;
    cin>>m2;
    cout<<"new m2="<<m2<<endl;
    cout<<endl;

    Money m3=m1+m2;
    cout<<"m1+2=m3: "<<m3<<endl;
    cout<<endl;


    return 0;
}

Money::Money()
{
    setMoney(0, 0);
}

Money::Money(int dollarAmount)
{
    setMoney(dollarAmount, 0);
}

Money::Money(int dollarAmount, int centAmount)
{
    setMoney(dollarAmount, centAmount);
}

Money::Money(double totalAmount)
{
    setMoney(totalAmount);
}

void Money::setMoney(int dollarAmount, int centAmount)
{
    dollars=dollarAmount;
    cents=centAmount;

    checkMoney();
}

void Money::checkMoney()
{
    while(getDollars()>0 && getCents()<0)
    {
        cout<<"You have entered an invalid cents amount, it must be positive. Please enter again."<<endl;
        cin>>cents;
    }
    while(getDollars()<0 && getCents()>0)
    {
        cout<<"You have entered an invalid cents amount, it must be negative. Please enter again."<<endl;
        cin>>cents;
    }
}

void Money::setMoney(double totalAmount)
{
    dollars=static_cast<int>(totalAmount);

    double doubleCents=totalAmount*100;
    int intCents=(static_cast<int>(floor((fabs(doubleCents))+0.5)))%100;
    if(totalAmount<0)
        intCents=-intCents;
    else
        cents=intCents;
}

double Money::getTotalAmount()const
{
    double totalAmount=getDollars()*100+getCents();

    return totalAmount;
}

ostream& operator<<(ostream& outputStream, const Money& amount)
{
    outputStream<<"$"<<amount.getDollars()<<".";

    if(amount.getCents()>0 && amount.getCents()<10)
        outputStream<<"0"<<amount.getCents();
    else if(amount.getCents()<0 && amount.getCents()>-10)
        outputStream<<"0"<<-amount.getCents();
    else if(amount.getCents()<-10)
        outputStream<<-amount.getCents();
    else
        outputStream<<amount.getCents();

    return outputStream;
}

istream& operator>>(istream& inputStream, Money& amount)
{
    char temp;
    inputStream>>temp;
    if(temp!='$')
    {
        cerr<<"Found an invalid character. There must have been $ before the money amount."<<endl;
        exit(-1);
    }
    inputStream>>amount.dollars>>temp;
    if(temp!='.')
    {
        cerr<<"Found an invalid character. There must be . between the dollars part and the cents part."<<endl;
        exit(-1);
    }
    inputStream>>amount.cents;

    if(amount.getDollars()<0)
        amount.cents=-amount.getCents();

    return inputStream;
}

Money Money::operator++()
{
    ++dollars;
    moneyArrange(getDollars(), getCents());

    Money temp(dollars, cents);
    return temp;
}

Money Money::operator++(int ignore)
{
    Money temp(getDollars(), getCents());
    ++(*this);
    return temp;
}

Money Money::operator--()
{
    --dollars;
    moneyArrange(getDollars(), getCents());

    Money temp(dollars, cents);
    return temp;
}

Money Money::operator--(int ignore)
{
    Money temp(getDollars(), getCents());
    --(*this);

    return temp;
}

const Money operator+(const Money& current, const Money& other)
{
    int newDollars;
    int newCents;

    newDollars=current.getDollars()+other.getDollars();
    newCents=current.getCents()+other.getCents();
    if(newCents>100)
    {
        newCents=newCents-100;
        newDollars++;
    }
    else if(newCents<-100)
    {
        newCents=newCents+100;
        newDollars--;
    }

    Money temp;
    temp.moneyArrange(newDollars, newCents);
    return temp;
}

const Money Money::operator-()const
{
    int newDollars;
    int newCents;

    newDollars=-getDollars();
    newCents=-getCents();

    Money temp(newDollars, newCents);
    return temp;
}

const Money operator-(const Money& current, const Money& other)
{
    return (current)+(-other);
}

bool operator==(const Money& current, const Money& other)
{
    if(current.getDollars()==other.getDollars() && current.getCents()==other.getCents())
        return true;
    else
        return false;
}

void Money::moneyArrange(int dollarAmount, int centAmount)
{
    if((dollarAmount<0 && centAmount>0) || (dollarAmount>0 && centAmount<0))
    {
        int tempDollars=dollarAmount;
        int tempCents=centAmount;

        tempDollars=tempDollars*100+tempCents;
        tempCents=(static_cast<int>(fabs(tempDollars)))%100;

        if(centAmount<0)
        {
            cents=-tempCents;
            dollars=dollarAmount-1;
        }
        else
        {
            cents=tempCents;
            dollars=dollarAmount+1;
        }
    }
    else
    {
        dollars=dollarAmount;
        cents=centAmount;
    }
}
