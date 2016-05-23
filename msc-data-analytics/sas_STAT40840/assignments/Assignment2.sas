/*Load file*/
proc import datafile="\\vmware-host\Shared Folders\host_documents\sandbox\msc-data-analytics\sas_STAT40840\assignments\Assignment2.csv"
     out=work.Assignment2
     dbms=csv
     replace;
     getnames=yes;
run;

/*Sort dataset by SEX*/
proc sort data=work.Assignment2
	out=work.Assignment2_sorted;
	by SEX;
run;

/*function to print SEX as string, male|female*/
proc format;
	value sex 1 = 'Male' 2 = 'Female';
run;


/*Print dataset*/
proc print data=work.Assignment2_sorted;
	where time = 0;
	format SEX sex.;
run;



/*Numerical & Descriptive statistics*/
ods trace on; 
proc univariate data=work.Assignment2_sorted;
	var ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
run;
ods trace off; 

proc means data=work.Assignment2_sorted n nmiss mean stderr t prt clm median std var min max range kurtosis skewness;
	var ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
	out=work.Assignment2_sorted_summary;
run;

ods select ExtremeObs;
proc univariate data=work.Assignment2_sorted ;
	var ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
run;

proc univariate data=work.Assignment2_sorted noprint;
	histogram ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
run;

proc univariate data=work.Assignment2_sorted noprint;
	histogram Chol;
run;


proc sgplot data=work.Assignment2_sorted;
	histogram ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
	density ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
run;

proc univariate data=work.Assignment2_sorted noprint;
	histogram ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
	by SEX;
	format SEX sex.;
run;

ods select BasicMeasures ExtremeObs;
proc univariate data=work.Assignment2_sorted;
	var ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
	by SEX;
	format SEX sex.;
run;

ods select Frequencies;
proc univariate data=work.Assignment2 freq;
	var SEX;
run;

/*Correlation*/

proc corr data=work.Assignment2_sorted nomiss plots=matrix(histogram);
	var ApoA1 ApoB ApoC2 ApoC3 ApoE Glucose NEFA Insulin Chol TAG;
run;
proc corr data=work.Assignment2_sorted nomiss plots=matrix(histogram);
	var ApoB Chol ;
run;
ods graphics on;
proc corr data=work.Assignment2_sorted nomiss plots=matrix(histogram);
	var ApoC2 ApoC3;
run;
ods graphics off;

/*Partial Correlation*/

proc corr data=work.Assignment2_sorted;
	var Glucose Insulin ;
	Partial BMI;
run;

/*Linear Model*/

ods graphics on;
proc reg data=work.Assignment2_sorted;
	model TAG = AGE SEX BMI;
	where time = 0;
run;

ods graphics on;
proc reg data=work.Assignment2_sorted;
	model TAG = AGE SEX BMI;
	where time = 0 and SEX = 1 ;
run;

ods graphics on;
proc glm data=work.Assignment2_sorted plots=(diagnostics);
	class SEX ;
	model TAG = AGE BMI SEX /;
	lsmeans SEX / pdiff;
	where time = 0;
	format SEX sex.;
run;

ods graphics on;
proc glm data=work.Assignment2_sorted plots=(diagnostics);
	class SEX ;
	model TAG = AGE BMI SEX / link=log dist=normal;
	lsmeans SEX / pdiff;
	where time = 0;
	format SEX sex.;
run;

ods graphics on;
proc genmod data=work.Assignment2_sorted plots=all;
	class SEX ;
	model TAG = AGE BMI SEX / dist=normal;
	lsmeans SEX / pdiff;
	where time = 0;
	format SEX sex.;
run;


ods graphics on;
proc genmod data=work.Assignment2_sorted plots=all;
	class SEX ;
	model TAG = AGE BMI SEX / link=log dist=normal;
	lsmeans SEX / pdiff;
	where time = 0;
	format SEX sex.;
run;

/*Correlation*/

proc mixed data=work.Assignment2_sorted plots=all;
	class SEX time ID;
	model Insulin = AGE SEX BMI / solution;
	repeated /subject=ID type=ar(1) r;
	format SEX sex.;
run;


proc mixed data=work.Assignment2_sorted plots=all;
	class SEX time ID;
	model Insulin = AGE SEX BMI / solution;
	repeated /subject=ID type=arh(1) r;
	format SEX sex.;
run;
