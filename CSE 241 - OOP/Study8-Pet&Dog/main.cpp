#include <iostream>
#include <string>

using namespace std;

class Pet
{
public:
    //Constructors
    Pet();
    explicit Pet(string nameInfo);
    Pet(string nameInfo, string ownerInfo);

    //Getters
    string getName()const{return name;}
    string getOwnerName()const{return ownerName;}

    //Functions
    virtual void printInfo()const;

private:
    string name;
    string ownerName;

};

class Dog : public Pet
{
public:
    //Constructors
    Dog();
    Dog(string nameInfo, string breedInfo);
    Dog(string nameInfo, string ownerInfo, string breedInfo);

    //Getters
    string getType()const{return type;}
    string getBreed()const{return breed;}

    //Functions
    virtual void printInfo()const override;

private:
    const string type="Dog";
    string breed;

};



int main()
{
    Pet vpet("Maggi", "Nova");
    Dog vdog("Lili", "Starley", "Husky");

    vpet.printInfo();
    cout<<endl;
    vdog.printInfo();
    cout<<endl;

    vpet=vdog;
    vpet.printInfo();
    cout<<endl;

    vpet=Pet("Maggi", "Nova");

    Pet* p1;
    Pet* p2;
    p2=&vpet;
    p1=&vdog;
    p1->printInfo();
    cout<<endl;

    (*p1)=Dog("Vel", "Nel", "Husky");

    Dog* pdog;

    pdog=dynamic_cast<Dog*>(p1);

    pdog->printInfo();
    cout<<endl;

    /*
    Will create runtime error
    pdog=dynamic_cast<Dog*>(p2);

    pdog->printInfo();
    cout<<endl;
    */

    return 0;
}



Pet::Pet()
    :Pet(" ", " ")
{/*Intentionally left empty*/}

Pet::Pet(string nameInfo)
    :Pet(nameInfo, " ")
{/*Intentionally left empty*/}

Pet::Pet(string nameInfo, string ownerInfo)
    :name(nameInfo), ownerName(ownerInfo)
{/*Intentionally left empty*/}

void Pet::printInfo()const
{
    cout<<"Name: "<<getName()<<endl<<
    "Owner Name: "<<getOwnerName()<<endl;
}



Dog::Dog()
    :Dog(" ", " ", " ")
{/*Intentionally left empty*/}

Dog::Dog(string nameInfo, string breedInfo)
    :Dog(nameInfo, " ", breedInfo)
{/*Intentionally left empty*/}

Dog::Dog(string nameInfo, string ownerInfo, string breedInfo)
    :Pet(nameInfo, ownerInfo), breed(breedInfo)
{/*Intentionally left empty*/}

void Dog::printInfo()const
{
    Pet::printInfo();
    cout<<"Type: "<<getType()<<endl<<
    "Breed: "<<getBreed()<<endl;
}
