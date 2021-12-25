#include <iostream>
#include <string>

using namespace std;

string returnBinary(int number)
{
    string result=" ";
    int startNo=1;
    int temp;

    while(startNo<number)
    {
        temp=startNo;
        startNo*=2;
    }

    if(startNo>number)
        startNo=temp;

    temp=0;

    while(number!=0)
    {
        temp=number%startNo;

        if((number/startNo)!=0)
        {
            result+=to_string(number/startNo);
            number=temp;
        }
        else
            result+="0";

        temp=0;
        startNo=startNo/2;
    }

    return result;
}

int main()
{
    cout<<returnBinary(45)<<endl;
    return 0;
}
