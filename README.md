TRBÄC Verifier
=========================

A verifier for TRBÄC security policies.

To use in Eclipse, install the following plug-ins:
* Xtend 2.8 ([Xtext update sites](https://www.eclipse.org/Xtext/download.html))
* EMF-IncQuery 1.0.0 ([EMF-IncQuery update sites](https://www.eclipse.org/incquery/download.php))
* VIATRA 0.7 ([VIATRA update sites](https://www.eclipse.org/viatra/downloads.php>))
* m2e 1.5+ ([m2e update site](http://download.eclipse.org/technology/m2e/releases/))

To set up the project in eclipse:
1. Right click on the module `com.vanderhighway.trbac.model`, Configure -> Convert to modelling project.
2. Right click on the module `com.vanderhighway.trbac.patterns`, Configure -> Convert to VIATRA Query project.

To build the project:
1. Go to the `com.vanderhighway.trbac.model` project and generate the model code with the `model/petrinet.genmodel` file.
2. Build the `trbac-verifier` project with `mvn install clean`.


