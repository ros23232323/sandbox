libname orion clear;
libname orion '\\vmware-host\Shared Folders\host_documents\sandbox\msc-data-analytics\sas_STAT40840\data';


*Numerical descriptive statistics;
*Basic descriptives;
proc univariate data=work.bodyweight;
	var Bodyweight0 Energy_Intake0 Bodyweight6 Energy_Intake6 ;
run;


*List the output tables that ods (output delivery system) produces;
ods trace on;
proc univariate data=work.bodyweight;
	var Bodyweight0 Energy_Intake0 Bodyweight6 Energy_Intake6 ;
run;
ods trace off;

*Output Added:
-------------
Name:       Moments
Name:       BasicMeasures
Name:       TestsForLocation
Name:       Quantiles
Name:       ExtremeObs;
quit;
ods select Moments ExtremeObs;
proc univariate data=work.bodyweight ;
	var Bodyweight0 Energy_Intake0 Bodyweight6 Energy_Intake6;
run;


Data work.bodyweight1;
	set work.bodyweight;
	if energy_intake0 gt 16000 then energy_intake0=".";
	else energy_intake0=energy_intake0;
run;


ods select Moments ExtremeObs;
proc univariate data=work.bodyweight noprint;
	var Bodyweight0 Energy_Intake0 Bodyweight6 Energy_Intake6;
run;



*Creating a frequency table;
ods select Frequencies;
proc univariate data=work.bodyweight1 freq;
   var gender;
run;

*Grouping descriptive statistics;
proc sort data=work.bodyweight1;
	by gender;
run;

proc univariate data=work.bodyweight1;
	by gender;
	var bodyweight0;
run;



*Saving Summary Statistics in an OUT= Output Data Set;
proc univariate data=work.bodyweight1;
	var bodyweight0;
	output out=work.means mean=m_weight0 ;
run;
proc print data=work.means;
run;


*Saving Output Table;
ods output Moments=work.Moments;
proc univariate data=work.bodyweight1;
	var bodyweight0;
run;
proc print data=work.moments;
run;




*Saving Summary Statistics in an OUT= Output Data Set;
proc univariate data=work.bodyweight1;
	var bodyweight0;
	output out=work.means mean=m_weight0 ;
run;
proc print data=work.means;
run;


*Saving Output Table;
ods output Moments=work.Moments;
proc univariate data=work.bodyweight1;
	var bodyweight0;
run;
proc print data=work.moments;
run;



*Computing Confidence Limits for the Mean, Standard Deviation, and Variance;
proc univariate data=work.bodyweight1 cibasic;
	var bodyweight0;
run;




*Graphical descriptive statistics;
*Creating a histogram;
proc univariate data=work.bodyweight1 noprint /*suppresses automatic printing of tables*/;
	histogram bodyweight0 ;
run;

*Creating a comparitive histogram;
proc univariate data=work.bodyweight1 noprint;
	class gender;
	histogram bodyweight0 ;
run;


*Adding a normal curve to a histogram;
*An INSET statement places a box or table of summary statistics, called an inset, directly in a graph;
proc univariate data=work.bodyweight1 noprint;
   histogram bodyweight0/ normal;
   inset n normal(ksdpval) / pos = ne ;
run;




*Graphical descriptive statistics;
*Creating a histogram;
proc univariate data=work.bodyweight1 noprint /*suppresses automatic printing of tables*/;
	histogram bodyweight0 ;
run;

*Creating a comparitive histogram;
proc univariate data=work.bodyweight1 noprint;
	class gender;
	histogram bodyweight0 ;
run;


*Adding a normal curve to a histogram;
*An INSET statement places a box or table of summary statistics, called an inset, directly in a graph;
proc univariate data=work.bodyweight1 noprint;
   histogram bodyweight0/ normal;
   inset n normal(ksdpval) / pos = ne ;
run;



*Creating a normal quantile plot;
proc univariate data=work.bodyweight1 noprint normal;
   qqplot bodyweight0 / normal;
run;

*Add a distribution reference line to quantile plot;
proc univariate data=work.bodyweight1 normal;
   qqplot bodyweight0 / normal (mu=est sigma=est);
run;



*Calculating correlation coefficient;
proc corr data=work.bodyweight1;
	var  Bodyweight0 Age;
run;



*Producing a scatterplot matrix;
ods graphics on;
proc corr data=work.bodyweight1 nomiss plots=matrix(histogram);
   var Age  Bodyweight0 Energy_Intake0 Bodyweight6 Energy_Intake6;
run;
ods graphics off;



*Computing partial correlations;
proc corr data=work.bodyweight1;
	var  Bodyweight0 Energy_Intake0;
	Partial age;
run;



*Regression analysis;
ods graphics on;
 proc reg data=work.bodyweight1;
    model bodyweight0 = age energy_intake0;
 run;

 *Saving residuals and predicted values;
ods graphics on;
 proc reg data=work.bodyweight1;
    model bodyweight0 = age energy_intake0;
	output out=pred r=r p=p;
 run;
quit;

