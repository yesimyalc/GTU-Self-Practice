#include <iostream>
#include <new>
#include <cstdlib>

using namespace std;

template<class T> class PFArray;
template<class T> ostream& operator<<(ostream& outputStream, const PFArray<T>& current);

template<class T>
class PFArray
{
public:
    //Constructors
    PFArray();
    explicit PFArray(int capValue);

    //Big Three
    PFArray(const PFArray<T>& other);
    virtual ~PFArray();
    PFArray& operator=(const PFArray<T>& other);

    //Overloaded Operators
    T& operator[](int indexNo)const;
    PFArray<T> operator--();
    PFArray<T> operator--(int ignore);
    const PFArray<T> operator+(const PFArray<T>& other);
    friend ostream& operator<< <>(ostream& outputStream, const PFArray<T>& current);

    //Getters
    int getUsed()const{return used;}
    int getCap()const{return capacity;}

    //Setters
    void setCapacity(int newCapValue);

    //Other Functions
    void addElement(T element);
    void addElement(int indexNo, T element);
    void deleteElement(int indexNo);
    void changeElement(int indexNo, T element);
    void emptyArray();

protected:
    int used;
    int capacity;
    T *pointer=nullptr;

};


template<class T>
class PFArrayB : public PFArray<T>
{
public:
    //Constructors
    PFArrayB();
    explicit PFArrayB(int capValue);

    //Other Functions
    void backup();
    void restore();

private:
    PFArray<T> save;

};



int main()
{
    PFArrayB<int> a1;
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

    PFArrayB<int> a2=a1;
    cout<<a2;

    return 0;
}



template<class T>
PFArray<T>::PFArray()
    :used(0), capacity(50)
{
    try
    {
        pointer=new T[getCap()];
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

template<class T>
PFArray<T>::PFArray(int capValue)
    :used(0)
{
    setCapacity(capValue);
    try
    {
        pointer=new T[getCap()];
    }
    catch(bad_alloc& allocationError)
    {
        cerr<<allocationError.what()<<endl;
        exit(-1);
    }
}

template<class T>
PFArray<T>::PFArray(const PFArray<T>& other)
{
    *this=other;
}

template<class T>
PFArray<T>::~PFArray()
{
    delete[] pointer;
}

template<class T>
PFArray<T>& PFArray<T>::operator=(const PFArray<T>& other)
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
            pointer=new T[getCap()];

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

template<class T>
void PFArray<T>::setCapacity(int newCapValue)
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

template<class T>
T& PFArray<T>::operator[](int indexNo)const
{
    if(indexNo>=getUsed())
    {
        cerr<<"There is no element with this index."<<endl;
        exit(-1);
    }

    return pointer[indexNo];
}

template<class T>
PFArray<T> PFArray<T>::operator--()
{
    if(getUsed()==0)
    {
        cerr<<"There is no element to delete in the array."<<endl;
        exit(-1);
    }

    used--;

    return *this;
}

template<class T>
PFArray<T> PFArray<T>::operator--(int ignore)
{
    PFArray temp=*this;

    --(*this);

    return temp;
}

template<class T>
const PFArray<T> PFArray<T>::operator+(const PFArray<T>& other)
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

template<class T>
ostream& operator<<(ostream& outputStream, const PFArray<T>& current)
{
    for(int i=0; i<current.getUsed(); ++i)
        outputStream<<current[i]<<endl;

    return outputStream;
}

template<class T>
void PFArray<T>::addElement(T element)
{
    if(getUsed()==getCap())
        capacity++;

    used++;
    pointer[getUsed()-1]=element;
}

template<class T>
void PFArray<T>::addElement(int indexNo, T element)
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
        T* temp=new T[getUsed()];

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

template<class T>
void PFArray<T>::deleteElement(int indexNo)
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
        T* temp=new T[getUsed()];

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

template<class T>
void PFArray<T>::changeElement(int indexNo, T element)
{
    if(indexNo>=getUsed() || indexNo<0)
    {
        cerr<<"There is no element with that index number."<<endl;
        exit(-1);
    }

    pointer[indexNo]=element;
}

template<class T>
void PFArray<T>::emptyArray()
{
    if(getUsed()==0)
        return;

    used=0;
}



template<class T>
PFArrayB<T>::PFArrayB()
    :PFArray<T>(), save(50)
{/*Intentionally left empty*/}

template<class T>
PFArrayB<T>::PFArrayB(int capValue)
    :PFArray<T>(capValue), save(capValue)
{/*Intentionally left empty*/}

template<class T>
void PFArrayB<T>::backup()
{
    save=*this;
}

template<class T>
void PFArrayB<T>::restore()
{
    if(save.getUsed()==0)
    {
        cout<<"There is no backups to restore from."<<endl;
        return;
    }

    PFArray<T>::operator=(save);
}
