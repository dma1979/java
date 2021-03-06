# ZOO modules

## Compiling Simple Solid Module without dependencies
Compile/Recompile 'zoo.animal.feeding' from 'zoo/feeding' folder:
`javac --module-path mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java`
Alternate forms:
* `javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/*.java`
* `javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java`
* `javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/module-info.java`
* `javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/*.java`

Describe the module
* `java -p mods -d zoo.animal.feeding`
* `java -p mods --describe-module zoo.animal.feeding`

### 'javac' options:
* `-d <dir>` - Directory for class files
* `-p <path>` or `--module-path <path>` - Module path

## Running Simple Solid Module without dependencies
Run program from 'zoo.animal.feeding'
`java --module-path feeding --module zoo.animal.feeding/zoo.animal.feeding.Task`
Alternate forms
* `java -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task`

### 'java' options:
* `-m <name>` or  `--module <name>` - Module name
* `-p <path>` or `--module-path <path>` - Module path

##Packaging Simple Solid Module without dependencies
* Create jar for 'zoo.animal.feeding'
`jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .`
* Run program from jar using the `mods` directory
`java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task`
  
### 'jar options:
* `-c` or `--create` - creates a new JAR file
* `-v` or `--verbose`  - prints details when working with JAR files
* `-f <fileName>` or `--file <fileName>` - defines JAR filename
* `-C <directory>`  - defines a directory containing files to be used to create the JAR

## Compiling Dependent Modules without dependencies
### CARE module
* Compile/Recompile 'zoo.animal.care' from 'zoo' folder:
`javac -p mods -d care care/zoo/animal/care/details/*.java care/zoo/animal/care/medical/*.java care/module-info.java`
* pack to jar
`jar -cvf mods/zoo.animal.care.jar -C care/ .`
* Describe the module
`java -p mods -d zoo.animal.care`

### TALKS module
* Compile/Recompile 'zoo.animal.talks' from 'zoo' folder:
`javac -p mods -d talks talks/zoo/animal/talks/content/*.java talks/zoo/animal/talks/media/*.java talks/zoo/animal/talks/schedule/*.java talks/module-info.java`
* Package 'zoo.animal.talks' module
`jar -cvf mods/zoo.animal.talks.jar -C talks/ .`
* run Announcement
`java -p mods -m zoo.animal.talks/zoo.animal.talks.media.Announcement`
  
### STAFF module
* Compile/Recompile 'zoo.animal.talks' from 'zoo' folder:
`javac -p mods -d staff staff/zoo/staff/*.java staff/module-info.java`
* Package 'zoo.animal.talks' module
`jar -cvf  mods/zoo.staff.jar -C staff/ .`

## module-info File details
### exports
It gives  more levels of access control using with modules.

| Level | Within module code | Outside module |
|-------|:-------------------|:---------------|
|protected | Available only within package or to subclasses | Accessible to subclasses only if package is ex |
|public |Available to all classes | Accessible only if package is exported |

### requires transitive
`transitive moduleName` it means that any module that requires this module will also depend on moduleName.

### provides, uses, and opens
* The `provides` keyword specifies that a class provides an implementation of a service.
* The `uses` keyword specifies that a module is relying on a service
* `opens`. Since reflection can be dangerous, the module system requires developers to explicitly allow reflection 
  in the module-info if they want calling modules to be allowed to use it.
  
## Listing modules
* `java --list-modules` lists modules in JDK
* `java -p mods --list-modules` lists JDK + own modules
* `java --show-module-resolution -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task` runs a program with Module Resolution 
* `jar -f mods/zoo.animal.feeding.jar -d` or `jar --file mods/zoo.animal.feeding.jar --describe-module` describes a module in jar-file 
* `jdeps -s mods/zoo.animal.feeding.jar` or `jdeps -summary mods/zoo.animal.feeding.jar` tells what dependencies are actually used rather than simply declared.


## The jmod Command
Oracle recommends using JAR files for most modules.
JMOD files are recommended only when you have native libraries or something that can’t go inside a JAR file.

| Operation | Description |
|-------|:-------------------|
| create |Creates a JMOD file.|
| extract |Extracts all files from the JMOD. Works like unzipping.|
| describe |Prints the module details such as requires.|
| list |Lists all files in the JMOD file.|
| hash |Shows a long string that goes with the file|
