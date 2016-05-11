/*Exam Snippets*/

/*Create Library*/
libname orion clear;
libname orion '\\vmware-host\Shared Folders\host_documents\sandbox\msc-data-analytics\sas_STAT40840\data';

/*Print dataset description*/
proc contents data=orion.sales;
run;

/*Print dataset records*/
proc print data=orion.sales;
run;


/*Print subset of dataset records using where*/
proc print data=orion.sales;
/*	where Gender = 'M' AND Salary > 100000;*/
/*	where First_Name in ('Renee','Roger','Sandy') or Country ne 'US';*/
/*	where First_name like 'T_m'; /*_ = exactly one char is wild*/*/
	where First_name like 'K%a'; /* % = n char is wild*/
	id Employee_ID ;
	where First_name like 'K%a';
	var Employee_ID Job_Title;
run;

/*Sort dataset by Gender ascending and salary descending records using where*/
proc sort data=orion.sales out=sales_sorted;
	by Gender DESCENDING Salary ;
run;

proc print data=work.sales_sorted noobs;
	by Gender;
	sum Salary;
	var Gender Salary;
run;
