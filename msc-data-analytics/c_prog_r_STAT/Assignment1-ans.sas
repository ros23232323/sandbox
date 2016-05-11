
libname assign clear;
libname assign '\\vmware-host\Shared Folders\host_desktop\DataAnalytics\year3\semester2\STAT40840 Data Prog with SAS\labs\assign1';

/*****************************************************************************************
******************************************************************************************
******************************************************************************************
	Question 1
******************************************************************************************
******************************************************************************************
******************************************************************************************/

proc sort data=assign.Assignment1 out=assign.Assignment1SortedByGender ;
	by Gender;
run;

proc format;
	value gender 0 = 'male' 1 = 'female';
run;

title 'Dietary Intervention: participants between the ages of 40 and 50';
proc print data=assign.Assignment1SortedByGender noobs label;
	id Participant_ID;
	var Bodyweight0 Bodyweight6;
	label Bodyweight0='Bodyweight0 : Weight of individual, in Kg, at the start of the study' Bodyweight6='Bodyweight6 : Weight of individual, in Kg, after six months';
	where Age between 40 and 50;
	by Gender ;
	format Gender gender.;
run;


/*****************************************************************************************
******************************************************************************************
******************************************************************************************
	Question 2
******************************************************************************************
******************************************************************************************
******************************************************************************************/

data assign.Assignment1Q2;
	set assign.Assignment1;
	where 
		Age > 35 and 
		Bodyweight6 is not null and 
		Bodyweight0 is not null and 
		Energy_Intake0 is not null and
		Energy_Intake6 is not null;
	Bodyweight_Delta = Bodyweight6 - Bodyweight0;
	Energy_Intake_Delta = (Energy_Intake6 / Energy_Intake0) - 1;
	if Energy_Intake_Delta < -0.1;
	label Bodyweight_Delta='change in bodyweight' 
		Energy_Intake_Delta='proportional change in energy intake';
	format Energy_Intake_Delta percent10.2;
run;

proc contents data=assign.Assignment1Q2;
run;

title 'Dietary Intervention: participants over the age of 35';
proc print data=assign.Assignment1Q2 label;
	var Bodyweight_Delta Energy_Intake_Delta;
run;
