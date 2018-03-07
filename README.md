# Android-Skeleton
Create Android Project from Command Line Interface 

Hola!

I had seen many websites that show building apps from commandline interface with gradle support

but none say that how to create the project form cli so, every time when i want to create a project i have to open android studio doing blabla.... so, i had created a java file that accepts project name and package somemore like android studio and creates a project in gradle build-able project

Note : I dont know whether Already had created such project or studio create a project itself fron cli because i dont find any such if you know them please tell them to me


1.What is does:

As your requirement is to create a project from cli it takes project name,package name,some other params and gives output a skeleton android project with gradle support just like what android studio does thus we don't have to open android studio to create a new android project.

2.For who:

like me if you don't have enough ram to uphold android studio or want to use only cli to develop android apps with gradle support.Remember if you want to start android development from cli it just takes avg 700mb ram may be lower to 250mb in some cases but android studio takes at least in my case a 2gb ram so i got the things better with this

3.Source Code:

Source code available at same repository in GitHub freely available so you can modify to your need.Tool written in java

4.Usage :

Type as stated above.....

TODO: 

// If you find some meathod can be improved create an issue with solution i prefer improved code plus less bloat

usage

download jar from releases and open with java -jar AndroidSkeleton.jar Enter detailes Your project created i dont include gradlewrapper so copy wrapper file from other projects to this to root/gradle/wrapper/copy_here

If you have suggesion please give it to me i will defenately take that

if you find the code childish yeah i wrote every line of code by googling it because i don't much java

Also if you have time add simple gui- Graphical User Interface to this using swing of fx
report back your gui version to me
