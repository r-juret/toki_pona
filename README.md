# Android Toki pona renderer
## Introduction
### Toki pona
Toki pona is a philosophical language known for its small vocabulary. It has been created by Sonja Lang a Canadian linguist in 2001.
### Sitelen Sitelen
Sitelen Sitelen is the Mayan script equivalent of Toki pona. You can see what it looks like on this link https://omniglot.com/conscripts/sitelen.htm.
We used the SVG images from the JavaScript project of Olaf Janssen https://github.com/olafjanssen/sitelen-sitelen-renderer/
which is used on his website http://livingtokipona.smoishele.com/examples/liveinput/liveinput.html.
### Team
We are a team of 4 computer science students, supervised by one of our teacher-researcher. This project is our second year project of our 2 years diploma.
## Description
In this project we have created a jar library in Java to translate a toki pona sentence into a Sitelen Sitelen sentence.
To translate into Sitelen Sitelen.

On this repository you can find :
* The jar file of our Java project
* The Java project
* The Android project which use our jar
## Project
### Images
You find all the images in the java library : /java_project/ressources
And for the Android project all the images are already include
### Jar 
The jar library as been compiled with Java 1.8.
With the jar file, you have to use the class PhraseDetection, this is a static class. You first have to use the setPaths(String db, String res).
* db: The path to the folder which contains the image database in which a folder "glyphs" must be present, in the glyphs folder, the folders "containers" and "words" must be present. Currently we don't use the ponctuation and the syllabes in our project.
* results: the path where you want the results to be created. It will creates 2 folders : 
    * res : the results of the translation
    * temp: the temporary files (parts of the sentence)

Once you set the paths, you can use the method draw(String sentence). This is the main method of our class. It will translate the sentence and will return the path to the svg file created (int the folder "results" that you set previously).

Requirement : You are free to use our jar to your projects but you must have the SVG file for every word of toki pona.
### Java project 
On the Java project you will can see our algorithm and how they are working. (The most part of the description is in French).
### Android project
This is a simple android application with the API 23. In this app we use our jar to translate any sentence into a SVG file on which you can zoom, you can also save it into your smartphone.