%let path="\\vmware-host\Shared Folders\host_documents\sandbox\msc-data-analytics\sas_STAT40840\data";

libname orion clear;
libname orion '\\vmware-host\Shared Folders\host_documents\sandbox\msc-data-analytics\sas_STAT40840\data';

/**/
proc print data=orion.sales;
run;

proc sql;
	select distinct Country from orion.sales;
run;


********** Concat Like-Structured Data Sets **********;
data empsall1;
   set empsdk empsfr;
run;

********** Concat dataset renaming field Region to country in dataset empsjp **********;
data empsall2;
   set empscn empsjp(rename=(Region=Country));
run;

proc print data=work.empsall1;
run;

proc sql;
	select * from orion.sales where Country='AU' and  Salary<30000;
run;


****** CReate a dataset******;
data phoneh;
   input EmpID Phone $15.;
   datalines;
121150 +61(2)5555-1793
121151 +61(2)5555-1849
121152 +61(2)5555-1665
;

proc print data=work.phoneh;
run;
