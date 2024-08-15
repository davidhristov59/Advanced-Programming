package Kalkulator;

//IMPLEMENTACIJA NA DESIGN PATTERN

//Definira strategija za nekakva kalkulacija. Strategy definira deka treba da se pravi nekakva kalkulacija

//Design Pattern Strategy ke prepoznaeme ako ima kalkulacii, presmetki koi mozam da gi piknam pod edna kapa a vnatre se razlicni.Se koristi sekogas koga imam presmetki od ralicen tip no mozat da se stavat pod 1 kapa

public interface Strategy{

     double execute(double num1, double num2); //interface

}

