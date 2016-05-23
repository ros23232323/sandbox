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


/* Adding titles and footnotes to reports */

title1 'Orion Star Sales Staff';
title2 'Salary Report';

footnote1 'Confidential';

proc print data=orion.sales;
   var Employee_ID Last_Name Salary;
run;

/*Cancel title and foot notes*/
title;
footnote;

/* Use a LABEL statement and the LABEL option to display 
descriptive column headings instead of variable names */

title1 'Orion Star Sales Staff';
title2 'Salary Report';
footnote1 'Confidential';

proc print data=orion.sales label;
   var Employee_ID Last_Name Salary;
   label Employee_ID='Sales ID' 
         Last_Name='Last Name' 
         Salary='Annual Salary';
run;

title;
footnote;

/*formatting Date and numeric values using predefined formats*/
proc print data=orion.sales label;
format Salary dollar8. Hire_Date mmddyy10.;
   var Employee_ID Last_Name Hire_Date Salary;
   label Employee_ID='Sales ID' 
         Last_Name='Last Name' 
         Salary='Annual Salary';
		 	
run;

/*formatting Date and numeric values using predefined formats

Built in Date formats

MMDDYY10.
MMDDYY8. 
MMDDYY6. 
DDMMYY10.
DDMMYY8. 
DDMMYY6.


*/
proc print data=orion.sales label;
format Salary eurox8.2 Hire_Date mmddyy10.;
   var Employee_ID Last_Name Hire_Date Country Salary;
   label Employee_ID='Sales ID' 
         Last_Name='Last Name' 
         Salary='Annual Salary';
		 	
run;

/*user defined formates*/
proc format;
	value $ctryfmt 'AU'='Australia'
					   'US'='United States' 
			  	    other ='Miscoded';
run;

proc format;
   value tiers low-<50000 ='Tier 1'                  
             50000-<100000='Tier 2'
               100000-high='Tier 3';
run;


proc print data=orion.sales;
	var Employee_ID  Salary Salary 
		 Country Birth_Date Hire_Date;
	format Salary dollar10. 
		    Birth_Date Hire_Date monyy7.
			 Country $ctryfmt.;
run;




