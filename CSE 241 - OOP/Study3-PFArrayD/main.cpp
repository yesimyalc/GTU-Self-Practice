#include <iostream>
#include <cstdlib>

using namespace std;

class PFArrayD{
public:
   PFArrayD();
   explicit PFArrayD(int capValue);

   //Big Three Provided
   ~PFArrayD();
   PFArrayD(const PFArrayD& other);
   const PFArrayD& operator=(const PFArrayD& other);

   double& operator[](int index)const;
   friend ostream& operator<<(ostream& outputStream, const PFArrayD& current);
   friend istream& operator>>(istream& inputStream, PFArrayD& current);
   PFArrayD operator--();
   PFArrayD operator--(int ignore);
   const PFArrayD operator+(const PFArrayD& other)const;

   int getUsed()const{return used;}
   int getCapacity()const{return capacity;}

   void increaseCap(int add);
   void addElement(double element);
   void addElement(double element, int index);
   void changeElement(double element, int index);
   void emptyArray();
   void deleteElement(int index);

private:
    int used;
    int capacity;
    double* pointer=nullptr;

    void checkPFArrayD();
};

int main()
{
    PFArrayD a1;

    a1.addElement(1);
    a1.addElement(2);
    a1.addElement(3);
    a1.addElement(10);
    a1.addElement(11);
    a1.addElement(12);
    cout<<a1;
    cout<<endl;

    a1.addElement(4,1);
    a1.changeElement(50, 2);
    cout<<a1;
    cout<<endl;

    a1--;
    --a1;
    a1.deleteElement(3);
    cout<<a1;
    cout<<endl;

    cout<<a1[0]<<endl;
    cout<<a1[1]<<endl;
    cout<<a1[2]<<endl;
    cout<<a1[3]<<endl;
    cout<<endl;

    PFArrayD a2;
    cout<<a2;
    a2=a1;
    cout<<a2;
    cout<<endl;

    cout<<a2.getCapacity()<<endl;
    cout<<a2.getUsed()<<endl;
    a2.increaseCap(3);
    cout<<a2.getCapacity()<<endl;
    cout<<endl;

    cout<<a1<<endl;
    cout<<a2<<endl;
    cout<<a1+a2<<endl;


    return 0;
}

PFArrayD::PFArrayD()
    :used(0), capacity(50)
{
    pointer=new double[50];
}

PFArrayD::PFArrayD(int capValue)
    :used(0), capacity(capValue)
{
    checkPFArrayD();
    pointer=new double[getCapacity()];
}

void PFArrayD::checkPFArrayD()
{
    while((getUsed()>getCapacity()) || getUsed()<0 || getCapacity()<0)
    {
        if((getUsed()>getCapacity()))
        {
            cout<<"Used value exceeds the capacity value, invalid input. Enter the capacity again."<<endl;
            cin>>capacity;
        }
        if(getUsed()<0)
        {
            cout<<"Used value cannot be smaller than 0, invalid input. Enter the used again."<<endl;
            cin>>used;
        }
        if(getCapacity()<0)
        {
            cout<<"Capacity value cannot be smaller than 0, invalid input. Enter the capacity again."<<endl;
            cin>>capacity;
        }
    }
}

PFArrayD::~PFArrayD()
{
    delete[] pointer;
}

PFArrayD::PFArrayD(const PFArrayD& other)
{
    (*this)=other;
}

const PFArrayD& PFArrayD::operator=(const PFArrayD& other)
{
    if(pointer!=nullptr && (getUsed()!=other.getUsed() || getCapacity()!=other.getCapacity()))
    {
        delete[] pointer;
        pointer=new double[getCapacity()];
    }
    else if(pointer==nullptr)
        pointer=new double[getCapacity()];

    used=other.getUsed();
    capacity=other.getCapacity();

    for(int i=0; i<getUsed(); ++i)
        pointer[i]=other.pointer[i];

    return *this;
}

double& PFArrayD::operator[](int index)const
{
    if(index>=getUsed())
    {
        cerr<<"Illegal index in PFArraD."<<endl;
        exit(-1);
    }

    return pointer[index];
}

ostream& operator<<(ostream& outputStream, const PFArrayD& current)
{
    for(int i=0; i<current.getUsed(); ++i)
        outputStream<<current[i]<<endl;

    return outputStream;
}

istream& operator>>(istream& inputStream, PFArrayD& current)
{
    for(int i=0; i<current.getUsed(); ++i)
        inputStream>>current[i];

    return inputStream;
}

PFArrayD PFArrayD::operator--()
{
    if(getUsed()!=0)
        used--;

    return *this;
}

PFArrayD PFArrayD::operator--(int ignore)
{
    PFArrayD temp=*this;
    --(*this);

    return temp;
}

const PFArrayD PFArrayD::operator+(const PFArrayD& other)const
{
    int j=0;

    int newCapacity=getCapacity()+other.getCapacity();
    PFArrayD temp(newCapacity);
    temp.used=getUsed()+other.getUsed();

    for(int i=0; i<getUsed(); ++i)
        temp.pointer[i]=pointer[i];
    for(int i=getUsed(); i<getUsed()+other.getUsed(); ++i)
    {
        temp.pointer[i]=other.pointer[j];
        j++;
    }

    return temp;
}

void PFArrayD::increaseCap(int add)
{
    capacity+=add;

    double *temp=new double[getCapacity()];
    for(int i=0; i<getUsed(); ++i)
        temp[i]=pointer[i];

    delete[] pointer;
    pointer=temp;
}

void PFArrayD::addElement(double element)
{
    if(getUsed()==getCapacity())
        increaseCap(1);

    pointer[used]=element;
    used++;
}

void PFArrayD::addElement(double element, int index)
{
    if(index>=getUsed())
    {
        cerr<<"Illegal index in PFArraD."<<endl;
        exit(-1);
    }

    if(getUsed()==getCapacity())
        increaseCap(1);

    double *temp=new double[getUsed()];
    for(int i=0; i<getUsed(); ++i)
        temp[i]=pointer[i];

    pointer[index]=element;
    used++;

    for(int i=index+1; i<getUsed(); ++i)
        pointer[i]=temp[i-1];
}

void PFArrayD::changeElement(double element, int index)
{
    if(index>=getUsed())
    {
        cerr<<"Illegal index in PFArraD."<<endl;
        exit(-1);
    }

    pointer[index]=element;
}

void PFArrayD::emptyArray()
{
    if(getUsed()==0)
        return;
    else
        used=0;
}

void PFArrayD::deleteElement(int index)
{
    if(index>=getUsed())
    {
        cerr<<"Illegal index in PFArraD."<<endl;
        exit(-1);
    }

    double *temp=new double[getUsed()];
    for(int i=0; i<getUsed(); ++i)
        temp[i]=pointer[i];

    for(int i=index; i<getUsed()-1; ++i)
        pointer[i]=temp[i+1];

    used--;
    delete[] temp;
}
