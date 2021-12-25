#include <iostream>
#include <new>
#include <cstdlib>

using namespace std;

class PFArray
{
public:
    //Constructors
    PFArray();
    explicit PFArray(int capValue);

    //Big Three
    PFArray(const PFArray& other);
    virtual ~PFArray();
    PFArray& operator=(const PFArray& other);

    //Overloaded Operators
    double& operator[](int indexNo)const;
    PFArray operator--();
    PFArray operator--(int ignore);
    const PFArray operator+(const PFArray& other);
    friend ostream& operator<<(ostream& outputStream, const PFArray& current);

    //Getters
    int getUsed()const{return used;}
    int getCap()const{return capacity;}

    //Setters
    void setCapacity(int newCapValue);

    //Other Functions
    void addElement(double element);
    void addElement(int indexNo, double element);
    void deleteElement(int indexNo);
    void changeElement(int indexNo, double element);
    void emptyArray();

protected:
    int used;
    int capacity;
    double *pointer=nullptr;

};


class PFArrayB : public PFArray
{
public:
    //Constructors
    PFArrayB();
    explicit PFArrayB(int capValue);

    //Other Functions
    void backup();
    void restore();

private:
    PFArray save;

};



int main()
{
    PFArrayB a1;
    a1.addElement(5);
    a1.addElement(4);
    a1.addElement(2);
    a1.addElement(16);
    a1.addElement(7);
    cout<<a1;
    cout<<endl;

    a1.addElement(2, 150);
    a1--;
    a1.deleteElement(1);
    a1.changeElement(0, 12);
    a1.addElement(48);
    cout<<a1;
    cout<<endl;

    a1.addElement(5);
    a1.addElement(4);
    a1.addElement(2);
    a1.addElement(16);
    a1.addElement(7);
    cout<<a1;
    cout<<endl;

    a1.backup();

    a1.setCapacity(5);
    cout<<a1;
    cout<<endl;

    a1.restore();
    cout<<a1;
    cout<<endl;

    PFArrayB a2=a1;
    cout<<a2;

    return 0;
}



PFArray::PFArray()
    :used(0), capacity(50)
{
    try
    {
        pointer=new double[getCap()];
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

PFArray::PFArray(int capValue)
    :used(0)
{
    setCapacity(capValue);
    try
    {
        pointer=new double[getCap()];
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

PFArray::PFArray(const PFArray& other)
{
    *this=other;
}

PFArray::~PFArray()
{
    delete[] pointer;
}

PFArray& PFArray::operator=(const PFArray& other)
{
    if(this==&other)
        return *this;

    if(pointer!=nullptr && getCap()!=other.getCap())
    {
        delete[] pointer;
        pointer=nullptr;
    }

    used=other.getUsed();
    capacity=other.getCap();

    try
    {
        if(pointer==nullptr)
            pointer=new double[getCap()];

        for(int i=0; i<getUsed(); ++i)
        pointer[i]=other[i];

        return *this;
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

void PFArray::setCapacity(int newCapValue)
{
    capacity=newCapValue;
    while(getCap()<=0)
    {
        cout<<"Invalid capacity, please enter the capacity again."<<endl;
        cin>>capacity;
    }
    if(getCap()<getUsed())
        used=capacity;
}

double& PFArray::operator[](int indexNo)const
{
    if(indexNo>=getUsed())
    {
        cerr<<"There is no element with this index."<<endl;
        exit(-1);
    }

    return pointer[indexNo];
}

PFArray PFArray::operator--()
{
    if(getUsed()==0)
    {
        cerr<<"There is no element to delete in the array."<<endl;
        exit(-1);
    }

    used--;

    return *this;
}

PFArray PFArray::operator--(int ignore)
{
    PFArray temp=*this;

    --(*this);

    return temp;
}

const PFArray PFArray::operator+(const PFArray& other)
{
    int newCapacity=getCap()+other.getCap();
    PFArray temp(newCapacity);
    temp.used=getUsed()+other.getUsed();

    for(int i=0; i<getUsed(); ++i)
        temp.pointer[i]=pointer[i];
    for(int i=getUsed(); i<getUsed()+other.getUsed(); ++i)
        temp.pointer[i]=other.pointer[i-getUsed()];

    return temp;
}

ostream& operator<<(ostream& outputStream, const PFArray& current)
{
    for(int i=0; i<current.getUsed(); ++i)
        outputStream<<current[i]<<endl;

    return outputStream;
}

void PFArray::addElement(double element)
{
    if(getUsed()==getCap())
        capacity++;

    used++;
    pointer[getUsed()-1]=element;
}

void PFArray::addElement(int indexNo, double element)
{
    if(indexNo>getUsed() || indexNo<0)
    {
        cerr<<"There is no element with that index number."<<endl;
        exit(-1);
    }
    else if(indexNo==getUsed())
    {
        addElement(element);
        return;
    }

    try
    {
        double* temp=new double[getUsed()];

        for(int i=0; i<getUsed(); ++i)
            temp[i]=pointer[i];

        if(getUsed()==getCap())
            capacity++;

        used++;
        pointer[indexNo]=element;

        for(int i=indexNo+1; i<getUsed(); ++i)
            pointer[i]=temp[i-1];

        delete[] temp;
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

void PFArray::deleteElement(int indexNo)
{
    if(indexNo>=getUsed() || indexNo<0)
    {
        cerr<<"There is no element with that index number."<<endl;
        exit(-1);
    }
    else if(indexNo==getUsed())
    {
        (*this)--;
        return;
    }

    try
    {
        double* temp=new double[getUsed()];

        for(int i=0; i<getUsed(); ++i)
            temp[i]=pointer[i];

        used--;

        for(int i=indexNo; i<getUsed(); ++i)
            pointer[i]=temp[i+1];
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

void PFArray::changeElement(int indexNo, double element)
{
    if(indexNo>=getUsed() || indexNo<0)
    {
        cerr<<"There is no element with that index number."<<endl;
        exit(-1);
    }

    pointer[indexNo]=element;
}

void PFArray::emptyArray()
{
    if(getUsed()==0)
        return;

    used=0;
}



PFArrayB::PFArrayB()
    :PFArray(), save(50)
{/*Intentionally left empty*/}

PFArrayB::PFArrayB(int capValue)
    :PFArray(capValue), save(capValue)
{/*Intentionally left empty*/}

void PFArrayB::backup()
{
    save=*this;
}

void PFArrayB::restore()
{
    if(save.getUsed()==0)
    {
        cout<<"There is no backups to restore from."<<endl;
        return;
    }

    PFArray::operator=(save);
}
