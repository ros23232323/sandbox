libname orion clear;
libname orion '\\vmware-host\Shared Folders\host_documents\sandbox\msc-data-analytics\sas_STAT40840\data';


/**/
proc print data=orion.sales;
run;

*The SAS functions are used within the assignment statements in the DATA step;
data work.comp;
   set orion.sales;
   Bonus=500;
   Compensation=sum(Salary,Bonus);
   BonusMonth=month(Hire_Date);
run;

proc print data=orion.sales noobs;
run;

proc sql;
	select distinct Job_Title from orion.sales;
run;



proc print data=work.comp noobs;
run;

proc print data=work.comp noobs;
   var Employee_ID First_Name Last_Name
       Bonus Compensation BonusMonth;
run;


data work.comp;
   set orion.sales;
   drop Gender Salary Job_Title Country 
        Birth_Date Hire_Date;
   Bonus=500;
   Compensation=sum(Salary,Bonus);
   BonusMonth=month(Hire_Date);
run;

proc print data=work.comp noobs;
run;


data work.comp;
   set orion.sales;
   if Job_Title = 'Chief Sales Officer' then
   	Bonus=100;
   if Job_Title = 'Senior Sales Manager' then
   	Bonus=1000;
   if Job_Title = 'Sales Manager' then
   	Bonus=10000;
   if Job_Title = 'Sales Rep. I' then
   	Bonus=100000;
   if Job_Title = 'Sales Rep. II' then
   	Bonus=1000000;
   if Job_Title = 'Sales Rep. III' then
   	Bonus=10000000;
   if Job_Title = 'Sales Rep. IV' then
   	Bonus=100000000;
   Compensation=sum(Salary,Bonus);
   BonusMonth=month(Hire_Date);
run;


data work.bonus;
   set orion.sales;
	if Country = 'US' then Bonus=1000;
	else Bonus=10;
   Compensation=sum(Salary,Bonus);
   BonusMonth=month(Hire_Date);
run;

proc print data=work.bonus noobs;
run;


data work.bonus;
   set orion.sales;
   if Country='US' then do;
      Bonus=500;
	   Freq='Once a Year';
   end;
   else if Country='AU' then do;
		Bonus=300;
	   Freq='Twice a Year';
   end;
run;

proc print data=work.bonus;
   var First_Name Last_Name Country Bonus Freq;
run;


data work.bonus;
   set orion.sales;
   length Freq $ 12;
   if Country='US' then do;
      Bonus=500;
	   Freq='Once a Year';
   end;
   else if Country='AU' then do;
		Bonus=300;
	   Freq='Twice a Year';
   end;
run;

proc print data=work.bonus;
   var First_Name Last_Name Country Bonus Freq;
run;


********** Create Data **********;
data empsdk;
   input First $ Gender $ Country $;
   datalines;
Lars    M   Denmark
Kari    F   Denmark
Jonas   M   Denmark
;

data empsfr;
   input First $ Gender $ Country $;
   datalines;
Pierre  M   France
Sophie  F   France
;

********** Like-Structured Data Sets **********;
data empsall1;
   set empsdk empsfr;
run;

proc print data=empsall1;
run;



********** Create Data **********;
data empscn;
   input First $ Gender $ Country $;
   datalines;
Chang   M   China
Li      M   China
Ming    F   China
;

data empsjp;
   input First $ Gender $ Region $;
   datalines;
Cho     F   Japan
Tomi    M   Japan
;

********** Unlike-Structured Data Sets **********;
data empsall2;
   set empscn empsjp;
run;

proc print data=empsall2;
run;


********** Create Data **********;
data empscn;
   input First $ Gender $ Country $;
   datalines;
Chang   M   China
Li      M   China
Ming    F   China
;

data empsjp;
   input First $ Gender $ Region $;
   datalines;
Cho     F   Japan
Tomi    M   Japan
;

data empsall2;
   set empscn empsjp(rename=(Region=Country));
run;

proc print data=empsall2;
run;



********** Create Data **********;
data empsau;
   input First $ Gender $ EmpID;
   datalines;
Togar   M   121150
Kylie   F   121151
Birin   M   121152
;

data phoneh;
   input EmpID Phone $15.;
   datalines;
121150 +61(2)5555-1793
121151 +61(2)5555-1849
121152 +61(2)5555-1665
;

********** One-to-One Merge **********;
data empsauh;
   merge empsau phoneh;
   by EmpID;
run;

proc print data=empsauh;
run;


********** Create Data **********;
data empsau;
   input First $ Gender $ EmpID;
   datalines;
Togar   M   121150
Kylie   F   121151
Birin   M   121152
;

data phoneh;
   input EmpID Phone $15.;
   datalines;
121150 +61(2)5555-1793
121151 +61(2)5555-1849
121152 +61(2)5555-1665
;

********** One-to-One Merge **********;
data empsauh;
   merge empsau phoneh;
   by EmpID;
run;

proc print data=empsauh;
run;

proc sql;
	select * from empsau a , phoneh b where a.EmpID = b.EmpID; 
run;


/*********************************************************
*  1. Complete the program to match-merge the sorted     *
*     SAS data sets referenced in the PROC SORT steps.   *                                  *
*  2. Submit the program. Correct and resubmit,          *
*     if necessary.                                      * 
*  4. What are the modified, completed statement?        *
*********************************************************/

proc sort data=orion.employee_payroll
          out=work.payroll; 
   by Employee_ID;
run;

proc sort data=orion.employee_addresses
          out=work.addresses;
   by Employee_ID;
run;

data work.payadd;
   merge    work.payroll   work.addresses   ;
   by        Employee_ID    ;
run;

proc print data=work.payadd;
   var Employee_ID Employee_Name Birth_Date Salary;
   format Birth_Date weekdate.;
run;


********** Create Data **********;
data empsau;
   input First $ Gender $ EmpID;
   datalines;
Togar   M   121150
Kylie   F   121151
Birin   M   121152
;

data phones;
   input EmpID Type $ Phone $15.;
   datalines;
121150 Home +61(2)5555-1793
121150 Work +61(2)5555-1794
121151 Home +61(2)5555-1849
121152 Work +61(2)5555-1850
121152 Home +61(2)5555-1665
121152 Cell +61(2)5555-1666
;

********** One-to-Many Merge **********;
data empphones;
   merge empsau phones;
   by EmpID;
run;

proc print data=empphones;
run;
