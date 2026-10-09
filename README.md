# FTC Base Code
> Prizmatics' base code for an FTC robot

## Good principles to follow
Generally, this code follows the [KISS](https://en.wikipedia.org/wiki/KISS_principle) principle.

### Filenames
Start every Java OpMode's name with "M_" if it is a TeleOp OpMode, or an "A_" if it is an Autonomous 
OpMode.
Start every subsystem's name with "Sub_".

For example:
```
A_Gather.java // this file is for an automatic OpMode
M_OneController.java // this file is for a manual OpMode 
Sub_Outtake // this file is for a subsystem
```

### Hardware device names
When configuring devices on the REV Driver Hub, use snake case (`one_two_three`). When giving it a 
variable name, use camel case (`oneTwoThree`) as per usual.

For example:
```java
outtakeLeft = hardwareMap.dcMotor.get("outtake_left"); // notice the different cases for outtakeLeft and outtake_left 
```

### OpMode Annotations
Every `public class` that `extends OpMode` (an `OpMode`) should be annotated with
```java
@Autonomous(name="A_Something", group="Linear OpMode") // if it's autonomous, or
@TeleOp(name="M_Something", group="Linear OpMode") // if it's manual
```
